package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BebidaDAO;
import models.Bebida;
import utils.DaoFactory;

public class BebidaTest {
	
	private BebidaDAO bebidaDao;
    private Bebida bebida;

    @BeforeEach
    public void setUp() {
        bebidaDao = DaoFactory.getBebidaDAO();

        // Configuración inicial para una Bebida de prueba
        bebida = new Bebida();
        bebida.setNombre("Coca Cola");
        bebida.setImagen("coca_cola.jpg");
        bebida.setPrecio(1.50f);

        // Persistimos una Bebida inicial para probar operaciones
        bebida = bebidaDao.create(bebida);
    }

    @Test
    public void testCreate() {
        // Verificamos que la bebida creada tenga un ID generado
        assertNotNull(bebida.getId(), "El ID de la bebida no debería ser nulo después de crearla");
        assertEquals("Coca Cola", bebida.getNombre());
    }

    @Test
    public void testGetById() {
        // Obtenemos la bebida usando su ID
        Bebida bebidaEncontrada = bebidaDao.getById(bebida.getId());

        // Comprobamos que los detalles coincidan
        assertNotNull(bebidaEncontrada, "Bebida debería existir");
        assertEquals("Coca Cola", bebidaEncontrada.getNombre());
    }

    @Test
    public void testGetAll() {
        // Verificamos que la lista de bebidas no esté vacía
        List<Bebida> bebidas = bebidaDao.getAll("nombre");
        assertFalse(bebidas.isEmpty(), "La lista de bebidas no debería estar vacía");
    }

    @Test
    public void testUpdate() {
        // Actualizamos un campo de Bebida y persistimos el cambio
        bebida.setPrecio(2.00f);
        Bebida bebidaActualizada = bebidaDao.update(bebida);

        // Verificamos que el cambio haya sido persistido
        assertEquals(2.00f, bebidaActualizada.getPrecio());
    }

    @Test
    public void testExists() {
        // Comprobamos si la bebida creada existe
        assertTrue(bebidaDao.exists(bebida.getId()), "La bebida debería existir");

        // Comprobamos con un ID inexistente
        assertFalse(bebidaDao.exists(-1L), "La bebida no debería existir con ID -1");
    }

    @Test
    public void testDeleteById() {
        // Eliminamos la bebida y comprobamos que se haya eliminado
        Bebida bebidaEliminada = bebidaDao.delete(bebida.getId());

        // Validamos que la bebida fue eliminada correctamente
        assertNotNull(bebidaEliminada, "La bebida eliminada debería devolver la entidad eliminada");
        assertFalse(bebidaDao.exists(bebida.getId()), "La bebida debería haber sido eliminada");
    }

    @Test
    public void testDeleteByEntity() {
        // Eliminamos la bebida por la entidad en sí
        bebidaDao.delete(bebida);

        // Verificamos que ya no exista en la base de datos
        assertFalse(bebidaDao.exists(bebida.getId()), "La bebida debería haber sido eliminada");
    }

}
