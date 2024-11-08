package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.EncargadoDAO;
import models.Encargado;
import models.Turno;
import utils.DaoFactory;

public class EncargadoTest {

	private EncargadoDAO encargadoDao;
	private Encargado encargado;

	@BeforeEach
	public void setUp() {
		encargadoDao = DaoFactory.getEncargadoDAO();

		// Configuración inicial para un Cliente de prueba
		encargado = new Encargado();
		encargado.setNombre("TestNombre");
		encargado.setApellido("TestApellido");
		encargado.setDni("123456789");
		encargado.setEmail(UUID.randomUUID().toString());
		encargado.setClave("testClave");
		encargado.setTurno(Turno.MANANA);

		// Persistimos un Cliente inicial para probar operaciones
		encargado = encargadoDao.create(encargado);
	}

	@Test
	public void testGetById() {
		// Obtenemos el cliente usando su ID
		Encargado encargadoEncontrado = encargadoDao.getById(encargado.getId());

		// Comprobamos que los detalles coincidan
		assertNotNull(encargadoEncontrado, "Cliente debería existir");
		assertEquals("TestNombre", encargadoEncontrado.getNombre());
	}

	@Test
	public void testGetAll() {
		// Verificamos que la lista de clientes no esté vacía al menos después de crear
		// uno
		List<Encargado> encargados = encargadoDao.getAll("nombre");
		assertFalse(encargados.isEmpty(), "La lista de clientes no debería estar vacía");
	}

	@Test
	public void testUpdate() {
		// Actualizamos un campo de Cliente y persistimos el cambio
		encargado.setNombre("NombreActualizado");
		Encargado encargadoActualizado = encargadoDao.update(encargado);

		// Verificamos que el cambio haya sido persistido
		assertEquals("NombreActualizado", encargadoActualizado.getNombre());
	}

	@Test
	public void testExists() {
		// Comprobamos si el cliente creado existe
		assertTrue(encargadoDao.exists(encargado.getId()), "Cliente debería existir");

		// Comprobamos con un ID inexistente
		assertFalse(encargadoDao.exists(-1L), "Cliente no debería existir con ID -1");
	}

	@Test
	public void testDeleteById() {
		// Eliminamos el cliente y comprobamos que se haya eliminado
		Encargado encargadoEliminado = encargadoDao.delete(encargado.getId());

		// Validamos que el cliente fue eliminado correctamente
		assertNotNull(encargadoEliminado, "Cliente eliminado debería devolver la entidad eliminada");
		assertFalse(encargadoDao.exists(encargado.getId()), "Cliente debería haber sido eliminado");
	}

	@Test
	public void testDeleteByEntity() {
		// Eliminamos el cliente por la entidad en sí
		encargadoDao.delete(encargado);

		// Verificamos que ya no exista en la base de datos
		assertFalse(encargadoDao.exists(encargado.getId()), "Cliente debería haber sido eliminado");
	}

}
