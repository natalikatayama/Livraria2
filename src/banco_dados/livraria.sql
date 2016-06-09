CREATE DATABASE livraria;
USE livraria;
CREATE TABLE Funcionario (
	IdFuncionario INT(11) AUTO_INCREMENT,
	Login VARCHAR(20),
	Senha VARCHAR(16),
	Nome VARCHAR(30),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(IdFuncionario)
);

CREATE TABLE Editora(
	CodEditora INT(11) AUTO_INCREMENT,
	Cnpj CHAR(18),
	Nome VARCHAR(50),
	Endereco VARCHAR(100),
	Telefone CHAR(14),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(CodEditora)
);

CREATE TABLE Autor(
	CodAutor INT(11) AUTO_INCREMENT,
	CodigoAlt CHAR(14),
	Nome VARCHAR(30),
	DataNasci DATE DEFAULT '1900-01-01',
	DataMorte DATE DEFAULT '1900-01-01',
	LocalNasci VARCHAR(30),
	LocalMorte VARCHAR(30),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(CodAutor)
);

CREATE TABLE Livro( /*Dep. Autor, Editora*/
	CodLivro INT(11) AUTO_INCREMENT,
	Isbn CHAR(13),
	Titulo VARCHAR(100),
	CodAutor INT(11),
	CodEditora INT(11),
	AnoPublicacao INT(4),
	Categoria CHAR(20),
	Resumo VARCHAR(1000),
	Sumario VARCHAR(1000),
	Formato CHAR(20),
	NumPaginas INT(6),
	QtdEstoque INT(7),
	PrecoVenda DECIMAL(6,2),
	PrecoOferta DECIMAL(6,2),
	PrecoCusto DECIMAL(6,2),
	MargemLucro DECIMAL(5,2),
	Q_Oferta BOOLEAN,
	Q_Digital BOOLEAN,
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(CodLivro),
/*CHAVES ESTRANGEIRAS*/
	FOREIGN KEY(CodEditora) REFERENCES Editora(CodEditora),
	FOREIGN KEY(CodAutor) REFERENCES Autor(CodAutor)
);

CREATE TABLE Endereco(
	CodEndereco INT(11) AUTO_INCREMENT,
	Logradouro VARCHAR(30),
	Numero CHAR(5),
	Complemento VARCHAR(20),
	Bairro VARCHAR(30),
	Cidade VARCHAR(30),
	Estado CHAR(2),
	Cep CHAR(9),
	Referencia VARCHAR(50),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(CodEndereco)
);

CREATE TABLE Cliente( /*Dep. Endereço*/
	IdCliente INT(11) AUTO_INCREMENT,
	Email VARCHAR(30),
	Senha VARCHAR(16),
	Nome VARCHAR(15),
	Sobrenome VARCHAR(25),
	Sexo CHAR(1),
	Cpf CHAR(14),
	DataNascimento DATE DEFAULT '1900-01-01',
	Telefone CHAR(14),
	TelefoneAlt CHAR(14),
	Celular CHAR(14),
	Q_Promocional BOOLEAN,
	CodEndereco INT(11),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(IdCliente),
/*CHAVES ESTRANGEIRAS*/
	FOREIGN KEY(CodEndereco) REFERENCES Endereco(CodEndereco)
);

CREATE TABLE Pedido( /*Dep. Cliente*/
	IdPedido INT(11) AUTO_INCREMENT,
	IdCliente INT(11),
	Data DATE DEFAULT '1900-01-01',
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(IdPedido),
/*CHAVE ESTRANGEIRA*/
	FOREIGN KEY(IdCliente) REFERENCES Cliente(IdCliente)
);

CREATE TABLE ItemPedido( /*Dep. Pedido, Livro; FRACA*/
	IdPedido INT(11),
	CodLivro INT(11),
	Quantidade INT(2),
	Subtotal DECIMAL(6,2),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(IdPedido,CodLivro),
/*CHAVES ESTRANGEIRAS*/
	FOREIGN KEY(IdPedido) REFERENCES Pedido(IdPedido),
	FOREIGN KEY(CodLivro) REFERENCES Livro(CodLivro)
);

CREATE TABLE VT_ItemCarrinho( /*Dep. Cliente, Livro; FRACA*/
	IdCliente INT(11),
	CodLivro INT(11),
	Quantidade INT(2),
	Subtotal DECIMAL(6,2),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(IdCliente,CodLivro),
/*CHAVES ESTRANGEIRAS*/
	FOREIGN KEY(IdCliente) REFERENCES Cliente(IdCliente),
	FOREIGN KEY(CodLivro) REFERENCES Livro(CodLivro)
);

CREATE TABLE Pagamento(
	CodPagamento INT(11) AUTO_INCREMENT,
	Forma CHAR(20),
	NumParcelas INT(1),
	ParcelaRestante INT(1),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(CodPagamento)
);

CREATE TABLE Entrega(
	CodEntrega INT(11) AUTO_INCREMENT,
	Tipo CHAR(20),
	Preco DECIMAL(6,2),
	DataEntrega DATE DEFAULT '1900-01-01',
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(CodEntrega)
);

CREATE TABLE Venda( /*Dep. Pedido, Endereço, Pagamento, Entrega*/
	CodVenda INT(11) AUTO_INCREMENT,
	Status CHAR(10),
	Pago CHAR(10),
	IdPedido INT(11),
	CodEndereco INT(11),
	CodPagamento INT(11),
	CodEntrega INT(11),
	XDEAD BOOLEAN DEFAULT FALSE,
/*CHAVE PRIMÁRIA*/
	PRIMARY KEY(CodVenda),
/*CHAVES ESTRANGEIRAS*/
	FOREIGN KEY(IdPedido) REFERENCES Pedido(IdPedido),
	FOREIGN KEY(CodEndereco) REFERENCES Endereco(CodEndereco),
	FOREIGN KEY(CodPagamento) REFERENCES Pagamento(CodPagamento),
	FOREIGN KEY(CodEntrega) REFERENCES Entrega(CodEntrega)
);

/*INSERIR FUNCIONÁRIO*/
INSERT INTO Funcionario
VALUES (NULL,'mariana_peixoto','abc123','Mariana Peixoto',FALSE),
(NULL,'thiago','abc123','Thiago Castro',FALSE),
(NULL,'admin','admin','Administrador',FALSE);
