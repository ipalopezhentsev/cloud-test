package cloud.test;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * For testing k8s/spring boot liveness/readiness statuses.
 * After context starts but before this runner starts,
 * boot will return k8s liveness probe as OK (so that it doesn't have to restart it),
 * but readiness probe as not ok (so no traffic will be sent).
 * After this finishes both probes will be ok and k8s svc will start serving traffic to it.
 */
@Service
public class TimeTakingApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Sleeping for 20 secs");
        Thread.sleep(Duration.ofSeconds(20));
        System.out.println("Done sleeping; starting serving");
    }
}
