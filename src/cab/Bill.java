package cab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Bill
 */
public class Bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bill() {
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
	        HttpSession session=request.getSession();
            String id=(String) session.getAttribute("key");
            out.println("<html><body>");
	        out.println("<center>");
	        out.println("<h2 style='color:red;'>Billing</h2>");
	        out.println("<a href='driverlogin.html'>Driver Home</a>");
	        out.println("</center>");
	        out.println("<hr>");
	        String name=null;
	        String mobile=null;
	        String gid=null;
	        String city=null;
	        String parea=null;
	        String darea=null;
	        while(rs.next())
	        {
	        	name=rs.getString(2);
	        	mobile=rs.getString(3);
	        	gid=rs.getString(1);
	        	city=rs.getString(4);
	        	parea=rs.getString(6);
	        	darea=rs.getString(7);
	        	if(gid.equals(id))
	        	{
	        		out.println("<form name='myform' method='get' action='Final'");
	        		out.println("<table border='2' cellspacing='3' cellpadding='3'>");
			        out.println("<tr><p style='margin-left:3em;'>");
	 		        out.println("<th>Booking Id&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--</th>");
	 		        out.println("<th>"+gid+"</th><br/>");
	 		        out.println("</tr>");
	 		        out.println("<tr>");
	 		        out.println("<th>Travaller Name&nbsp&nbsp--</th>");
	 		        out.println("<th>"+name+"</th><br/>");
	 		        out.println("</tr>");
	 		        out.println("<tr>");
	 		        out.println("<th>Mobile Number&nbsp&nbsp--</th>");
	 		        out.println("<th>"+mobile+"</th><br/>");
	 		        out.println("</tr>");
	 		        out.println("<tr>");
	 		        out.println("<th>Enter Distance(in Kms)-</th>");
	 		        out.println("<th><input type='number' name='km' placeholder='Distance in Kms' /></th><br/>");
	 		        out.println("</tr>");
	 		        out.println("</table>");
	 		        out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type='submit' value='Generate Bill'/>");
	        	}
	        }
	        out.println("</form>");
	        out.println("</body></html>");
            
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//out.println("<th>City</th>");
		        //out.println("<th>"+city+"</th>");
		        //out.println("<th>Pick Up Area(in</th>");
		        //out.println("<th>"+parea+"</th>");
		        //out.println("<th>Drop Area(in</th>");
		        //out.println("<th>"+darea+"</th>");
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
