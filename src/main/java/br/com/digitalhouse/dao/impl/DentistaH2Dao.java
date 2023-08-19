package br.com.digitalhouse.dao.impl;

import br.com.digitalhouse.dao.ConfiguracaoJdbc;
import br.com.digitalhouse.dao.IDao;
import br.com.digitalhouse.model.Dentista;
import br.com.digitalhouse.model.EspecialidadeEnum;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DentistaH2Dao implements IDao<Dentista> {
    private static final Logger log = Logger.getLogger(DentistaH2Dao.class);
    private static final String SQL_CRIACAO_DENTISTA = """
            INSERT INTO Dentista(nome, cro, data_nascimento, especialidade) VALUES(?, ?, ?, ?);
            """;
    private static final String SQL_BUSCA_DENTISTA_POR_ID = """
            SELECT d.id, d.nome, d.cro, d.data_nascimento, d.especialidade
            FROM Dentista d WHERE d.id = ?
            """;
    private static final String SQL_BUSCAR_TODOS = """
            SELECT d.id, d.nome, d.cro, d.data_nascimento, d.especialidade FROM Dentista d
            """;
    private static final String SQL_EXCLUIR_DENTISTA_POR_ID = "DELETE FROM Dentista WHERE id = ?";
    private static final String SQL_ATUALIZAR_DENTISTA = """
            UPDATE Dentista SET nome = ?, cro = ?, data_nascimento = ?, especialidade = ? WHERE id = ?;
            """;
    private final ConfiguracaoJdbc configuracaoJdbc = new ConfiguracaoJdbc();

    @Override
    public Dentista criar(Dentista entidade) {
        log.info("[dentista_h2]: resgatando conexão para persistir os dados!");
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_CRIACAO_DENTISTA, Statement.RETURN_GENERATED_KEYS)) {
            log.info("[dentista_h2]: configurando persistencia que retorna id");
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getCro());
            statement.setDate(3, entidade.getDataNascimentoComoDate());
            statement.setString(4, entidade.getEspecialidade().name());
            log.info("[dentista_h2]: executando script de persistencia do dentista: " + entidade);
            statement.execute();
            log.info("[dentista_h2]: buscando id retornado da criação do dentista");
            ResultSet resultado = statement.getGeneratedKeys();
            while (resultado.next()) {
                entidade.setId(resultado.getInt(1));
            }
            log.info("[dentista_h2]: id encontrado: " + entidade.getId());
            return entidade;
        } catch (Exception e) {
            log.error("Alguma coisa deu errado!", e);
            return null;
        }
    }

    @Override
    public Optional<Dentista> buscarPorId(Integer id) {
        log.info("[dentista_h2]: buscando dentista por id: " + id);
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_BUSCA_DENTISTA_POR_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Dentista dentista = null;
            while (resultSet.next()) {
                dentista = this.getDentistaByResultSet(resultSet);
                log.info("dentista encontrado: " + dentista);
            }
            return Optional.ofNullable(dentista);
        } catch (Exception e) {
            log.error("Um erro inesperado aconteceu!", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Dentista> buscarTodos() {
        log.info("[dentista_h2]: buscando todos os dentistas salvos no banco de dados (h2)");
        Connection connection = configuracaoJdbc.getConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_BUSCAR_TODOS);

            List<Dentista> dentistas = new ArrayList<>();
            while (resultSet.next()) {
                Dentista dentista = getDentistaByResultSet(resultSet);
                log.info("dentista encontrado com identificação: " + dentista.getId());
                dentistas.add(dentista);
            }
            return dentistas;
        } catch (Exception e) {
            log.error("Um erro inesperado aconteceu!", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Dentista atualizar(Dentista dentista) {
        log.info("[dentista_h2]: atualizando dados do dentista");
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_ATUALIZAR_DENTISTA)) {
            statement.setString(1, dentista.getNome());
            statement.setString(2, dentista.getCro());
            statement.setDate(3, dentista.getDataNascimentoComoDate());
            statement.setString(4, dentista.getEspecialidade().name());
            statement.setInt(5, dentista.getId());
            log.info("[dentista_h2]: executando atualização fisica");
            statement.executeUpdate();
            return dentista;
        } catch (Exception e) {
            log.error("Opa, um erro insperado aconteceu :)", e);
            return null;
        }
    }

    @Override
    public void excluir(Integer id) {
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_EXCLUIR_DENTISTA_POR_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            log.info("Opa, um erro inesperado aconteceu!", e);
        }
    }

    private Dentista getDentistaByResultSet(ResultSet resultSet) throws SQLException {
        Dentista dentista;
        Integer id = resultSet.getInt(1);
        String nome = resultSet.getString(2);
        String cro = resultSet.getString(3);
        LocalDate dataNascimento = resultSet.getDate(4).toLocalDate();
        EspecialidadeEnum especialidade = EspecialidadeEnum.valueOf(resultSet.getString(5));
        dentista = new Dentista(id, nome, cro, dataNascimento, especialidade);
        return dentista;
    }

}
