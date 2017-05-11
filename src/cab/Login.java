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
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	        sql="SELECT * FROM signup";
	        ResultSet rs = stmt.executeQuery(sql);
	        String mnumber=request.getParameter("number");
	        String mpass=request.getParameter("pass");
	        int flag=0;
	        String number=null;
	        String pass=null;
	        String mname=null;
	        String mcity=null;
	        while(rs.next())
	         {
	             //Retrieve by column name
	        	
	             number = rs.getString("number");
	             pass = rs.getString("password");
	             mname=rs.getString(3);
	             if(mnumber.equals(number)&&mpass.equals(pass))
	             {
	            	 flag=1;
	            	 break;
	             }
	         }
	         if(flag==1)
	         {
	        	    HttpSession session=request.getSession();
	        	    session.setAttribute("name",mname);
	        	    session.setAttribute("snumber", mnumber);
	        	    out.println( "<h3>Hi!&nbsp;&nbsp;&nbsp;" + mname +"</h3>");
	        	    RequestDispatcher rd = request.getRequestDispatcher("user.html");
	        	    rd.include(request, response);
	         }
	         else
	         {
	        	 String docType =
	        		      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	        		      "transitional//en\">\n";
	        	  out.println(docType +
	                      "<html>\n" +
	                      "<body"+
	                      "<p align='center' style='Color:#660099;'> <strong> Invalid Credentials!!! </center></strong></p>"+
	        			  "</body></html>");
	             RequestDispatcher rd = request.getRequestDispatcher("book.html");
	             rd.include(request, response);
	         }
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
