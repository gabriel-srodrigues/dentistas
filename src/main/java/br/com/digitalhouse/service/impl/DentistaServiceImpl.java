package br.com.digitalhouse.service.impl;

import br.com.digitalhouse.dao.IDao;
import br.com.digitalhouse.model.Dentista;
import br.com.digitalhouse.service.DentistaService;

import java.util.List;

public class DentistaServiceImpl implements DentistaService {
    private final IDao<Dentista> dentistaIDao;

    public DentistaServiceImpl(IDao<Dentista> dentistaIDao) {
        this.dentistaIDao = dentistaIDao;
    }

    @Override
    public Dentista criarDentista(Dentista dentista) {
        return dentistaIDao.criar(dentista);
    }

    @Override
    public Dentista buscarDentistaPorId(Integer id) {
        return dentistaIDao.buscarPorId(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<Dentista> buscarTodosOsDentistas() {
        return dentistaIDao.buscarTodos();
    }

    @Override
    public Dentista atualizarDentista(Dentista dentista) {
        return dentistaIDao.atualizar(dentista);
    }

    @Override
    public void excluirDentista(Integer id) {
        dentistaIDao.excluir(id);
    }
}
