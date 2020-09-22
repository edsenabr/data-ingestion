package br.com.exemplo.dataingestion.application.usecases;

public interface UseCase<I,O> {
    O execute(I i);
}
