package br.com.exemplo.dataingestion.domain.repositories;

import br.com.exemplo.dataingestion.domain.entities.Lancamento;

import java.util.List;
import java.util.UUID;

public interface LancamentoListRepository {
    public List<String> save(List<Lancamento> lancamento);
}
