-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)

-- Tabela Cadastro de produto

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
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Tabela Cadastro de usuario

create table cad_usuario(
    id int not null auto_increment,
    nome varchar(30) not null,
    login varchar(30) not null,
    senha varchar(32) not null,
    administrador tinyint(1),
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Tabela Cadastro de vendas

create table vendasProduto(
    id_VendasProdutos int not null auto_increment,
    pedido int not null,
    produto int not null,
    preço double not null,
    PRIMARY KEY(id_VendasProdutos),
    FOREIGN KEY (pedido) REFERENCES vendas (id_vendas)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


create table vendas(
    id_vendas int not null auto_increment,
    data date,
    tipo varchar(20),
    PRIMARY KEY(id_vendas)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


