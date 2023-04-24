package com.enjoybycode.customer;

import com.enjoybycode.amqp.RabbitMQMessageProducer;
import com.enjoybycode.clients.fraud.FraudCheckResponse;
import com.enjoybycode.clients.fraud.FraudClient;
import com.enjoybycode.clients.notification.NotificationClient;
import com.enjoybycode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate,
                              FraudClient fraudClient,
                              NotificationClient notificationClient,
                              RabbitMQMessageProducer producer) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        //send to the fraud with eureka server
       /* FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );*/

        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudsterResponse = fraudClient.isFraudster(customer.getId());

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(), "you are not fraudster");

        if (fraudsterResponse.isFraudster()) {
            log.info("customer is fraudster ");
            throw new IllegalStateException(" customer is fraudster");
        } else {
//            notificationClient.sendNotification(notificationRequest);
            log.info("sending notification ... ");
            producer.publish(notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key");
        }

    }
}
