package crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.ClienteDAO;
import dao.SugerenciaDAO;
import models.Cliente;
import models.Sugerencia;
import models.TipoSugerencia;
import utils.DaoFactory;

public class SugerenciaTest {

	public static Cliente cliente;
	public static Sugerencia sugerencia1;
	public static Sugerencia sugerencia2;
	public static Sugerencia sugerencia3;

	@BeforeAll
	public static void init() {
		Cliente clienteModel = new Cliente();
		clienteModel.setNombre("nombre1");
		clienteModel.setApellido("apellido1");
		clienteModel.setDni("123456789");
		clienteModel.setEmail(UUID.randomUUID().toString());
		clienteModel.setClave("clave1");

		// Persistimos Cliente primero para que esté en el estado gestionado
		ClienteDAO clienteDao = DaoFactory.getClienteDAO();
		cliente = clienteDao.create(clienteModel);

		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		sugerencia1 = sugerenciaDAO
				.create(new Sugerencia("Contenido de prueba init", TipoSugerencia.ATENCION, cliente));
		sugerencia2 = sugerenciaDAO
				.create(new Sugerencia("Contenido de prueba init", TipoSugerencia.ALIMENTOS, cliente));
		sugerencia3 = sugerenciaDAO
				.create(new Sugerencia("Contenido de prueba init", TipoSugerencia.INFRAESTRUCTURA, cliente));
	}

	@Test
	public void testCreate() {
		Cliente clienteModel = new Cliente();
		clienteModel.setNombre("nombre4");
		clienteModel.setApellido("apellido3");
		clienteModel.setDni("123456789");
		clienteModel.setEmail("email3");
		clienteModel.setClave("clave3");

		// Persistimos el cliente antes de crear y asociar la sugerencia
		ClienteDAO clienteDao = DaoFactory.getClienteDAO();
		Cliente clientePersisted = clienteDao.create(clienteModel);

		// Creamos y persistimos la Sugerencia con el Cliente gestionado
		Sugerencia sugerenciaModel = new Sugerencia("Contenido de prueba", TipoSugerencia.ATENCION, clientePersisted);
		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		Sugerencia sugerenciaSaved = sugerenciaDAO.create(sugerenciaModel);

		assertNotNull(sugerenciaSaved.getId());
		assertEquals("Contenido de prueba", sugerenciaSaved.getContenido());
		assertEquals(TipoSugerencia.ATENCION, sugerenciaSaved.getTipo());
	}

	@Test
	public void testGetById() {
		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		Sugerencia sugerenciaSaved = sugerenciaDAO.getById(sugerencia1.getId());
		assertNotNull(sugerenciaSaved.getId());
	}

	@Test
	public void testgetAll() {
		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		List<Sugerencia> sugerenciaSaved = sugerenciaDAO.getAll("contenido");
		assertNotNull(sugerenciaSaved);
	}

	@Test
	public void testUpdate() {
		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		Sugerencia sugerenciaSaved = sugerenciaDAO.getById(sugerencia1.getId());
		sugerenciaSaved.setContenido("contenido actualizado2");
		sugerenciaSaved = sugerenciaDAO.update(sugerenciaSaved);
		assertEquals("contenido actualizado2", sugerenciaSaved.getContenido());
	}

	@Test
	public void testExists() {
		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		assertTrue(sugerenciaDAO.exists(sugerencia1.getId()));
		assertFalse(sugerenciaDAO.exists((long) -1));
	}

	@Test
	public void testDeleteById() {
		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		assertNotNull(sugerenciaDAO.delete(sugerencia2.getId()));
	}

	@Test
	public void testDeleteByEntity() {
		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
		long id = sugerencia3.getId();
		sugerenciaDAO.delete(sugerencia3);
		assertNull(sugerenciaDAO.getById(id));
	}

//	@Test
//	public void testCreate() {
//		Cliente clienteModel = new Cliente();
//		clienteModel.setNombre("nombre6");
//		clienteModel.setApellido("apellido6");
//		clienteModel.setDni("123456789");
//		clienteModel.setEmail("email6");
//		clienteModel.setClave("clave6");
//		Sugerencia sugerenciaModel = new Sugerencia("Contenido de prueba", TipoSugerencia.ATENCION, clienteModel);
//		clienteModel.setSugerencias(List.of(sugerenciaModel));
//		ClienteDAO clienteDao = DaoFactory.getClienteDAO();
//		SugerenciaDAO sugerenciaDAO = DaoFactory.getSugerenciaDAO();
//
//		Cliente clienteSaved = clienteDao.create(clienteModel);
//		Sugerencia sugerenciaSaved = sugerenciaDAO.create(sugerenciaModel);
//		System.out.println("DEntro del test create cliente: " + clienteSaved);
//		System.out.println("DEntro del test create sugerencia: " + sugerenciaSaved);
//		assertNotNull(sugerenciaSaved.getId());
//		assertEquals("Contenido de prueba", sugerenciaSaved.getContenido());
//		assertEquals((long) 6, (long) sugerenciaSaved.getCliente().getId());
//		assertEquals(TipoSugerencia.ATENCION, sugerenciaSaved.getTipo());
//	}

//	@Test
//	public void testCreate() {
//	    Cliente clienteModel = new Cliente();
//	    clienteModel.setNombre("nombre2");
//	    clienteModel.setApellido("apellido2");
//	    clienteModel.setDni("123456789");
//	    clienteModel.setEmail("email2");
//	    clienteModel.setClave("clave2");
//
//	    Sugerencia sugerenciaModel = new Sugerencia("Contenido de prueba", TipoSugerencia.ATENCION, clienteModel);
//	    clienteModel.setSugerencias(List.of(sugerenciaModel));
//
//	    // Save the Cliente (this will automatically persist Sugerencia due to Cascade.PERSIST)
//	    ClienteDAO clienteDao = DaoFactory.getClienteDAO();
//	    Cliente clienteSaved = clienteDao.create(clienteModel);
//
//	    // Assert results
//	    assertNotNull(clienteSaved);
//	    Sugerencia sugerenciaSaved = clienteSaved.getSugerencias().get(0);
//	    assertNotNull(sugerenciaSaved.getId());
//	    assertEquals("Contenido de prueba", sugerenciaSaved.getContenido());
//	    assertEquals(TipoSugerencia.ATENCION, sugerenciaSaved.getTipo());
//	    assertEquals(clienteSaved.getId(), sugerenciaSaved.getCliente().getId());
//	}

}
