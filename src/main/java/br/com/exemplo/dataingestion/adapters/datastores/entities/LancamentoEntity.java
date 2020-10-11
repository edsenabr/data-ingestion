package br.com.exemplo.dataingestion.adapters.datastores.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(
    indexName = "extrato_alias",
    createIndex = false,
    useServerConfiguration = true
)
public class LancamentoEntity {
    @Id
    private UUID numeroIdentificacaoLancamentoConta;
    private String codigoTipoOperacao;
    private String valorLancamento;
    private String codigoMoedaTransacao;
    private String siglaSistemaOrigem;
    private String codigoMotivoLancamento;
    private String textoComplementoLancamento;
    private boolean indicadorLancamentoCompulsorioOcorrencia;
    @Field(type = FieldType.Date)
    private String dataContabilLancamento;
    private String dataLancamento;
    @Field(type = FieldType.Nested, includeInParent = true)
    private ContaEntity conta;
    private Map<String,Object> metadados;
}
