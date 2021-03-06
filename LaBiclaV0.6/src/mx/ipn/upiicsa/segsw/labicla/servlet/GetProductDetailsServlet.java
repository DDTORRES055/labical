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
import mx.ipn.upiicsa.segsw.labicla.valueobject.ProductValueObject;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/get_product_details.controller")
public class GetProductDetailsServlet extends HttpServlet implements Servlet 
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
		
		String id = request.getParameter("id");
		
		String sql = "SELECT * FROM products WHERE id = ?";
		
		System.out.println(sql);
		
		try {
			Class.forName(DRIVER);
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			ProductValueObject product = null;

			if(rs.next())
			{
				product = new ProductValueObject();
				
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setImage(rs.getString("image"));
			}

			request.setAttribute("product", product);
			
			RequestDispatcher rd = request.getRequestDispatcher("product_details.jsp");
			rd.forward(request, response);
			return;
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
			
		}
	}
}
