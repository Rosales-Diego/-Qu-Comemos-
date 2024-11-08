package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.BebidaDAO;
import dao.MenuDAO;
import models.Bebida;
import models.Entrada;
import models.Menu;
import models.PlatoPrincipal;
import models.Postre;
import models.TipoMenu;
import utils.DaoFactory;

public class MenuTest {

	private Bebida bebida;
	private static Entrada entrada;
	private static PlatoPrincipal platoPrincipal;
	private Postre postre;
	private static Menu menu;
	private static MenuDAO menuDao = DaoFactory.getMenuDAO();;

	@BeforeAll
	public static void setup() {

		entrada = new Entrada();
		entrada.setNombre("Entrada Gourmet");
		entrada.setImagen("entrada_gourmet.jpg");
		entrada.setPrecio(12.99f);

		platoPrincipal = new PlatoPrincipal();
		platoPrincipal.setNombre("Ensalada César");
		platoPrincipal.setImagen("ensalada_cesar.jpg");
		platoPrincipal.setPrecio(8.99f);

		menu = new Menu();
		menu.setNombre("Menú Ejecutivo");
		menu.setImagen("Imagen del menu");
		menu.setPrecio(10.0f);
		menu.setTipo(TipoMenu.VEGETARIANO);
		menu.setComponentes(Arrays.asList(platoPrincipal, entrada));

		// Persistimos un Menu inicial para probar operaciones
		menu = menuDao.create(menu);
	}

	@Test
	public void testCreate() {
		assertNotNull(menu.getId(), "El ID del menú no debería ser nulo después de crearlo");
		assertEquals("Menú Ejecutivo", menu.getNombre());
		assertEquals(TipoMenu.VEGETARIANO, menu.getTipo());
	}

	@Test
	public void testGetById() {
		Menu menuEncontrado = menuDao.getById(menu.getId());
		assertNotNull(menuEncontrado, "El menú debería existir");
		assertEquals("Menú Ejecutivo", menuEncontrado.getNombre());
		assertEquals(TipoMenu.VEGETARIANO, menuEncontrado.getTipo());
	}

	@Test
	public void testGetAll() {
		List<Menu> menus = menuDao.getAll("nombre");
		assertFalse(menus.isEmpty(), "La lista de menús no debería estar vacía");
	}

	@Test
	public void testUpdate() {
		menu.setPrecio(22.99f);
		Menu menuActualizado = menuDao.update(menu);
		assertEquals(22.99f, menuActualizado.getPrecio());
	}

	@Test
	public void testExists() {
		assertTrue(menuDao.exists(menu.getId()), "El menú debería existir");
		assertFalse(menuDao.exists(-1L), "El menú no debería existir con ID -1");
	}

	@Test
	public void testAddComponent() {
		// Crear y persistir nueva bebida
		Bebida bebida = new Bebida();
		bebida.setNombre("Pepsi");
		bebida.setImagen("pepsi.jpg");
		bebida.setPrecio(2.99f);

		BebidaDAO bebidaDao = DaoFactory.getBebidaDAO();
		bebida = bebidaDao.create(bebida);
		long id_bebida = bebida.getId();
		// Obtener menu actualizado de la base de datos
		Menu menuActual = menuDao.getById(menu.getId());

		// Asegurarse de que la lista sea mutable
		if (menuActual.getComponentes() == null) {
			menuActual.setComponentes(new ArrayList<>());
		} else if (!(menuActual.getComponentes() instanceof ArrayList)) {
			menuActual.setComponentes(new ArrayList<>(menuActual.getComponentes()));
		}

		// Añadir el componente
		menuActual.getComponentes().add(bebida);

		// Actualizar el menu
		menu = menuDao.update(menuActual);
		// Verificar
		Menu menuVerificacion = menuDao.getById(menu.getId());

		assertNotNull(menuVerificacion.getComponentes(), "La lista de componentes no debería ser nula");
		assertEquals(3, menuVerificacion.getComponentes().size(), "El menú debería tener 3 componentes");
		// assertTrue(menuVerificacion.getComponentes().contains(bebida), "El menú
		// debería contener la bebida añadida");
		assertTrue(menuVerificacion.getComponentes().stream().anyMatch(c -> c.getId() == id_bebida),
				"El menú debería contener la bebida añadida");
	}

	@Test
	public void testDeleteById() {
		Bebida bebida = new Bebida();
		bebida.setNombre("Coca Cola");
		bebida.setImagen("coca_cola.jpg");
		bebida.setPrecio(1.50f);
		
		Postre postre = new Postre();
		postre.setNombre("Choco Mousse");
		postre.setImagen("choco_mousse.jpg");
		postre.setPrecio(5.99f);
		
		Menu menu2 = new Menu();
		menu2.setNombre("Menú Economico");
		menu2.setImagen("Imagen del menu");
		menu2.setPrecio(12.0f);
		menu2.setTipo(TipoMenu.NO_VEGETARIANO);
		menu2.setComponentes(Arrays.asList(bebida, postre));

		// Persistimos un Menu inicial para probar operaciones
		menu2 = menuDao.create(menu2);
		
		Menu menuEliminado = menuDao.delete(menu2.getId());
		assertNotNull(menuEliminado, "El menú eliminado debería devolver la entidad eliminada");
		assertFalse(menuDao.exists(menu2.getId()), "El menú debería haber sido eliminado");
	}

	@Test
	public void testDeleteByEntity() {
		Bebida bebida = new Bebida();
		bebida.setNombre("Coca Cola");
		bebida.setImagen("coca_cola.jpg");
		bebida.setPrecio(1.50f);
		
		Postre postre = new Postre();
		postre.setNombre("Choco Mousse");
		postre.setImagen("choco_mousse.jpg");
		postre.setPrecio(5.99f);
		
		Menu menu2 = new Menu();
		menu2.setNombre("Menú Economico");
		menu2.setImagen("Imagen del menu");
		menu2.setPrecio(12.0f);
		menu2.setTipo(TipoMenu.NO_VEGETARIANO);
		menu2.setComponentes(Arrays.asList(bebida, postre));

		// Persistimos un Menu inicial para probar operaciones
		menu2 = menuDao.create(menu2);
		
		menuDao.delete(menu2);
		assertFalse(menuDao.exists(menu2.getId()), "El menú debería haber sido eliminado");
	}
}