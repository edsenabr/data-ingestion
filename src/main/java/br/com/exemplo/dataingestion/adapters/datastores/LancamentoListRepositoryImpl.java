package br.com.exemplo.dataingestion.adapters.datastores;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import br.com.exemplo.dataingestion.domain.repositories.LancamentoListRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LancamentoListRepositoryImpl implements LancamentoListRepository {

    // private final EntityMapper<Lancamento, LancamentoEntity>
    // lancamentoLancamentoEntityEntityMapper;
    // private final ElasticsearchOperations elasticsearchOperations;

    private final RestHighLevelClient client;
    private ObjectMapper jacksonObjectMapper = new ObjectMapper();
    @Value("${processamento.elasticsearch.index:extrato_alias}")
    private String index;

    Counter counter = Metrics.globalRegistry.counter("elasticsearch.items", "Type", "Record");
    Counter reported = Metrics.globalRegistry.counter("elasticsearch.reported", "Type", "Record");
    AtomicLong took = Metrics.globalRegistry.gauge("elasticsearch.took", new AtomicLong(0));
    AtomicInteger batch = Metrics.globalRegistry.gauge("elasticsearch.batch", new AtomicInteger(0));
    AtomicInteger records = new AtomicInteger(0);

    Timer total = Metrics.globalRegistry.timer("elasticsearch.write", "Type", "Record");
    Timer es = Metrics.globalRegistry.timer("elasticsearch.request", "Type", "Record");

    @Override
    public List<String> save(List<Lancamento> lancamentoList) {
        Timer.Sample writer_timer = Timer.start(Metrics.globalRegistry);
        BulkRequest request = new BulkRequest();
        // request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
        // lancamentoList.stream().forEach(lancamento -> {
        //     try {
        //         // extrato-202010
        //         String indexName = "extrato-".concat(lancamento.getDataContabilLancamento().substring(0, 7));
        //         request.add(new IndexRequest(indexName)
        //                 .id(lancamento.getNumeroIdentificacaoLancamentoConta().toString())
        //                 .source(jacksonObjectMapper.writeValueAsString(lancamento), XContentType.JSON)
        //         );
        //     } catch (JsonProcessingException e) {
        //         log.error("failed to process item", lancamento);
        //     }
        // });
        for (Lancamento lancamento : lancamentoList) {
            try {
                // extrato-202010
                String indexName = "extrato-".concat(lancamento.getDataContabilLancamento().substring(0, 7));
                request.add(new IndexRequest(indexName)
                    .id(lancamento.getNumeroIdentificacaoLancamentoConta().toString())
                    .source(jacksonObjectMapper.writeValueAsString(lancamento), XContentType.JSON)
                );
            } catch (JsonProcessingException e) {
                log.error("failed to process item", lancamento);
            }
        }

        Timer.Sample es_timer = Timer.start(Metrics.globalRegistry);
        BulkResponse bulkResponse;
        try {
            bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
            took.set(bulkResponse.getIngestTookInMillis());
            ;
            es_timer.stop(es);
            int amount = lancamentoList.size();
            int inserted = bulkResponse.getItems().length;
            counter.increment(amount);
            reported.increment(inserted);
            batch.set(inserted);
            log.info(
                "Records so far: {} (added {}, reported {})", 
                records.addAndGet(amount),
                amount,
                inserted
            );
        } catch (IOException e) {
            log.error("failed to send bulk request {}", e.getMessage());
        }
        writer_timer.stop(total);
        return null;
    }

    // @Override
    // public List<String> save(List<Lancamento> lancamentoList) {
    //     Timer.Sample writer_timer = Timer.start(Metrics.globalRegistry);
    //     List<IndexQuery> collect = lancamentoList.stream()
    //             .map(lancamento -> lancamentoLancamentoEntityEntityMapper.convert(lancamento))
    //             .map(lancamentoEntity -> new IndexQueryBuilder()
    //                     .withId(lancamentoEntity.getNumeroIdentificacaoLancamentoConta().toString())
    //                     .withObject(lancamentoEntity)
    //                     .build()
    //             )
    //             .collect(Collectors.toList());
    //     Timer.Sample es_timer = Timer.start(Metrics.globalRegistry);
    //     List<String> idList = elasticsearchOperations.bulkIndex(collect, BulkOptions.builder().withTimeout(TimeValue.timeValueMillis(5000)).build(), IndexCoordinates.of(index));
    //     es_timer.stop(es);
    //     int amount = lancamentoList.size();
    //     int inserted = idList.size();
    //     counter.increment(amount);
    //     reported.increment(inserted);
    //     batch.set(inserted);
    //     log.info(
    //         "Records so far: {} (added {}, reported {})", 
    //         records.addAndGet(amount),
    //         amount,
    //         inserted
    //     );
    //     writer_timer.stop(total);
    //     return idList;
    // }
}
