package br.com.exemplo.dataingestion.adapters.datastores.mappers;


public interface EntityMapper<I,O> {
    O convert(I i);
}
