package jpa;

import dao.AdminDAO;
import models.Admin;

public class AdminDAOHibernateJPA extends GenericDAOHibernateJPA<Admin> implements AdminDAO {

	public AdminDAOHibernateJPA() {
		super(Admin.class);
	}

}
