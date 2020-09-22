package br.com.exemplo.dataingestion.adapters.events.entities;

import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DataLancamentoEvent {
    private LancamentoEvent data;
}
