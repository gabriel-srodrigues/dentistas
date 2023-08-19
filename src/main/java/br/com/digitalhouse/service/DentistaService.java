package br.com.digitalhouse.service;

import br.com.digitalhouse.model.Dentista;

import java.util.List;

public interface DentistaService {

    Dentista criarDentista(Dentista dentista);

    Dentista buscarDentistaPorId(Integer id);

    List<Dentista> buscarTodosOsDentistas();

    Dentista atualizarDentista(Dentista dentista);

    void excluirDentista(Integer id);

}
