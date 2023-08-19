package br.com.digitalhouse.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    T criar(T entidade);

    Optional<T> buscarPorId(Integer id);

    List<T> buscarTodos();

    T atualizar(T dentista);

    void excluir(Integer id);

}
