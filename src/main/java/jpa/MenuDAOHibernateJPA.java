package jpa;

import dao.MenuDAO;
import models.Menu;

public class MenuDAOHibernateJPA extends GenericDAOHibernateJPA<Menu> implements MenuDAO {

	public MenuDAOHibernateJPA() {
		super(Menu.class);
	}

//	@Override
//	public float getPrecio() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
