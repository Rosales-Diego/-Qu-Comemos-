package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.PostreDAO;
import models.Postre;
import utils.DaoFactory;

public class PostreTest {
	
	private PostreDAO postreDao;
    private Postre postre;

    @BeforeEach
    public void setUp() {
        postreDao = DaoFactory.getPostreDAO();

        // Configuración inicial para un Postre de prueba
        postre = new Postre();
        postre.setNombre("Choco Mousse");
        postre.setImagen("choco_mousse.jpg");
        postre.setPrecio(5.99f);

        // Persistimos un Postre inicial para probar operaciones
        postre = postreDao.create(postre);
    }

    @Test
    public void testCreate() {
        assertNotNull(postre.getId(), "El ID del postre no debería ser nulo después de crearla");
        assertEquals("Choco Mousse", postre.getNombre());
    }

    @Test
    public void testGetById() {
        Postre postreEncontrado = postreDao.getById(postre.getId());
        assertNotNull(postreEncontrado, "El postre debería existir");
        assertEquals("Choco Mousse", postreEncontrado.getNombre());
    }

    @Test
    public void testGetAll() {
        List<Postre> postres = postreDao.getAll("nombre");
        assertFalse(postres.isEmpty(), "La lista de postres no debería estar vacía");
    }

    @Test
    public void testUpdate() {
        postre.setPrecio(6.99f);
        Postre postreActualizado = postreDao.update(postre);
        assertEquals(6.99f, postreActualizado.getPrecio());
    }

    @Test
    public void testExists() {
        assertTrue(postreDao.exists(postre.getId()), "El postre debería existir");
        assertFalse(postreDao.exists(-1L), "El postre no debería existir con ID -1");
    }

    @Test
    public void testDeleteById() {
        Postre postreEliminado = postreDao.delete(postre.getId());
        assertNotNull(postreEliminado, "El postre eliminado debería devolver la entidad eliminada");
        assertFalse(postreDao.exists(postre.getId()), "El postre debería haber sido eliminado");
    }

    @Test
    public void testDeleteByEntity() {
        postreDao.delete(postre);
        assertFalse(postreDao.exists(postre.getId()), "El postre debería haber sido eliminado");
    }
}
