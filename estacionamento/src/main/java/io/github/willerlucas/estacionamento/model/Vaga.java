package io.github.willerlucas.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_VAGA")
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
}
