package br.com.digitalhouse.dao.impl;

import br.com.digitalhouse.dao.IDao;
import br.com.digitalhouse.model.Dentista;
import br.com.digitalhouse.model.EspecialidadeEnum;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

class DentistaH2DaoTest {
    private final IDao<Dentista> dentistaH2Dao = new DentistaH2Dao();
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void dadoUmNovoDentistaQualquer_quandoCriamosDentista_entaoRetornarDentistaComId() {
        Dentista dentista = easyRandom.nextObject(Dentista.class);
        Dentista dentistaCriado = dentistaH2Dao.criar(dentista);
        Assertions.assertNotNull(dentistaCriado.getId());
    }

    @Test
    void dadoDentistasNoBancoDeDados_quandoBuscamosTodos_entaoRetornarListaDeDentistas() {
        List<Dentista> dentistas = dentistaH2Dao.buscarTodos();
        Assertions.assertEquals(5, dentistas.size());
    }

    @Test
    void dadoDentistasNoBancoDeDados_quandoBuscamosDentistaPorId1_entaoRetornarDentistaEsperado() {
        Dentista dentista = dentistaH2Dao.buscarPorId(1).orElseThrow();
        Assertions.assertNotNull(dentista);
    }

    @Test
    void dadoDentistasNoBancoDeDados_quandoAtualizamosDentista1_entaoExecutarComSucesso() {
        String nomeDentista = "Gabriel Rodrigues";
        String croDentista = "615.535.105.014";
        LocalDate nascimentoDentista = LocalDate.of(1999, Month.JULY, 8);
        EspecialidadeEnum especialidadeDentista = EspecialidadeEnum.ORTODONTISTA;

        Optional<Dentista> dentistaAntesDaAtualizacao = dentistaH2Dao.buscarPorId(1);
        Assertions.assertTrue(dentistaAntesDaAtualizacao.isPresent());
        Dentista dentista = dentistaAntesDaAtualizacao.get();

        Assertions.assertEquals(nomeDentista, dentista.getNome());
        Assertions.assertEquals(croDentista, dentista.getCro());
        Assertions.assertEquals(nascimentoDentista, dentista.getDataNascimento());
        Assertions.assertEquals(especialidadeDentista, dentista.getEspecialidade());

        String novoNomeDentista = "Gabriel dos Santos Rodrigues";
        EspecialidadeEnum novaEspecialidadeDentista = EspecialidadeEnum.CLINICO_GERAL;

        dentista.setNome(novoNomeDentista);
        dentista.setEspecialidade(novaEspecialidadeDentista);

        dentistaH2Dao.atualizar(dentista);

        Optional<Dentista> optionalDentistaAtualizado = dentistaH2Dao.buscarPorId(1);
        Assertions.assertTrue(optionalDentistaAtualizado.isPresent());
        Dentista dentistaAtualizado = optionalDentistaAtualizado.get();

        Assertions.assertEquals(novoNomeDentista, dentistaAtualizado.getNome());
        Assertions.assertEquals(novaEspecialidadeDentista, dentistaAtualizado.getEspecialidade());
    }

    @Test
    void dadoDentistasNoBancoDeDados_quandoExcluimosDentista1_entaoNaoDarErro() {
        Optional<Dentista> dentistaAntesDaExclusao = dentistaH2Dao.buscarPorId(1);
        Assertions.assertTrue(dentistaAntesDaExclusao.isPresent());

        dentistaH2Dao.excluir(dentistaAntesDaExclusao.get().getId());

        Optional<Dentista> dentistaAposExclusao = dentistaH2Dao.buscarPorId(1);
        Assertions.assertTrue(dentistaAposExclusao.isEmpty());
    }

}