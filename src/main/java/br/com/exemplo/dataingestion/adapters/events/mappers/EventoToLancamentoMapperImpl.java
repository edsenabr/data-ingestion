package br.com.exemplo.dataingestion.adapters.events.mappers;

import br.com.exemplo.dataingestion.adapters.events.entities.DataLancamentoEvent;
import br.com.exemplo.dataingestion.domain.entities.Conta;
import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import org.springframework.stereotype.Component;

@Component
public class EventoToLancamentoMapperImpl implements EventMapper<DataLancamentoEvent, Lancamento> {
    @Override
    public Lancamento convert(DataLancamentoEvent dataLancamentoEvent) {
        return Lancamento.builder()
                .valorLancamento(dataLancamentoEvent.getData().getValorLancamento())
                .textoComplementoLancamento(dataLancamentoEvent.getData().getTextoComplementoLancamento())
                .siglaSistemaOrigem(dataLancamentoEvent.getData().getSiglaSistemaOrigem())
                .numeroIdentificacaoLancamentoConta(dataLancamentoEvent.getData().getNumeroIdentificacaoLancamentoConta())
                .metadados(dataLancamentoEvent.getData().getMetadados())
                .indicadorLancamentoCompulsorioOcorrencia(dataLancamentoEvent.getData().isIndicadorLancamentoCompulsorioOcorrencia())
                .dataLancamento(dataLancamentoEvent.getData().getDataLancamento())
                .dataContabilLancamento(dataLancamentoEvent.getData().getDataContabilLancamento())
                .conta(
                        Conta.builder()
                                .numeroUnicoConta(dataLancamentoEvent.getData().getConta().getNumeroUnicoConta())
                                .codigoSufixoConta(dataLancamentoEvent.getData().getConta().getCodigoSufixoConta())
                        .build()
                )
                .codigoTipoOperacao(dataLancamentoEvent.getData().getCodigoTipoOperacao())
                .codigoMotivoLancamento(dataLancamentoEvent.getData().getCodigoMotivoLancamento())
                .codigoMoedaTransacao(dataLancamentoEvent.getData().getCodigoMoedaTransacao())
                .build();
    }
}
