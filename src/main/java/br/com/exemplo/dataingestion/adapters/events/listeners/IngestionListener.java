package br.com.exemplo.dataingestion.adapters.events.listeners;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.exemplo.dataingestion.adapters.events.entities.DataLancamentoEvent;
import br.com.exemplo.dataingestion.adapters.events.mappers.EventMapper;
import br.com.exemplo.dataingestion.application.usecases.UseCase;
import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
        topics = "${data.ingestion.producer.topic}",
        containerFactory = "kafkaListenerContainerFactory")
public class IngestionListener {

    public IngestionListener(@Value("${POD_NAME}") String podName, UseCase<List<Lancamento>, Boolean> extratoService,
            EventMapper<DataLancamentoEvent, Lancamento> dataLancamentoEventLancamentoEventMapper) {
        this.extratoService = extratoService;
        this.dataLancamentoEventLancamentoEventMapper = dataLancamentoEventLancamentoEventMapper;
        errors = Metrics.globalRegistry.counter("elasticsearch.errors", "Type", podName);
        failedCounter = Metrics.globalRegistry.counter("elasticsearch.failed", "Type", podName);
        total = Metrics.globalRegistry.timer("elasticsearch.total", "Type", podName);
    }

    private final UseCase<List<Lancamento>,Boolean> extratoService;
    private final EventMapper<DataLancamentoEvent, Lancamento> dataLancamentoEventLancamentoEventMapper;
    private final Counter errors, failedCounter;
    private final Timer total;
    private final AtomicInteger records = new AtomicInteger(0);

    // @SneakyThrows
    @KafkaHandler
    public void processaIngestion(List<DataLancamentoEvent> dataLancamentoEvent)
    {
        Timer.Sample total_timer = Timer.start(Metrics.globalRegistry);
        try {
            extratoService.execute(
                dataLancamentoEvent.stream()
                .map(dataLancamentoEvent1 -> dataLancamentoEventLancamentoEventMapper.convert(dataLancamentoEvent1))
                .collect(Collectors.toList())
            );
        } catch (Exception e) {
            int amount = dataLancamentoEvent.size();
            log.error("Error ocurred processing a batch of {} records: {}  ==> {}", amount, amount, e);
            log.info(
                "Errors so far: {} (added {})", 
                records.addAndGet(amount),
                amount
            );
            errors.increment(amount);
            failedCounter.increment(amount);
        } finally {
            total_timer.stop(total);
        }
    }
}
