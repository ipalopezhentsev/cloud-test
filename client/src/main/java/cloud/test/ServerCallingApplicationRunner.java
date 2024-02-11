package cloud.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ServerCallingApplicationRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ServerCallingApplicationRunner.class);
    private final RestClient restClient;

    public ServerCallingApplicationRunner(RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("http://localhost:8080/api/")
                .build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            var obj = restClient.get().uri("testView").retrieve().body(Foo2.class);
            log.info("Received: {}", obj);
        }
    }
}
