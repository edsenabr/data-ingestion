package br.com.exemplo.dataingestion.adapters.events.entities;

import br.com.exemplo.dataingestion.domain.entities.Conta;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LancamentoEvent {
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
    private ContaEvent conta;
    private Map<String,Object> metadados;
}
