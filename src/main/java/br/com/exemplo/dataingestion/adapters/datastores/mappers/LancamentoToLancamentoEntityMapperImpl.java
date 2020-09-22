package br.com.exemplo.dataingestion.adapters.datastores.mappers;

import br.com.exemplo.dataingestion.adapters.datastores.entities.ContaEntity;
import br.com.exemplo.dataingestion.adapters.datastores.entities.LancamentoEntity;
import br.com.exemplo.dataingestion.domain.entities.Conta;
import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import org.springframework.stereotype.Component;

@Component
public class LancamentoToLancamentoEntityMapperImpl implements EntityMapper<Lancamento,LancamentoEntity> {
    @Override
    public LancamentoEntity convert(Lancamento lancamento) {
        return LancamentoEntity.builder()
                .codigoMoedaTransacao(lancamento.getCodigoMoedaTransacao())
                .codigoMotivoLancamento(lancamento.getCodigoMotivoLancamento())
                .codigoTipoOperacao(lancamento.getCodigoTipoOperacao())
                .conta(
                        ContaEntity.builder()
                                .codigoSufixoConta(lancamento.getConta().getCodigoSufixoConta())
                                .numeroUnicoConta(lancamento.getConta().getNumeroUnicoConta())
                        .build()
                )
                .dataContabilLancamento(lancamento.getDataContabilLancamento())
                .dataLancamento(lancamento.getDataLancamento())
                .indicadorLancamentoCompulsorioOcorrencia(lancamento.isIndicadorLancamentoCompulsorioOcorrencia())
                .metadados(lancamento.getMetadados())
                .numeroIdentificacaoLancamentoConta(lancamento.getNumeroIdentificacaoLancamentoConta())
                .routing(lancamento.getNumeroIdentificacaoLancamentoConta().toString())
                .siglaSistemaOrigem(lancamento.getSiglaSistemaOrigem())
                .textoComplementoLancamento(lancamento.getTextoComplementoLancamento())
                .valorLancamento(lancamento.getValorLancamento())
                .build();
    }
}
