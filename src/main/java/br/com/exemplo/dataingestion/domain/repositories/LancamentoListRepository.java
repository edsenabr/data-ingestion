package br.com.exemplo.dataingestion.domain.repositories;

import java.util.List;

import br.com.exemplo.dataingestion.domain.entities.Lancamento;

public interface LancamentoListRepository {
    public List<String> save(List<Lancamento> lancamento);
}
