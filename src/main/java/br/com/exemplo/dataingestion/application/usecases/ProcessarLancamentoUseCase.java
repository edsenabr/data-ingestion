package br.com.exemplo.dataingestion.application.usecases;

import br.com.exemplo.dataingestion.domain.entities.Lancamento;
import br.com.exemplo.dataingestion.domain.repositories.LancamentoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProcessarLancamentoUseCase implements UseCase<List<Lancamento>,Boolean>{

    private final LancamentoListRepository lancamentoListRepository;

    @Override
    public Boolean execute(List<Lancamento> lancamentoList) {
        lancamentoListRepository.save(lancamentoList);
        return true;
    }
}
