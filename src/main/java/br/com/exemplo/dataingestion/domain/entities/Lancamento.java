package br.com.exemplo.dataingestion.domain.entities;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Lancamento {
    private UUID numeroIdentificacaoLancamentoConta;
    private String codigoTipoOperacao;
    private String valorLancamento;
    private String codigoMoedaTransacao;
    private String siglaSistemaOrigem;
    private String codigoMotivoLancamento;
    private String textoComplementoLancamento;
    private boolean indicadorLancamentoCompulsorioOcorrencia;
    private String dataContabilLancamento;
    private String dataLancamento;
    private Conta conta;
    private Map<String,Object> metadados;
}
