## Spring Boot - Messaging with RabbitMq
This project utilizes RabbitMQ message queue to enable efficient and reliable communication between two Spring Boot microservices. RabbitMQ is a widely used message broker in microservices architectures, and this project showcases the integration of RabbitMQ to enable asynchronous and resilient communication between services.

### Pre-requisites
 - IDE
 - Java 17+
 - Gradle 7.5+
### Scripts
1. **Auth-service**
- Rabbitmq Configuration
```java
@Configuration
public class RabbitmqConfig {

    private final String exchange = "auth-exchange";
    private final String authQueue = "auth-to-user-register-queue";
    private final String authKey = "auth-to-user-register-key";

    @Bean
    DirectExchange authExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue registerQueue() {
        return new Queue(authQueue);
    }

    @Bean
    public Binding registerBinding(final DirectExchange authExchange, final Queue registerQueue) {
        return BindingBuilder.bind(registerQueue).to(authExchange).with(authKey);
    }
}
```
- Producer
```java
@Service
public class RegisterProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "auth-exchange";
    private final String authKey = "auth-to-user-register-key";

    public RegisterProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendRegisterMessage(RegisterModel registerModel) {
        rabbitTemplate.convertAndSend(exchange, authKey, registerModel);
    }

}
```
- Model
```java
public class RegisterModel implements Serializable {
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    
    //getter and setter
}
```
2. **User-service**
- Consumer
```java
@Service
public class RegisterConsumer {
    private final UserService userService;

    public RegisterConsumer(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "auth-to-user-register-queue")
    public void registerConsumer(RegisterModel registerModel) {
        userService.save(registerModel);
    }
}
```
### Getting started
1. Clone the project to your local machine.
```
https://github.com/ayse-ozcan/rabbitmq-spring-boot.git
```
2. If you have Docker installed locally, you can quickly start RabbitMQ, PostgreSQL, and MongoDB servers using Docker.
- RabbitMQ
```
docker run -d -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=secret -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
- PostgreSQL
```
docker run -d --name some-postgres -e POSTGRES_PASSWORD=secret -e PGDATA=/var/lib/postgresql/data/pgdata -v /custom/mount:/var/lib/postgresql/data -p 5432:5432 postgres
```
- MongoDB
```
docker run -d -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -p 27017:27017 mongo
```
3. Make sure microservices are running.
4. Open Postman, a popular API testing tool.
5. Create a new request in Postman.
6. Choose the HTTP method (POST) for your request.
7. Enter the URL "http://localhost:9090/api/v1/auth/save" as the endpoint.
8. Click on the "Send" button to send the request to the specified URL.
- You will observe that the request sent from the auth-service is also recorded in the user-service.

