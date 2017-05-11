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
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
	        HttpSession session4=request.getSession();
	        String number=(String) session4.getAttribute("knumber");
	        String mnumber=request.getParameter("number");
	        String mcname=request.getParameter("carname");
	        String mcity=request.getParameter("cities");
	        String mcnumber=request.getParameter("carnumber");
	        String mctype=request.getParameter("cabtype");
	        String mname=request.getParameter("name");
	        sql="update adddriver set city='"+mcity+"',drivername='"+mname+"',number='"+mnumber+"',CarNumber='"+mcnumber+"',CarType='"+mctype+"',CarName='"+mcname+"'  where number='"+number+"'";
	        stmt.executeUpdate(sql);
	        out.println("<html><body>");
	        out.println("<center>");
	        out.println("<h2 style='color:red;'>UPDATED</h2> ");
	        out.println("<a href='admin.html'><i>Admin home</i></a>");
	        out.println("<hr>");
	        out.println("</center>");
	        out.println("<h2 style='color:blue;'><i>Driver details have been Updated</i></h2>");
            out.println("</body></html>");
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
