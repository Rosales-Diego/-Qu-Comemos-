package misServlets;

import java.io.IOException;

import dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;
import utils.DaoFactory;

/**
 * Servlet implementation class PruebaSV
 */
@WebServlet("/PruebaSV")
public class PruebaSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PruebaSV() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		//System.out.println("mensajeeee1");
//		Sugerencia su = new Sugerencia("Contenido de prueba", TipoSugerencia.ATENCION, (long) 123);
//
//		SugerenciaDAO s = DaoFactory.getSugerenciaDAO();
//		s.create(su);

//		Cliente admin = new Cliente();
//		admin.setEmail("admin@email.com");
//		admin.setDni("987654321");
//		admin.setClave("clave123");
//		admin.setNombre("cliente1");
//		admin.setApellido("apellido1");
//
//		ClienteDAO u = DaoFactory.getClienteDAO();
//		u.create(admin);
//		System.out.println("mensajeeee");

//		try {
//			Connection conn = MiDataSource.getDataSource().getConnection();
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery("Select * from prueba;");
//			while (rs.next()) {
//				System.out.println(rs.getString(1) + "-" + rs.getString(2));
//			}
//			rs.close();
//			st.close();
//			conn.close(); // en gral. devuelve la conexión al pool
//		} catch (SQLException e) {
//			System.out.println("Error de SQL" + e.getMessage());
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
