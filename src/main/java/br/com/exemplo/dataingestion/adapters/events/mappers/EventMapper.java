package br.com.exemplo.dataingestion.adapters.events.mappers;

public interface EventMapper<I,O> {
    O convert(I i);
}
