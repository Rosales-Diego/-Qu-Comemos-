package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ClienteDAO;
import models.Cliente;
import utils.DaoFactory;

public class ClienteTest {
	private ClienteDAO clienteDao;
	private Cliente cliente;

	@BeforeEach
	public void setUp() {
		clienteDao = DaoFactory.getClienteDAO();

		// Configuración inicial para un Cliente de prueba
		cliente = new Cliente();
		cliente.setNombre("TestNombre");
		cliente.setApellido("TestApellido");
		cliente.setDni("123456789");
		cliente.setEmail(UUID.randomUUID().toString() + "@email.com");
		cliente.setClave("testClave");

		// Persistimos un Cliente inicial para probar operaciones
		cliente = clienteDao.create(cliente);
	}

	@Test
	public void testGetById() {
		// Obtenemos el cliente usando su ID
		Cliente clienteEncontrado = clienteDao.getById(cliente.getId());

		// Comprobamos que los detalles coincidan
		assertNotNull(clienteEncontrado, "Cliente debería existir");
		assertEquals("TestNombre", clienteEncontrado.getNombre());
	}

	@Test
	public void testGetAll() {
		// Verificamos que la lista de clientes no esté vacía al menos después de crear
		// uno
		List<Cliente> clientes = clienteDao.getAll("nombre");
		assertFalse(clientes.isEmpty(), "La lista de clientes no debería estar vacía");
	}

	@Test
	public void testUpdate() {
		// Actualizamos un campo de Cliente y persistimos el cambio
		cliente.setNombre("NombreActualizado");
		Cliente clienteActualizado = clienteDao.update(cliente);

		// Verificamos que el cambio haya sido persistido
		assertEquals("NombreActualizado", clienteActualizado.getNombre());
	}

	@Test
	public void testExists() {
		// Comprobamos si el cliente creado existe
		assertTrue(clienteDao.exists(cliente.getId()), "Cliente debería existir");

		// Comprobamos con un ID inexistente
		assertFalse(clienteDao.exists(-1L), "Cliente no debería existir con ID -1");
	}

	@Test
	public void testDeleteById() {
		// Eliminamos el cliente y comprobamos que se haya eliminado
		Cliente clienteEliminado = clienteDao.delete(cliente.getId());

		// Validamos que el cliente fue eliminado correctamente
		assertNotNull(clienteEliminado, "Cliente eliminado debería devolver la entidad eliminada");
		assertFalse(clienteDao.exists(cliente.getId()), "Cliente debería haber sido eliminado");
	}

	@Test
	public void testDeleteByEntity() {
		// Eliminamos el cliente por la entidad en sí
		clienteDao.delete(cliente);

		// Verificamos que ya no exista en la base de datos
		assertFalse(clienteDao.exists(cliente.getId()), "Cliente debería haber sido eliminado");
	}
}