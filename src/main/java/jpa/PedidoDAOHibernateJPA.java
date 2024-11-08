package jpa;

import dao.PedidoDAO;
import models.Pedido;

public class PedidoDAOHibernateJPA extends GenericDAOHibernateJPA<Pedido> implements PedidoDAO {

	public PedidoDAOHibernateJPA() {
		super(Pedido.class);
	}

//	@Override
//	public float calculatePrice(Pedido pedido) {
//		// TODO Auto-generated method stub
//		return 0;
//	}


	
	
}
