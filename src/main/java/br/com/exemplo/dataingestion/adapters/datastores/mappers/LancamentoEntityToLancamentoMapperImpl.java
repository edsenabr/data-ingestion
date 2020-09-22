package br.com.exemplo.dataingestion.adapters.datastores.mappers;

import br.com.exemplo.dataingestion.adapters.datastores.entities.LancamentoEntity;
import br.com.exemplo.dataingestion.domain.entities.Conta;
import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import org.springframework.stereotype.Component;

@Component
public class LancamentoEntityToLancamentoMapperImpl implements EntityMapper<LancamentoEntity,Lancamento> {
    @Override
    public Lancamento convert(LancamentoEntity lancamentoEntity) {
        return Lancamento.builder()
                .codigoMoedaTransacao(lancamentoEntity.getCodigoMoedaTransacao())
                .codigoMotivoLancamento(lancamentoEntity.getCodigoMotivoLancamento())
                .codigoTipoOperacao(lancamentoEntity.getCodigoTipoOperacao())
                .conta(
                        Conta.builder()
                                .codigoSufixoConta(lancamentoEntity.getConta().getCodigoSufixoConta())
                                .numeroUnicoConta(lancamentoEntity.getConta().getNumeroUnicoConta())
                        .build()
                )
                .dataContabilLancamento(lancamentoEntity.getDataContabilLancamento())
                .dataLancamento(lancamentoEntity.getDataLancamento())
                .indicadorLancamentoCompulsorioOcorrencia(lancamentoEntity.isIndicadorLancamentoCompulsorioOcorrencia())
                .metadados(lancamentoEntity.getMetadados())
                .numeroIdentificacaoLancamentoConta(lancamentoEntity.getNumeroIdentificacaoLancamentoConta())
                .siglaSistemaOrigem(lancamentoEntity.getSiglaSistemaOrigem())
                .textoComplementoLancamento(lancamentoEntity.getTextoComplementoLancamento())
                .valorLancamento(lancamentoEntity.getValorLancamento())
                .build();
    }
}
