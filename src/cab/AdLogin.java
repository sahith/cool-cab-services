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
public class AdLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdLogin() {
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
	        sql="SELECT * FROM adddriver";
	        ResultSet rs = stmt.executeQuery(sql);
	        String adnumber=request.getParameter("number");
	        String adpass=request.getParameter("pass");
	        int flag=0;
	        String number=null;
	        String pass=null;
	        String adname=null;
	        while(rs.next())
	         {
	             //Retrieve by column name
	        	
	             number = rs.getString("number");
	             pass = rs.getString("password");
	             adname=rs.getString(2);
	             if(adnumber.equals(number)&&adpass.equals(pass))
	             {
	            	 flag=1;
	            	 break;
	             }
	         }
	         if(flag==1)
	         {
	        	 if(number.equals("9876543210")&&pass.equals("coolcabs"))
	        	 {
	        		 HttpSession session=request.getSession();
		        	 session.setAttribute("name",adname);
	        		 RequestDispatcher rd = request.getRequestDispatcher("admin.html");
	 	        	 rd.include(request, response);
	        	 }
	        	 else
	        	 {
	        	    HttpSession session=request.getSession();
	        	    session.setAttribute("dnumber",number);
	        	    String bnumber=null;
	        	    String sname=null;
	        	    ResultSet rs1 = stmt.executeQuery(sql);
	        	    while(rs1.next())
	        	    {
	        	    	bnumber=rs1.getString(3);
	        	    	sname=rs1.getString(2);
	        	    	if(bnumber.equals(adnumber))
	        	    	{
	        	    		break;
	        	    	}
	        	    }
	        	    session.setAttribute("name", sname);
	        	    out.println( "<h3>Welcome&nbsp;&nbsp;&nbsp;" + adname +"</h3>");
	        	    RequestDispatcher rd = request.getRequestDispatcher("driverlogin.html");
	        	    rd.include(request, response);
	              }
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
	             RequestDispatcher rd = request.getRequestDispatcher("login.html");
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
