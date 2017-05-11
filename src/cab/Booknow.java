package cab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Signup
 */
public class Booknow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booknow() { super(); }

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
		String city=request.getParameter("cities");
		String ucab=request.getParameter("cab");
		String upick=request.getParameter("pick");
		String udrop=request.getParameter("drop");
		String udate =request.getParameter("date");
		String utime =request.getParameter("time");
		try
		{
			 //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
           

            //creating connection with the database 
            Connection con=DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
	        String sql;
	        sql="SELECT * FROM book";
	        ResultSet rs=stmt.executeQuery(sql);
	        HttpSession session=request.getSession();
            String name=(String)session.getAttribute("name");
            String number=(String)session.getAttribute("snumber");
            String status="pending";
	        PreparedStatement ps=con.prepareStatement("insert into book(Customername,number,city,Cabtype,PickupArea,DropArea,PickupDate,PickupTime,status) values(?,?,?,?,?,?,?,?,?)");
	        ps.setString(1, name);
	        ps.setString(2, number);
	        ps.setString(3, city);
	        ps.setString(4, ucab);
	        ps.setString(5, upick);
	        ps.setString(6, udrop);
	        ps.setString(7, udate);
	        ps.setString(8, utime);
	        ps.setString(9, status);
	        int i=ps.executeUpdate();
	        	if(i>0)
	        	{
	        		RequestDispatcher rd = request.getRequestDispatcher("bookingdetails.html");
		        	rd.include(request, response);
		        	out.println("<center");
		        	if(ucab.equals("Micro"))
		        	{
		        		out.println("<p align='right; style='color:red;'>Charge per KM("+ucab+")--Rs 10 per km</p>");
		        	}
		        	else if(ucab.equals("Mini"))
		        	{
		        		out.println("<p align='right; style='color:red;'>Charge per KM("+ucab+")--Rs 6 per km</p>");
		        	}
		        	else if(ucab.equals("Auto"))
		        	{
		        		out.println("<p align='right; style='color:red;'>Charge per KM("+ucab+")--Rs 12 per km</p>");
		        	}
		        	else
		        	{
		        		out.println("<p align='right; style='color:red;'>Charge per KM("+ucab+")--Rs 14 per km</p>");
		        	}
		        	out.println("</center>");
		        	out.println("<form name='myform' action='bcompleted.html' method='get'");
		        	out.println("<table>");
		        	out.println("<tr><th><p style='color:blue;'>PICK Up Area&nbsp  --"+ upick + "</p></th></tr>");
		        	out.println("<tr><th><p style='color:blue;'>DROP Area&nbsp     --"+ udrop + "</p></th></tr>");
		        	out.println("<tr><th><p style='color:blue;'>Pick Up Date&nbsp  --"+ udate + "</p></th></tr>");
		        	out.println("<tr><th><p style='color:blue;'>Pick Up Time&nbsp  --"+ utime + "</p></th></tr>");
		        	out.println("<tr><th><p style='color:blue;'>CAB TYPE    &nbsp  --"+ ucab + "</p></th></tr>");
		        	out.println("<tr><th><p style='color:blue;'>Traveller Name&nbsp--"+ name  + "</p></th></tr>");
		        	out.println("<tr><th><p style='margin-left:4em;'</p><input type='submit' value='Confirm Booking' /</th></tr>");
		        	out.println("</table></form>");
	        	}	
	      
	      // Clean-up environment
	         rs.close();
	         stmt.close();
	         con.close();
		}
		catch(Exception e)
		{
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
