package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.upiicsa.segsw.labicla.valueobject.ErrorValueObject;
import mx.ipn.upiicsa.segsw.labicla.valueobject.UserValueObject;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/authenticate.controller")
public class AuthenticateServlet extends HttpServlet implements Servlet 
{
	private static final long serialVersionUID = 1L;
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/la_bicla_db";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doSomething(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doSomething(request, response);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ErrorValueObject error = null;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email==null || email.trim().equals("") || password == null || password.trim().equals(""))
		{
			
			error = new ErrorValueObject();
			
			error.setMessage("Parametro faltante");
			error.setDescription("<h1>Falt&oacute; un parametro obligatorio.</h1>");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);

			return;
		}
		
		if(email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$") == false 
				|| password.matches("^[^-']*$") == false) {
			error = new ErrorValueObject();
			
			error.setMessage("Parametros erroneos");
			error.setDescription("<h1>Formato incorrecto en los parametros.</h1>");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);

			return;
		}

		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		
		System.out.println("AutenticarServlet - SQL - " + sql);

		// Prepara acceso a Base de Datos
		try 
		{
			
			Class.forName(DRIVER); // Carga Driver JDBC en memoria
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);		// Establece conexion con base de datos
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery(); // Ejecuta query en base de datos para Valida que usario y passwors sean correcto
			
			if(rs.next()) // Encontro un registro -- Credenciales validas
			{
				//request.getSession().setAttribute("email", email);
				
				UserValueObject user = new UserValueObject();
				
				user.setEmail(rs.getString("email"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				
				request.getSession().setAttribute("user", user);
				
				RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
				rd.forward(request, response);
				return;				
			}
			else // Las credenciales NO son validas
			{
				error = new ErrorValueObject();
				
				error.setMessage("Credenciales no validas");
				error.setDescription("Las credenciales proporcionadas no son correctas.");
				
				request.setAttribute("error", error);
				
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
					
			error = new ErrorValueObject();
			
			error.setMessage("Ocurrio un error");
			error.setDescription(ex.getMessage());
			error.setException(ex);
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			
			error = new ErrorValueObject();
			
			error.setMessage("Ocurrio un error con la base de datos");
			error.setDescription("El query ejecutado fue [" + sql + "]. " + ex.getMessage());
			error.setException(ex);
			
			request.setAttribute("error", error);

			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
		finally
		{
			try{ if(rs != null) rs.close(); } catch(SQLException exi) { exi.printStackTrace();}
			try{ if(pstmt != null) pstmt.close(); } catch(SQLException exi) { exi.printStackTrace();}
			try{ if(con != null) con.close(); } catch(SQLException exi) { exi.printStackTrace();}
			
			System.out.println("AutenticarServlet - database resources were closed");
		}
	}
}
