package br.com.digitalhouse.files;

import br.com.digitalhouse.model.Dentista;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DentistaTest {
    private final EasyRandom easyRandom = new EasyRandom();

    private List<Dentista> dentistas() {
        List<Dentista> dentistas = new ArrayList<>();
        dentistas.add(easyRandom.nextObject(Dentista.class));
        dentistas.add(easyRandom.nextObject(Dentista.class));
        dentistas.add(easyRandom.nextObject(Dentista.class));
        dentistas.add(easyRandom.nextObject(Dentista.class));
        dentistas.add(easyRandom.nextObject(Dentista.class));
        return dentistas;
    }


    @Test
    void testandoCriacaoELEituraDeArquivos() {
        List<Dentista> dentistas = dentistas();

        try (FileOutputStream fileOutputStream = new FileOutputStream("lista_dentistas.txt")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(dentistas);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream("lista_dentistas.txt")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Dentista> dentistasRetornadosDoArquivo = (ArrayList) objectInputStream.readObject();

//  Podemos fazer destas duas formas para chegar no mesmo resultado do lista.foreach();
//  for (int i = 0; i <= dentistasRetornadosDoArquivo.size(); i++) {}
//  for (Dentista dentista : dentistasRetornadosDoArquivo) {}

            System.out.println("Dentistas encontrados no arquivo!\n----------------------------------------------");
            dentistasRetornadosDoArquivo.forEach(dentista -> {
                String informacoes = """
                        Nome do dentista: %s
                        Data de nascimento: %s
                        CRO: %s
                        Especialidade: %s
                        ----------------------------------------------
                        """.formatted(
                        dentista.getNome(),
                        dentista.getDataNascimento(),
                        dentista.getCro(),
                        dentista.getEspecialidade().name()
                );

                System.out.println(informacoes);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
