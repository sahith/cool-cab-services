package cab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Viewreports extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Viewreports() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String url="jdbc:mysql://localhost/coolcabs";
	String driver="com.mysql.jdbc.Driver";
	String user="root";
	String password="root";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		try
		{
			 //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
           

            //creating connection with the database 
            Connection con=DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
	        String sql;
	        sql="SELECT * FROM report";
	        ResultSet rs = stmt.executeQuery(sql);
	        String dname=null;
	        String report=null;
	        RequestDispatcher rd = request.getRequestDispatcher("vreport.html");
   	        rd.include(request, response);
	        out.println("<html><body>");
	        out.println("<center>");
	        out.println("<table border='2' cellspacing='2' cellpadding='2'>");
	        out.println("<tr style='color:blue;'>");
	        out.println("<th>Driver Name</th>");
	        out.println("<th>Report</th>");
	        out.println("</tr>");
	        while(rs.next())
	         {
	             //Retrieve by column name	
	             dname = rs.getString("drivername");
	             report = rs.getString("Report");
	            out.println("<tr>");
	 	        out.println("<th>"+dname+"</th>");
	 	        out.println("<th>"+report+"</th>");
	 	        out.println("</tr>");
	         }
	        out.println("</table>");
	        out.println("</center>");
	         out.println("</body></html>");
	         // Clean-up environment
	         rs.close();
	         stmt.close();
	         con.close();
		}
		catch(Exception e)
	     {
	         //Handle errors for Class.forName
	         e.printStackTrace();
	     }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
