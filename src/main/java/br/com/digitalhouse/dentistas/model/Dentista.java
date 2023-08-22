package br.com.digitalhouse.dentistas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Dentista implements Serializable {
    private Integer id;
    private String nome;
    private String cro;
    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;

    public Date getDataNascimentoComoDate() {
        return Date.valueOf(getDataNascimento());
    }

}
