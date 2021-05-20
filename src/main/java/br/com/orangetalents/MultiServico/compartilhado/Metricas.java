package br.com.orangetalents.MultiServico.compartilhado;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class Metricas {

    private final MeterRegistry meterRegistry;

    public Metricas(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }


    public void meuContador() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "Cielo"));
        tags.add(Tag.of("banco", "Nubank"));

        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);

    }

}