package br.com.exemplo.dataingestion.adapters.datastores.repositories;

import br.com.exemplo.dataingestion.adapters.datastores.entities.LancamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LancamentoEntityRepository extends ElasticsearchRepository<LancamentoEntity, UUID> {
    Page<LancamentoEntity> findByContaNumeroUnicoConta(UUID idConta, Pageable pageable);
}
