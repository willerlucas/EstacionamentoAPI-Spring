
CREATE SCHEMA `estacionamento`;
USE `estacionamento` ;

CREATE TABLE `estacionamento`.`tb_cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(14),
  `email` VARCHAR(50),
  `nome` VARCHAR(50),
  `telefone` VARCHAR(20),
  PRIMARY KEY (`id`));



CREATE TABLE `estacionamento`.`tb_veiculo` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(255),
  `placa` VARCHAR(255),
  `tipo` VARCHAR(255),
  `zero_km` BIT(1) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `estacionamento`.`tb_cliente_veiculos` (
  `cliente_id` BIGINT NOT NULL,
  `veiculos_id` BIGINT NOT NULL,
    FOREIGN KEY (`cliente_id`)
    REFERENCES `estacionamento`.`tb_cliente` (`id`),
    FOREIGN KEY (`veiculos_id`)
    REFERENCES `estacionamento`.`tb_veiculo` (`id`));


CREATE TABLE `estacionamento`.`tb_vaga` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(20),
  PRIMARY KEY (`id`));



CREATE TABLE `estacionamento`.`tb_ticket` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `entrada` DATETIME(6),
  `preco` INT NOT NULL,
  `saida` DATETIME(6),
  `status` VARCHAR(20),
  `vaga_id` BIGINT,
  `veiculo_id` BIGINT,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`vaga_id`)
    REFERENCES `estacionamento`.`tb_vaga` (`id`),
    FOREIGN KEY (`veiculo_id`)
    REFERENCES `estacionamento`.`tb_veiculo` (`id`));
	
	
INSERT INTO `estacionamento`.`tb_veiculo`
(`modelo`,
`placa`,
`tipo`,
`zero_km`)
VALUES
("Fox", "ABA-0021", "CARRO", false),
("Palio", "BBB-1111", "CARRO", false),
("Mustang", "GGG-2222", "CARRO", false),
("Ferrari", "TTT-1111", "CARRO", false);


INSERT INTO `estacionamento`.`tb_cliente`
(`cpf`,`email`,`nome`,`telefone`)
VALUES
("157.123.15-30", "joao@everis.com", "joao", "(48)99999-9999"),
("158.254.368-88", "pedro@everis.com", "pedro", "(48)88888-8888"),
("247.698.002-25", "maria@everis.com", "maria", "(48)9999-9999"),
("123.145.789-00", "ana@everis.com", "ana", "(48)12471-247"),
("159.157.147-11", "felipe@everis.com", "felipe", "(45)1254-2547"),
("157.879.515-77", "luiz@everis.com", "luiz", "(47)1478-8745");


INSERT INTO `estacionamento`.`tb_vaga`
(`status`)
VALUES
("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE"),
("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE"),
("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE"),
("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE"), ("LIVRE");
	
	
