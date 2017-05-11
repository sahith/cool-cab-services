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
public class Customerbookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customerbookings() {
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
	        String name=null;
	        String parea=null;
	        String darea=null;
	        String pickdate=null;
	        String cabtype=null;
	        String city=null;
	        String ptime=null;
	        String status=null;
	        String number=null;
	        String id=null;
	        String zid=null;
	        HttpSession session=request.getSession();
            String znumber=(String) session.getAttribute("dnumber");
    	    out.println("<html><body>");
	        out.println("<center>");
	        out.println("<h2 style='color:red;'>Bookings</h2>");
	        out.println("<a href='driverlogin.html'>Driver Home</a>");
	        out.println("</center>");
	        out.println("<hr>");
	        while(rs.next())
	         {
	             //Retrieve by column name	
	             number=rs.getString(3);
	             id=rs.getString(9);
	             cabtype=rs.getString(5);
	             if(number.equals(znumber))
	             {
                     break;
	             }
	         }
	         session.setAttribute("zcabtype",cabtype);
	         session.setAttribute("key", id);
	        if(!(id.equals("0")))
	        {
	        	Statement stmt1 = con.createStatement();
		        String sql1;
		        sql1="SELECT * FROM book";
		        ResultSet rs1 = stmt1.executeQuery(sql1);
		        int flag=0;
		        while(rs1.next())
		        { 
		        	 zid=rs1.getString(1);
		        	 name = rs1.getString(2);
		             number=rs1.getString(3);
		             city = rs1.getString(4);
		             parea = rs1.getString(6);
		             darea=rs1.getString(7);
		             pickdate = rs1.getString(8);
		             ptime = rs1.getString(9);
		             status = rs1.getString(10);
		             if(zid.equals(id))
		             {
		            	out.println("<table border='2' cellspacing='3' cellpadding='3'>");
		 		        out.println("<tr style='color:blue;'>");
		 		        out.println("<th>City</th>");
		 		        out.println("<th>Travaller Name</th>");
		 		        out.println("<th>Pick up Area</th>");
		 		        out.println("<th>Pick up Date</th>");
		 		        out.println("<th>Pick up Time</th>");
		 		        out.println("<th>Drop Area</th>");
		 		        out.println("<th>Generate Bill</th></tr>");
       	                out.println("<tr>");
	                    out.println("<th>"+city+"</th>");
	                    out.println("<th>"+name+"</th>");
	                    out.println("<th>"+parea+"</th>");
	                    out.println("<th>"+pickdate+"</th>");
	                    out.println("<th>"+ptime+"</th>");
	                    out.println("<th>"+darea+"</th>");
	                    out.println("<th><a href='Bill'>Generate Bill</a></th></tr>");
	                    out.println("</table>");
	                    out.println("</body></html>");
	                    flag=1;
	                    break;
		             }
		        }
	        }
	        else
	        {
	        	out.println("<h2>NO Bookings</h2>");
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
