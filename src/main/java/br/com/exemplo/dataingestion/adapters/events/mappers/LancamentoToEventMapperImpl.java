package br.com.exemplo.dataingestion.adapters.events.mappers;

import org.springframework.stereotype.Component;

import br.com.exemplo.dataingestion.adapters.events.entities.ContaEvent;
import br.com.exemplo.dataingestion.adapters.events.entities.DataLancamentoEvent;
import br.com.exemplo.dataingestion.adapters.events.entities.LancamentoEvent;
import br.com.exemplo.dataingestion.domain.entities.Lancamento;

@Component
public class LancamentoToEventMapperImpl implements EventMapper<Lancamento,DataLancamentoEvent > {
    @Override
    public DataLancamentoEvent convert(Lancamento lancamento) {
        return DataLancamentoEvent.builder()
                .data(
                        LancamentoEvent.builder()
                .valorLancamento(lancamento.getValorLancamento())
                .textoComplementoLancamento(lancamento.getTextoComplementoLancamento())
                .siglaSistemaOrigem(lancamento.getSiglaSistemaOrigem())
                .numeroIdentificacaoLancamentoConta(lancamento.getNumeroIdentificacaoLancamentoConta())
                .metadados(lancamento.getMetadados())
                .indicadorLancamentoCompulsorioOcorrencia(lancamento.isIndicadorLancamentoCompulsorioOcorrencia())
                .dataLancamento(lancamento.getDataLancamento())
                .dataContabilLancamento(lancamento.getDataContabilLancamento())
                .conta(
                        ContaEvent.builder()
                                .numeroUnicoConta(lancamento.getConta().getNumeroUnicoConta())
                                .codigoSufixoConta(lancamento.getConta().getCodigoSufixoConta())
                        .build()
                )
                .codigoTipoOperacao(lancamento.getCodigoTipoOperacao())
                .codigoMotivoLancamento(lancamento.getCodigoMotivoLancamento())
                .codigoMoedaTransacao(lancamento.getCodigoMoedaTransacao())
                .build()
        )
                .build();
    }
}
