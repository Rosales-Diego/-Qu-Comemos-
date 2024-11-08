package jpa;

import dao.EncargadoDAO;
import models.Encargado;

public class EncargadoDAOHibernateJPA extends GenericDAOHibernateJPA<Encargado> implements EncargadoDAO {

	public EncargadoDAOHibernateJPA() {
		super(Encargado.class);
	}
}