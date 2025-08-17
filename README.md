| Step                  | Command / URL                                                                   | Description                         |
| --------------------- | ------------------------------------------------------------------------------- | ----------------------------------- |
| Build project         | `mvn clean package`                                                             | Builds both microservices           |
| Run ProductService    | `java -jar productservice/target/productservice-1.0.0.jar`                      | Runs ProductService locally         |
| Run OrderService      | `java -jar orderservice/target/orderservice-1.0.0.jar --server.port=8081`       | Runs OrderService on port 8081      |
| Run with Dapr sidecar | `dapr run --app-id <id> --app-port <port> -- java -jar ...`                     | Runs microservice with Dapr sidecar |
| Post product event    | `curl -X POST http://localhost:3500/v1.0/invoke/productservice/method/products` | Publishes product event via Dapr    |

brew install dapr/tap/dapr-cli  

dapr init

dapr run \
--app-id productservice \
--app-port 8080 \
--dapr-http-port 3500 \
--components-path ./components \
-- java -jar productservice/target/productservice-1.0.0.jar