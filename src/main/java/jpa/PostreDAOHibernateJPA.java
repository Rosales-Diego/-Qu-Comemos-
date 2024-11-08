package jpa;

import dao.PostreDAO;
import models.Postre;

public class PostreDAOHibernateJPA extends GenericDAOHibernateJPA<Postre> implements PostreDAO {

	public PostreDAOHibernateJPA() {
		super(Postre.class);
	}

}
