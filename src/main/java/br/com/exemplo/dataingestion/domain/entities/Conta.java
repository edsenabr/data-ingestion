package br.com.exemplo.dataingestion.domain.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Conta {
    private UUID numeroUnicoConta;
    private String codigoSufixoConta;
}
