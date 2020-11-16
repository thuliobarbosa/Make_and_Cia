-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)

USE bdlsi;


-- Tabela cad_produto

DROP TABLE IF EXISTS `cad_produto`;

CREATE TABLE `cad_produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `preco_custo` double NOT NULL,
  `preco_venda` double NOT NULL,
  `categoria` int(11) DEFAULT NULL,
  `cod_fornecedor` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  FOREIGN KEY (categoria) REFERENCES cad_categoria (id),
  FOREIGN KEY (cod_fornecedor) REFERENCES cad_fornecedor (id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

-- Tabela cad_usuario

create table cad_usuario(
    id int not null auto_increment,
    nome varchar(30) not null,
    login varchar(30) not null,
    senha varchar(32) not null,
    administrador tinyint(1),
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Tabela vendasProduto

create table vendasProduto(
    id_VendasProdutos int not null auto_increment,
    pedido int not null,
    produto int not null,
    preço double not null,
    PRIMARY KEY(id_VendasProdutos),
    FOREIGN KEY (pedido) REFERENCES vendas (id_venda)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Tabela vendas

create table vendas(
    id_venda int not null auto_increment,
    data date,
    forma_pagamento varchar(20),
    PRIMARY KEY(id_venda)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

-- Tabela cad_fornecedor

CREATE TABLE cad_fornecedor(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cnpj VARCHAR(15) NOT NULL,
	endereco VARCHAR(80) NOT NULL,
	cidade VARCHAR(40) NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

-- Tabela cad_categoria

CREATE TABLE cad_categoria(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	descricao VARCHAR(70) NOT NULL,
	PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

