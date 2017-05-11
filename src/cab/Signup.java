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
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() { super(); }

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
		String email=request.getParameter("mail");
		String zpass=request.getParameter("pass");
		String zname=request.getParameter("name");
		String zphone=request.getParameter("number");
		String zcity =request.getParameter("city");
		try
		{
			 //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
           

            //creating connection with the database 
            Connection con=DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
	        String sql;
	        sql="SELECT * FROM signup";
	        ResultSet rs=stmt.executeQuery(sql);
	        PreparedStatement ps=con.prepareStatement("insert into signup values(?,?,?,?,?)");
	        int flag=0;
	        String mail=null;
	        String pass=null;
	        String name=null;
	        String number=null;
	        String city=null;
	        while(rs.next())
	        {
	        	//Retrieve by column name
                name=rs.getString("name");
	        	pass=rs.getString("password");
	        	if(zname.equals(name)&&zpass.equals(pass))
	        	{
	        		flag=1;
	        		break;
	        	}
	        }
	        if(flag==0)
	        {
	        	ps.setString(1, email);
	        	ps.setString(2, zpass);
	        	ps.setString(3, zname);
	        	ps.setString(4, zphone);
	        	ps.setString(5, zcity);
	        	int i=ps.executeUpdate();
	        	if(i>0)
	        	{
	        		RequestDispatcher rd = request.getRequestDispatcher("signedup.html");
	        		rd.include(request, response);
	        	}	
	        }
	        else
        	{
        		RequestDispatcher rd = request.getRequestDispatcher("nsignedup.html");
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
