DROP TABLE IF EXISTS Dentista;

CREATE TABLE Dentista
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    nome            VARCHAR(255) NOT NULL,
    cro             VARCHAR(80)  NOT NULL,
    data_nascimento DATE         NOT NULL,
    especialidade   VARCHAR(80)  NOT NULL
);

INSERT INTO Dentista(nome, cro, data_nascimento, especialidade)
VALUES ('Gabriel Rodrigues', '615.535.105.014', '1999-07-08', 'ORTODONTISTA');
INSERT INTO Dentista(nome, cro, data_nascimento, especialidade)
VALUES ('Miranda Bailey', '860.885.957.225', '1966-01-27', 'ODONTOPEDIATRA');
INSERT INTO Dentista(nome, cro, data_nascimento, especialidade)
VALUES ('Rick Sanches', '172.991.644.169', '1964-12-09', 'CLINICO_GERAL');
INSERT INTO Dentista(nome, cro, data_nascimento, especialidade)
VALUES ('Érick Jacquin', '514.848.066.585', '2000-12-17', 'IMPLANTODENTISTA');
INSERT INTO Dentista(nome, cro, data_nascimento, especialidade)
VALUES ('Edson Arantes Pele', '612.422.833.716', '1940-10-23', 'ORTODONTISTA');