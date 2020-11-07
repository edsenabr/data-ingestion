package br.com.exemplo.dataingestion.domain.entities;

import lombok.*;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Conta {
    @JsonProperty("nu")
    private UUID numeroUnicoConta;

    @JsonProperty("cs")
    private String codigoSufixoConta;
}
