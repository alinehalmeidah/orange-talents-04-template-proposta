package br.com.orangetalents.MultiServico.check;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HelthCkeck implements HealthIndicator {

    @Override
    public Health health(){
        Map<String, Object> details = new HashMap<>();
        details.put("Versão","1.2.3");
        details.put("descrição","Health Check");
        details.put("endereço","127.0.0.1");

        return Health.status(Status.UP).withDetails(details).build();

    }
}
