package br.com.exemplo.dataingestion.adapters.datastores.entities;

import br.com.exemplo.dataingestion.domain.entities.Conta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(indexName = "extrato_alias",createIndex = false)
public class LancamentoEntity {
    @Id
    private UUID numeroIdentificacaoLancamentoConta;
    private String routing;
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
