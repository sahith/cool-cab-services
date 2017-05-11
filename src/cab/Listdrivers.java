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
public class Listdrivers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listdrivers() {
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
            int flag=0;
	        String name=null;
	        String city=null;
	        String number=null;
	        String cartype=null;
	        String carname=null;
	        String carnumber=null;
	        RequestDispatcher rd = request.getRequestDispatcher("listdrivers.html");
   	        rd.include(request, response);
   	        out.println("<html><body>");
 	        out.println("<form name='myform' method='get' action='Delete'>");
 	        out.println("<h4 style='color:blue;'>Enter Driver Number(present) to Delete</h4>");
	        out.println("<input type='text' name='number' placeholder='Driver number' maxlength='10'/>");
	        out.println("<input type='submit' value='Delete'/></form>");
	        out.println("<form name='form' method='get' action='Edit'>");
 	        out.println("<h4 style='color:blue;'>Enter Driver Number(present) to Update(Edit)</h4>");
	        out.println("<input type='text' name='number' placeholder='Driver number' maxlength='10'/>");
	        out.println("<input type='submit' value='Edit'/></form>");
	        out.println("<center>");
	        out.println("<table border='2' cellspacing='3' cellpadding='3'>");
	        out.println("<tr style='color:blue;'>");
	        out.println("<th style='color:blue;'>Name</th>");
	        out.println("<th style='color:blue;'>City</th>");
	        out.println("<th style='color:blue;'>Number</th>");
	        out.println("<th style='color:blue;'>CarType</th>");
	        out.println("<th style='color:blue;'>CarName</th>");
	        out.println("<th style='color:blue;'>CarNumber</th>");
	        while(rs.next())
	         {
	             //Retrieve by column name
	        	flag=0;
	        	 city=rs.getString(1);
	             name = rs.getString(2);
	             number= rs.getString(3);
	             cartype=rs.getString(5);
	             carname= rs.getString(6);
	             carnumber= rs.getString(7);
	             if(name.equals("Admin")&&number.equals("9876543210"))
	             {
	            	 flag=1;
	             }
	             if(flag==0)
	             {
	            	   HttpSession session=request.getSession(true);
	            	out.println("<tr>");
	     	        out.println("<th>"+name+"</th>");
	     	        out.println("<th>"+city+"</th>");
	     	        out.println("<th>"+number+"</th>");
	     	        out.println("<th>"+cartype+"</th>");
	     	        out.println("<th>"+carname+"</th>");
	     	        out.println("<th>"+carnumber+"</th>"); 
	             }
	         }
	          out.println("</table></center>");
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
