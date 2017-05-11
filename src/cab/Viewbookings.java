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
public class Viewbookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Viewbookings() {
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
	        sql="SELECT * FROM book";
	        ResultSet rs = stmt.executeQuery(sql);
	        String acity=request.getParameter("cities");
	        String astatus=request.getParameter("status");
	        String date=request.getParameter("date");
	        String name=null;
	        String parea=null;
	        String cabtype=null;
	        String pickdate=null;
	        String city=null;
	        String ptime=null;
	        String status=null;
	        String number=null;
	        HttpSession session2=request.getSession();
	        session2.setAttribute("udate", date);
	        session2.setAttribute("ucity", acity);
	        //RequestDispatcher rd = request.getRequestDispatcher("mybooking.html");
    	    //rd.include(request, response);
    	    out.println("<html><body>");
    	    out.println("<h2>Bookings</h2><br/>");
    	    out.println("<center>");
    	    out.println("<a href=admin.html>Admin Home</a>");
	        out.println("<hr>");
	        out.println("</center>");
    	    out.println("<form name='myform' method='get' action='Assigncab'>");
    	    out.println("<table>");
    	    out.println("<tr style='color:blue;'>");
	        out.println("<th><input type='text' name='number' placeholder='number' maxlength='10' /></th>");
	        out.println("<th><input type='submit' value='Assign cab'/></th></tr></table></form>");
	        out.println("<center>");
	        out.println("<table border='2' cellspacing='3' cellpadding='3'>");
	        out.println("<tr style='color:blue;'>");
	        out.println("<th>City</th>");
	        out.println("<th>Name</th>");
	        out.println("<th>Number</th>");
	        out.println("<th>Pick up Area</th>");
	        out.println("<th>Pick up Date</th>");
	        out.println("<th>Pick up Time</th>");
	        out.println("<th>Cab Type</th>");
	        out.println("<th>Status</th>");
	        int flag=0;
	        while(rs.next())
	         {
	             //Retrieve by column name	
	             name = rs.getString(2);
	             number=rs.getString(3);
	             city = rs.getString(4);
	             cabtype=rs.getString(5);
	             parea = rs.getString(6);
	             pickdate = rs.getString(8);
	             ptime = rs.getString(9);
	             status = rs.getString(10);
	             if(city.equals(acity)&&status.equals(astatus)&&pickdate.equals(date))
	             {
	            	 out.println("<tr>");
	     	        out.println("<th>"+city+"</th>");
	     	        out.println("<th>"+name+"</th>");
	     	        out.println("<th>"+number+"</th>");
	     	        out.println("<th>"+parea+"</th>");
	     	        out.println("<th>"+pickdate+"</th>");
	     	        out.println("<th>"+ptime+"</th>");
	     	        out.println("<th>"+cabtype+"</th>");
	     	        out.println("<th>"+status+"</th>"); 
	     	        flag=1;
	             }
	         }
	          out.println("</table></center>");
	          out.println("</body></html>");
	          if(flag==0)
	          {
	        	  out.println("<h3>No Bookings</h3>");
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
