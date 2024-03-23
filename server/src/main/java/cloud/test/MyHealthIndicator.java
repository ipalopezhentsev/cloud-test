package cloud.test;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        //point of down is to show that Spring Boot's k8s liveness/readyness state
        //does not depend on it, despite 'regular' health endpoint will be down due to it
        return Health.outOfService().withDetail("reason", "test").build();
    }
}
