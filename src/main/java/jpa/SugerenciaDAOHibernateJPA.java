package jpa;

import dao.SugerenciaDAO;
import models.Sugerencia;

public class SugerenciaDAOHibernateJPA extends GenericDAOHibernateJPA<Sugerencia> implements SugerenciaDAO {

	public SugerenciaDAOHibernateJPA() {
		super(Sugerencia.class);
	}

}
