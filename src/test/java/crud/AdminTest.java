package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.AdminDAO;
import models.Admin;
import utils.DaoFactory;

public class AdminTest {
	private AdminDAO adminDao;
	private Admin admin;

	@BeforeEach
	public void setUp() {
		adminDao = DaoFactory.getAdminDAO();

		// Configuración inicial para un Cliente de prueba
		admin = new Admin();

		admin.setDni("123456789");
		admin.setEmail(UUID.randomUUID().toString());
		admin.setClave("testClave");
		// System.out.println(admin);
		// Persistimos un Admin inicial para probar operaciones
		admin = adminDao.create(admin);
	}

	@Test
	public void testGetById() {
		// Obtenemos el cliente usando su ID
		Admin adminEncontrado = adminDao.getById(admin.getId());

		// Comprobamos que los detalles coincidan
		assertNotNull(adminEncontrado, "Admin debería existir");
		assertEquals("testClave", adminEncontrado.getClave());
	}

	@Test
	public void testGetAll() {
		// Verificamos que la lista de clientes no esté vacía al menos después de crear
		// uno
		List<Admin> admins = adminDao.getAll("dni");
		assertFalse(admins.isEmpty(), "La lista de admins no debería estar vacía");
	}

	@Test
	public void testUpdate() {
		// Actualizamos un campo de Cliente y persistimos el cambio
		admin.setDni("987654321");
		Admin clienteActualizado = adminDao.update(admin);

		// Verificamos que el cambio haya sido persistido
		assertEquals("987654321", clienteActualizado.getDni());
	}

	@Test
	public void testExists() {
		// Comprobamos si el cliente creado existe
		assertTrue(adminDao.exists(admin.getId()), "Cliente debería existir");

		// Comprobamos con un ID inexistente
		assertFalse(adminDao.exists(-1L), "Cliente no debería existir con ID -1");
	}

	@Test
	public void testDeleteById() {
		// Eliminamos el cliente y comprobamos que se haya eliminado
		Admin clienteEliminado = adminDao.delete(admin.getId());

		// Validamos que el cliente fue eliminado correctamente
		assertNotNull(clienteEliminado, "Cliente eliminado debería devolver la entidad eliminada");
		assertFalse(adminDao.exists(admin.getId()), "Cliente debería haber sido eliminado");
	}

	@Test
	public void testDeleteByEntity() {
		// Eliminamos el cliente por la entidad en sí
		adminDao.delete(admin);

		// Verificamos que ya no exista en la base de datos
		assertFalse(adminDao.exists(admin.getId()), "Cliente debería haber sido eliminado");
	}
}
