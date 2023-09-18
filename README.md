# Academia - Sistema de Gerenciamento

Este projeto foi desenvolvido como parte da Disciplina de Projeto Orientado a Objetos do Curso de Sistemas de Informação do IFF Campos Centro. Ele representa uma aplicação de exemplo para um sistema de gerenciamento de uma academia, concebido com o intuito de auxiliar no aprendizado de programação. O sistema oferece uma série de funcionalidades básicas que permitem administrar os principais aspectos de uma academia.

## Funcionalidades Principais

**Cadastro de Clientes:** Registre informações dos clientes, como nome, CPF, endereço e telefone.

**Registro de Planos:** Defina diferentes tipos de planos de adesão para os clientes, especificando o período de validade, as modalidades disponíveis, as formas de pagamento aceitas e o preço.

**Contratação de Professores:** Cadastre informações dos professores, incluindo nome, CPF, salário, especialidade e turno de trabalho. Atribua alunos a professores para um acompanhamento personalizado.

**Agendamento de Aulas:** Permita que os clientes agendem aulas com base nas modalidades oferecidas e nos horários disponíveis.

**Gerenciamento de Treinamentos:** Crie e gerencie treinamentos individuais para os clientes, incluindo detalhes, datas e horários.

## SQL - Banco De Dados

create database Academia;
use Academia;
-- Tabela de Enderecos
CREATE TABLE Enderecos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(10),
    rua VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255)
);

-- Tabela de Clientes
CREATE TABLE Clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE,
    nome VARCHAR(255),
    telefone VARCHAR(20),
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES Enderecos(id)
);

-- Tabela de Professores
CREATE TABLE Professores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE,
    nome VARCHAR(255),
    salario DECIMAL(10, 2),
    especialidade VARCHAR(255),
    turno VARCHAR(50),
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES Enderecos(id)
);

-- Tabela de Treinamentos
CREATE TABLE Treinamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detalhes TEXT,
    data DATE,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

-- Tabela de Planos
CREATE TABLE Planos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    periodo VARCHAR(50),
    preco DECIMAL(10, 2),
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

-- Tabela de Aulas
CREATE TABLE Aulas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modalidade VARCHAR(255),
    horario DATETIME,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

-- Tabela de Alunos do Professor (relação muitos-para-muitos)
CREATE TABLE Alunos_Professores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professor_id INT,
    aluno_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professores(id),
    FOREIGN KEY (aluno_id) REFERENCES Clientes(id)
);

## Licença:

Este projeto está disponível sob licença de código aberto. Isso significa que você tem a liberdade de usar, modificar e distribuir o código conforme suas necessidades. 
