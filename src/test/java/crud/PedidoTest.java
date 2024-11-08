package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ClienteDAO;
import dao.EntradaDAO;
import dao.PedidoDAO;
import dao.PostreDAO;
import models.Cliente;
import models.Entrada;
import models.EstadoPedido;
import models.Pedido;
import models.Postre;
import utils.DaoFactory;

public class PedidoTest {
	
	private static PedidoDAO pedidoDAO;
	private static ClienteDAO clienteDAO;
	private static EntradaDAO entradaDAO;
	private static PostreDAO postreDAO;
    private static Cliente cliente;
    private static Entrada producto1;
    private static Postre producto2;

    @BeforeAll
    public static void setup() {
        // Inicializa el DAO
        pedidoDAO = DaoFactory.getPedidoDAO();
        clienteDAO = DaoFactory.getClienteDAO();
        entradaDAO = DaoFactory.getEntradaDAO();
        postreDAO = DaoFactory.getPostreDAO();
        // Crear cliente
        cliente = new Cliente("Juan", "Pérez");
        cliente.setEmail("homero@email.com");
        cliente.setDni("7777777");
        cliente.setClave("abc");
        // Persistir cliente
        clienteDAO.create(cliente);

        // Crear productos
        producto1 = new Entrada();
        producto1.setNombre("Producto 1");
        producto1.setPrecio(100.0f);
        producto2 = new Postre();
        producto2.setNombre("Producto 2");
        producto2.setPrecio(50.0f);
        
        // Persistir productos
        entradaDAO.create(producto1);
        postreDAO.create(producto2);
    }

    @Test
    public void testCrearPedido() {
        // Crear pedido
        Pedido pedido = new Pedido();
        pedido.setCodigo("PED123");
        pedido.setClientePedido(cliente);
        pedido.setProductos(Arrays.asList(producto1, producto2));
        
        // Persistir pedido
        pedidoDAO.create(pedido);
        
        // Verificar que el pedido fue guardado
        Pedido pedidoGuardado = pedidoDAO.getById(pedido.getId());
        assertNotNull(pedidoGuardado);
        assertEquals("PED123", pedidoGuardado.getCodigo());
        assertEquals(2, pedidoGuardado.getProductos().size());
        assertEquals(cliente.getDni(), pedidoGuardado.getClientePedido().getDni());
    }

    @Test
    public void testCalcularMontoTotal() {
        // Crear y persistir pedido con productos
        Pedido pedido = new Pedido();
        pedido.setCodigo("PED456");
        pedido.setClientePedido(cliente);
        pedido.setProductos(Arrays.asList(producto1, producto2));
        System.out.println("ANTES DEL CREATE PEDIDO: " + pedido);
        // Persistir pedido
        Pedido aux = pedidoDAO.create(pedido);
        System.out.println("DESPUES DEL CREATE PEDIDO: " + aux);
        // Verificar monto total del pedido
        float montoTotal = pedido.getMonto();
        assertEquals(150.0f, montoTotal);
    }

    @Test
    public void testActualizarEstadoPedido() {
        // Crear y persistir pedido
        Pedido pedido = new Pedido();
        pedido.setCodigo("PED789");
        pedido.setClientePedido(cliente);
        pedidoDAO.create(pedido);

        // Actualizar estado del pedido
        pedido.setEstado(EstadoPedido.CANCELADO);
        pedidoDAO.update(pedido);
        
        // Verificar que el estado del pedido fue actualizado
        Pedido pedidoActualizado = pedidoDAO.getById(pedido.getId());
        assertEquals(EstadoPedido.CANCELADO, pedidoActualizado.getEstado());
    }

    @Test
    public void testEliminarPedido() {
        // Crear y persistir pedido
        Pedido pedido = new Pedido();
        pedido.setCodigo("PED999");
        pedido.setClientePedido(cliente);
        pedidoDAO.create(pedido);

        // Eliminar pedido
        pedidoDAO.delete(pedido);

        // Verificar que el pedido fue eliminado
        Pedido pedidoEliminado = pedidoDAO.getById(pedido.getId());
        assertNull(pedidoEliminado);
    }
}
