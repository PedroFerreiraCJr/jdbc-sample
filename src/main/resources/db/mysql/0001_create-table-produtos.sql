CREATE TABLE produto(
	id					INT AUTO_INCREMENT,
	nome				VARCHAR(50) NOT NULL,
	descricao			VARCHAR(255),
	CONSTRAINT pk_produto_id PRIMARY KEY (id)
	
)Engine=InnoDB;
