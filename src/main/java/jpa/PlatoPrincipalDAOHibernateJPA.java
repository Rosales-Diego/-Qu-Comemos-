package jpa;

import dao.PlatoPrincipalDAO;
import models.PlatoPrincipal;

public class PlatoPrincipalDAOHibernateJPA extends GenericDAOHibernateJPA<PlatoPrincipal> implements PlatoPrincipalDAO {

	public PlatoPrincipalDAOHibernateJPA() {
		super(PlatoPrincipal.class);
	}

}
