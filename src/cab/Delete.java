package cab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
            HttpSession session = request.getSession(true);
            //creating connection with the database 
            Connection con=DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
            String sql;
	        sql="SELECT * FROM adddriver";
	        ResultSet rs=stmt.executeQuery(sql);
	        String dnumber=request.getParameter("number");
	        PreparedStatement ps=con.prepareStatement("delete from adddriver where number ='"+dnumber+"' ");
	        ps.executeUpdate();
	        String snumber=null;
	        int flag=0;
	        while(rs.next())
	        {
	        	snumber=rs.getString(3);
	        	if(snumber.equals(dnumber))
	        	{
	        		flag=1;
	        		break;
	        	}
	        }	 
	        out.println("<center>");
	        out.println("<h2 style='color:red;'>Delete</h2>");
	        out.println("<hr>");
	        out.println("<a href='admin.html'><i>Admin home</i>/a>");
	        out.println("</center>");
	        if(flag==1)
	        {
	            out.println("<h3 style='color:blue;'><i>Row(Driver) has been deleted successfully.</i></h3>");
	        }
	        else 
	        {
	            out.println("<h3 style='color:blue;'><i>No Driver is Present with the Specified Number</i></h3>");
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
