CREATE TABLE categoria(
	id					INT AUTO_INCREMENT,
	nome				VARCHAR(50) NOT NULL,
	CONSTRAINT pk_categoria_id PRIMARY KEY (id)
	
)Engine=InnoDB;

INSERT INTO categoria(nome) VALUES('Eletronico');
INSERT INTO categoria(nome) VALUES('Eletrodomestico');
INSERT INTO categoria(nome) VALUES('Movel');

ALTER TABLE produto ADD COLUMN categoria_id INT NOT NULL;

ALTER TABLE produto ADD CONSTRAINT fk_produto_categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria (id);