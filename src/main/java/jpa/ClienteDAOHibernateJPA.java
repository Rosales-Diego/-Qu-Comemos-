package jpa;

import dao.ClienteDAO;
import models.Cliente;

public class ClienteDAOHibernateJPA extends GenericDAOHibernateJPA<Cliente> implements ClienteDAO {

	public ClienteDAOHibernateJPA() {
		super(Cliente.class);
	}
}