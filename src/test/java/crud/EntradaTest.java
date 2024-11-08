package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.EntradaDAO;
import models.Entrada;
import utils.DaoFactory;

public class EntradaTest {
	
	private EntradaDAO entradaDao;
    private Entrada entrada;

    @BeforeEach
    public void setUp() {
        entradaDao = DaoFactory.getEntradaDAO();

        // Configuración inicial para una Entrada de prueba
        entrada = new Entrada();
        entrada.setNombre("Entrada Gourmet");
        entrada.setImagen("entrada_gourmet.jpg");
        entrada.setPrecio(12.99f);

        // Persistimos una Entrada inicial para probar operaciones
        entrada = entradaDao.create(entrada);
    }

    @Test
    public void testCreate() {
        // Verificamos que la entrada creada tenga un ID generado
        assertNotNull(entrada.getId(), "El ID de la entrada no debería ser nulo después de crearla");
        assertEquals("Entrada Gourmet", entrada.getNombre());
    }

    @Test
    public void testGetById() {
        // Obtenemos la entrada usando su ID
        Entrada entradaEncontrada = entradaDao.getById(entrada.getId());

        // Comprobamos que los detalles coincidan
        assertNotNull(entradaEncontrada, "Entrada debería existir");
        assertEquals("Entrada Gourmet", entradaEncontrada.getNombre());
    }

    @Test
    public void testGetAll() {
        // Verificamos que la lista de entradas no esté vacía
        List<Entrada> entradas = entradaDao.getAll("nombre");
        assertFalse(entradas.isEmpty(), "La lista de entradas no debería estar vacía");
    }

    @Test
    public void testUpdate() {
        // Actualizamos un campo de Entrada y persistimos el cambio
        entrada.setPrecio(15.99f);
        Entrada entradaActualizada = entradaDao.update(entrada);

        // Verificamos que el cambio haya sido persistido
        assertEquals(15.99f, entradaActualizada.getPrecio());
    }

    @Test
    public void testExists() {
        // Comprobamos si la entrada creada existe
        assertTrue(entradaDao.exists(entrada.getId()), "La entrada debería existir");

        // Comprobamos con un ID inexistente
        assertFalse(entradaDao.exists(-1L), "La entrada no debería existir con ID -1");
    }

    @Test
    public void testDeleteById() {
        // Eliminamos la entrada y comprobamos que se haya eliminado
        Entrada entradaEliminada = entradaDao.delete(entrada.getId());

        // Validamos que la entrada fue eliminada correctamente
        assertNotNull(entradaEliminada, "La entrada eliminada debería devolver la entidad eliminada");
        assertFalse(entradaDao.exists(entrada.getId()), "La entrada debería haber sido eliminada");
    }

    @Test
    public void testDeleteByEntity() {
        // Eliminamos la entrada por la entidad en sí
        entradaDao.delete(entrada);

        // Verificamos que ya no exista en la base de datos
        assertFalse(entradaDao.exists(entrada.getId()), "La entrada debería haber sido eliminada");
    }
}
