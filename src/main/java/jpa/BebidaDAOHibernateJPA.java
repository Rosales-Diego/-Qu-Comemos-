package jpa;

import dao.BebidaDAO;
import models.Bebida;

public class BebidaDAOHibernateJPA extends GenericDAOHibernateJPA<Bebida> implements BebidaDAO {

	public BebidaDAOHibernateJPA() {
		super(Bebida.class);
	}

}
