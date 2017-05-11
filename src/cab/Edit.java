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
 * Servlet implementation class Edit
 */
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
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
	        String dnumber=request.getParameter("number");
	        HttpSession session4=request.getSession();
	        session4.setAttribute("knumber", dnumber);
	        out.println("<html><body>");
	        out.println("<center>");
	        out.println("<h2 style='color:red;'>UPDATE</h2> ");
	        out.println("<a href='admin.html'>Admin home</a>");
	        out.println("<hr>");
	        out.println("</center>");
	        out.println("<form name='myform' method='get' action='Update'>");
	        out.println("<table>");
	        String anumber=null;
	        String name=null;
	        String city=null;
	        String cartype=null;
	        String carname=null;
	        String carnumber=null;
	        int flag=0;
	        while(rs.next())
	        {
	        	 city=rs.getString(1);
	             name = rs.getString(2);
	             anumber= rs.getString(3);
	             cartype=rs.getString(5);
	             carname= rs.getString(6);
	             carnumber= rs.getString(7);
	             if(dnumber.equals(anumber))
	             {
	            	out.println("<tr style='color:blue;'>");
	     	        out.println("<th style='color:blue;'>Name</th>");
	     	        out.println("<th><input type='text' name='name' value='"+name+"' maxlength='20'/></th></tr>");
	     	        out.println("<tr style='color:blue;'>");
	     	        out.println("<th style='color:blue;'>City</th>");
	     	        out.println("<th><select name='cities' required>"+
	     	        		 "<option value="+city+">"+city+"</option>"+	        
    "<option value='warangal'>warangal</option>"+
    "<option value='hyderabad'>hyderabad</option>"+
    "<option value='vizag'>vizag</option>"+
    "<option value='bangalore'>bangalore</option>"+
    "<option value='mumbai'>mumbai</option>"+
    "<option value='chennai'>chennai</option>"+
    "<option value='Delhi'>Delhi</option>"+
    "</select></th></tr>");
	     	        out.println("<tr style='color:blue;'>");
	     	        out.println("<th style='color:blue;'>Number</th>");
	     	        out.println("<th><input type='number' name='number' value='"+anumber+"' maxlength='10'/></th></tr>");
	     	        out.println("<tr style='color:blue;'>");
	     	        out.println("<th style='color:blue;'>CarType</th>");
	     	        out.println("<th><select name='cabtype' required>"+
	     	        		"<option value="+cartype+">"+cartype+"</option>"+
    "<option value='Micro'>Micro</option>"+
    "<option value='Mini'>Mini</option>"+
    "<option value='Prime'>Prime</option>"+
    "<option value='Auto'>Auto</option>"+"</select></th></tr>");
	     	        out.println("<tr style='color:blue;'>");
	     	        out.println("<th style='color:blue;'>CarName</th>");
	     	        out.println("<th><input type='text' name='carname' value='"+carname+"' maxlength='20'/></th></tr>");
	     	        out.println("<tr style='color:blue;'>");
	     	        out.println("<th style='color:blue;'>CarNumber</th>");
	     	        out.println("<th><input type='text' name='carnumber' value='"+carnumber+"' maxlength='20'/></th></tr>");
	     	        out.println("<tr><th><p style='margin-left:5em;'></p></th><th><input type='submit' value='Update' /></th></tr>");
	     	        flag=1;
	     	        break;
	             }
	        	
	        }
	        if(flag==0)
	        {
	        	out.println("<h2 style='color:blue;'>Driver with the Specified number is not present</h2>");
	        }
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
