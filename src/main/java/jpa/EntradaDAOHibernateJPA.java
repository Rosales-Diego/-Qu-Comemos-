package jpa;

import dao.EntradaDAO;
import models.Entrada;

public class EntradaDAOHibernateJPA extends GenericDAOHibernateJPA<Entrada> implements EntradaDAO {

	public EntradaDAOHibernateJPA() {
		super(Entrada.class);
	}

}
