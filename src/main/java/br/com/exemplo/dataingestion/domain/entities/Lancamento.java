package br.com.exemplo.dataingestion.domain.entities;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty("id")
    private UUID numeroIdentificacaoLancamentoConta;

    @JsonProperty("to")
    private String codigoTipoOperacao;

    @JsonProperty("vl")
    private String valorLancamento;

    @JsonProperty("mt")
    private String codigoMoedaTransacao;

    @JsonProperty("so")
    private String siglaSistemaOrigem;

    @JsonProperty("cm")
    private String codigoMotivoLancamento;

    @JsonProperty("tc")
    private String textoComplementoLancamento;

    @JsonProperty("ic")
    private boolean indicadorLancamentoCompulsorioOcorrencia;

    @JsonProperty("dc")
    private String dataContabilLancamento;

    @JsonProperty("dt")
    private String dataLancamento;

    @JsonProperty("ct")
    private Conta conta;

    @JsonProperty("md")
    private Map<String,Object> metadados;
}
