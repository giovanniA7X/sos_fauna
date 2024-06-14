CREATE DATABASE SOS_FAUNA;
USE SOS_FAUNA;

CREATE TABLE Empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(20),
    cnpj VARCHAR(20)
);

alter table Empresa
add column senha VARCHAR(20) NOT NULL;

insert into Empresa (nome, senha) values ('admin', 'admin');
select * from Empresa;

CREATE TABLE Veterinario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    crmv VARCHAR(200),
    cpf VARCHAR(20),
    email VARCHAR(255),
    endereco VARCHAR(255),
    telefone VARCHAR(20)
);

insert into Veterinario (nome, senha) values ('vetadmin', 'vetadmin');
select * from Veterinario;

alter table Veterinario
add column senha VARCHAR(20) NOT NULL;

CREATE TABLE Animal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    especie VARCHAR(255) NOT NULL,
    idadeEstimada INT,
    sexo VARCHAR(20),
    status VARCHAR(255),
    local VARCHAR(255),
    data DATE,
    chip INT
);

ALTER TABLE Consultas MODIFY laudo VARCHAR(255);
ALTER TABLE Consultas MODIFY data DATE;

CREATE TABLE Consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    laudo TEXT,
    data DATE,
    veterinario_id INT,
    animal_id INT,
    FOREIGN KEY (veterinario_id) REFERENCES Veterinario(id),
    FOREIGN KEY (animal_id) REFERENCES Animal(id)
);

CREATE TABLE Exames (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resultado TEXT,
    dataPedido DATE,
    dataResultado DATE,
    consulta_id INT,
    FOREIGN KEY (consulta_id) REFERENCES Consultas(id)
);

CREATE TABLE Receitas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prescricao TEXT,
    data DATE,
    consulta_id INT,
    FOREIGN KEY (consulta_id) REFERENCES Consultas(id)
);
