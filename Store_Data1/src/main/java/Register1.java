

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/Register1")
public class Register1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String fname = request.getParameter("fname");
		String address = request.getParameter("address");
		String country = request.getParameter("country");
		String option = request.getParameter("option");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
			if (option.equals("Register")) {
			PreparedStatement ps = con.prepareStatement("insert into reg values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, fname);
			ps.setString(3, address);
			ps.setString(4, country);
			int i = ps.executeUpdate();
			if(i>0)
			{
				out.print("You are Successfully Register");
			}
			}
			if (option.equals("Update")) {
				PreparedStatement ps=con.prepareStatement("update reg set name=? where address=?");  
				ps.setString(1,name);//1 specifies the first parameter in the query i.e. name  
				ps.setString(2,address); 
				int i = ps.executeUpdate();
				if(i>0)
				{
					out.print("You are Successfully Updated");
				}
				}
			
			if(option.equals("Show"))
				{
				PreparedStatement stmt=con.prepareStatement("select *from reg");  
				ResultSet rs=stmt.executeQuery();  
				while(rs.next()){  
				out.print(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));  
				} 
				}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		
	}

}
