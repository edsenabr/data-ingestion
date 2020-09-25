package br.com.exemplo.dataingestion.adapters.events.listeners;

import br.com.exemplo.dataingestion.adapters.events.entities.DataLancamentoEvent;
import br.com.exemplo.dataingestion.adapters.events.mappers.EventMapper;
import br.com.exemplo.dataingestion.application.usecases.UseCase;
import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
@KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
        topics = "${data.ingestion.producer.topic}",
        containerFactory = "kafkaListenerContainerFactory")
@RequiredArgsConstructor
public class IngestionListener {

    private final UseCase<List<Lancamento>,Boolean> extratoService;
    private final EventMapper<DataLancamentoEvent, Lancamento> dataLancamentoEventLancamentoEventMapper;
    private final MeterRegistry simpleMeterRegistry;

    @SneakyThrows
    @KafkaHandler
    public void processaIngestion(List<DataLancamentoEvent> dataLancamentoEvent)
    {
        log.info("KZRX {} {} {} ",Thread.currentThread().getId(),dataLancamentoEvent.size(), InetAddress.getLocalHost().getHostName());
        extratoService.execute(
                dataLancamentoEvent.stream()
                .map(dataLancamentoEvent1 -> dataLancamentoEventLancamentoEventMapper.convert(dataLancamentoEvent1))
                .collect(Collectors.toList())
        );
    }
}
