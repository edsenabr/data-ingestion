package br.com.exemplo.dataingestion.adapters.events.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContaEvent {
    private UUID numeroUnicoConta;
    private String codigoSufixoConta;
}
