package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.PlatoPrincipalDAO;
import models.PlatoPrincipal;
import utils.DaoFactory;

public class PlatoPrincipalTest {
	
	private PlatoPrincipalDAO platoPrincipalDao;
    private PlatoPrincipal platoPrincipal;

    @BeforeEach
    public void setUp() {
        platoPrincipalDao = DaoFactory.getPlatoPrincipalDAO();

        // Configuración inicial para una Comida de prueba
        platoPrincipal = new PlatoPrincipal();
        platoPrincipal.setNombre("Ensalada César");
        platoPrincipal.setImagen("ensalada_cesar.jpg");
        platoPrincipal.setPrecio(8.99f);

        // Persistimos una Comida inicial para probar operaciones
        platoPrincipal = platoPrincipalDao.create(platoPrincipal);
    }

    @Test
    public void testCreate() {
        assertNotNull(platoPrincipal.getId(), "El ID de la comida no debería ser nulo después de crearla");
        assertEquals("Ensalada César", platoPrincipal.getNombre());
    }

    @Test
    public void testGetById() {
        PlatoPrincipal platoPrincipalEncontrado = platoPrincipalDao.getById(platoPrincipal.getId());
        assertNotNull(platoPrincipalEncontrado, "La comida debería existir");
        assertEquals("Ensalada César", platoPrincipalEncontrado.getNombre());
    }

    @Test
    public void testGetAll() {
        List<PlatoPrincipal> comidas = platoPrincipalDao.getAll("nombre");
        assertFalse(comidas.isEmpty(), "La lista de comidas no debería estar vacía");
    }

    @Test
    public void testUpdate() {
        platoPrincipal.setPrecio(9.99f);
        PlatoPrincipal comidaActualizada = platoPrincipalDao.update(platoPrincipal);
        assertEquals(9.99f, comidaActualizada.getPrecio());
    }

    @Test
    public void testExists() {
        assertTrue(platoPrincipalDao.exists(platoPrincipal.getId()), "La comida debería existir");
        assertFalse(platoPrincipalDao.exists(-1L), "La comida no debería existir con ID -1");
    }

    @Test
    public void testDeleteById() {
    	PlatoPrincipal comidaEliminada = platoPrincipalDao.delete(platoPrincipal.getId());
        assertNotNull(comidaEliminada, "La comida eliminada debería devolver la entidad eliminada");
        assertFalse(platoPrincipalDao.exists(platoPrincipal.getId()), "La comida debería haber sido eliminada");
    }

    @Test
    public void testDeleteByEntity() {
    	platoPrincipalDao.delete(platoPrincipal);
        assertFalse(platoPrincipalDao.exists(platoPrincipal.getId()), "La comida debería haber sido eliminada");
    }
}
