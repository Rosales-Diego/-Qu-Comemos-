package utils;

import dao.AdminDAO;
import dao.BebidaDAO;
import dao.ClienteDAO;
import dao.EncargadoDAO;
import dao.EntradaDAO;
import dao.MenuDAO;
import dao.PedidoDAO;
import dao.PlatoPrincipalDAO;
import dao.PostreDAO;
import dao.SugerenciaDAO;
import jpa.AdminDAOHibernateJPA;
import jpa.BebidaDAOHibernateJPA;
import jpa.ClienteDAOHibernateJPA;
import jpa.EncargadoDAOHibernateJPA;
import jpa.EntradaDAOHibernateJPA;
import jpa.MenuDAOHibernateJPA;
import jpa.PedidoDAOHibernateJPA;
import jpa.PlatoPrincipalDAOHibernateJPA;
import jpa.PostreDAOHibernateJPA;
import jpa.SugerenciaDAOHibernateJPA;

public class DaoFactory {
	public static SugerenciaDAO getSugerenciaDAO() {
		return new SugerenciaDAOHibernateJPA();
	}

	public static ClienteDAO getClienteDAO() {
		return new ClienteDAOHibernateJPA();
	}

	public static AdminDAO getAdminDAO() {
		return new AdminDAOHibernateJPA();
	}

	public static EncargadoDAO getEncargadoDAO() {
		return new EncargadoDAOHibernateJPA();
	}

	public static MenuDAO getMenuDAO() {
		return new MenuDAOHibernateJPA();
	}

	public static BebidaDAO getBebidaDAO() {
		return new BebidaDAOHibernateJPA();
	}
	
	public static PlatoPrincipalDAO getPlatoPrincipalDAO() {
		return new PlatoPrincipalDAOHibernateJPA();
	}
	
	public static EntradaDAO getEntradaDAO() {
		return new EntradaDAOHibernateJPA();
	}
	
	public static PostreDAO getPostreDAO() {
		return new PostreDAOHibernateJPA();
	}
	
	public static PedidoDAO getPedidoDAO() {
		return new PedidoDAOHibernateJPA();
	}
}
