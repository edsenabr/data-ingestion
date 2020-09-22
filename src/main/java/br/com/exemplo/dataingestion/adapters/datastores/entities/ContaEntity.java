package br.com.exemplo.dataingestion.adapters.datastores.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContaEntity {
    private UUID numeroUnicoConta;
    private String codigoSufixoConta;
}
