package cloud.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.concurrent.*;

@Component
public class ServerCallingApplicationRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ServerCallingApplicationRunner.class);
    private final RestClient restClient;

    public ServerCallingApplicationRunner(
            RestClient.Builder restClient,
            @Value("${SERVER_ADDR}")
            String server
    ) {
        this.restClient = restClient
                .baseUrl(server)
                .build();
    }

    @Override
    public void run(ApplicationArguments args) {
//        var exSvc = Executors.newVirtualThreadPerTaskExecutor();
//        var exSvc = Executors.newFixedThreadPool(4);
        var exSvc = new ThreadPoolExecutor(4, 4, 1, 
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        exSvc.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        while (true) {
            exSvc.submit(()-> {
                var obj = restClient.get().uri("testView").retrieve().body(Foo2.class);
                log.info("Received: {}", obj);
            });
        }
    }
}
