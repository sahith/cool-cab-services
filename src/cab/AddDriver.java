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

/**
 * Servlet implementation class AddDriver
 */
public class AddDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDriver() {
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
		String dcity=request.getParameter("cities");
		String dname=request.getParameter("name");
		String dphone=request.getParameter("number");
		String dpass=request.getParameter("pass");
		String dcar =request.getParameter("car");
		String dcname =request.getParameter("carname");
		String dcnumber=request.getParameter("carnumber");
		try
		{
			 //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            //creating connection with the database 
            Connection con=DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
	        String sql;
	        sql="SELECT * FROM adddriver";
	        ResultSet rs=stmt.executeQuery(sql);
	        PreparedStatement ps=con.prepareStatement("insert into adddriver values(?,?,?,?,?,?,?,?,?)");
	        int flag=0;
	        String cities=null;
	        String name=null;
	        String number=null;
	        String pass=null;
	        String car=null;
	        String carname=null;
	        String carnumber=null;
	        String status="free";
	        String id="0";
	        while(rs.next())
	        {
	        	//Retrieve by column name
                name=rs.getString("drivername");
	        	pass=rs.getString("password");
	        	if(dname.equals(name)&&dpass.equals(pass))
	        	{
	        		flag=1;
	        		break;
	        	}
	        }
	        if(flag==0)
	        {
	        	ps.setString(1, dcity);
	        	ps.setString(2, dname);
	        	ps.setString(3, dphone);
	        	ps.setString(4, dpass);
	        	ps.setString(5, dcar);
	        	ps.setString(6, dcname);
	        	ps.setString(7, dcnumber);
	        	ps.setString(8, status);
	        	ps.setString(9, id);
	        	int i=ps.executeUpdate();
	        	if(i>0)
	        	{
	        		RequestDispatcher rd = request.getRequestDispatcher("dsignup.html");
	        		rd.include(request, response);
	        	}
	        }
	        else
	        {
	        		RequestDispatcher rd = request.getRequestDispatcher("ndsignup.html");
	        		rd.include(request, response);
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
