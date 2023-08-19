package br.com.digitalhouse.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Dentista implements Serializable {
    private Integer id;
    private String nome;
    private String cro;
    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;

    public Dentista(String nome,
                    String cro,
                    LocalDate dataNascimento,
                    EspecialidadeEnum especialidade) {
        this.nome = nome;
        this.cro = cro;
        this.dataNascimento = dataNascimento;
        this.especialidade = especialidade;
    }

    public Dentista(Integer id,
                    String nome,
                    String cro,
                    LocalDate dataNascimento,
                    EspecialidadeEnum especialidade) {
        this.id = id;
        this.nome = nome;
        this.cro = cro;
        this.dataNascimento = dataNascimento;
        this.especialidade = especialidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimentoComoDate() {
        return Date.valueOf(getDataNascimento());
    }

    public EspecialidadeEnum getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeEnum especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cro='" + cro + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", especialidade=" + especialidade +
                '}';
    }
}
