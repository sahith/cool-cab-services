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
 * Servlet implementation class Assignedcab
 */
public class Assignedcab extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assignedcab() {
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
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            Statement stmt1 = con.createStatement();
	        String sql;
	        HttpSession session2=request.getSession();
	        String number=(String) session2.getAttribute("number");
	        String date=(String) session2.getAttribute("udate");
	        String acity=(String) session2.getAttribute("ucity");
	        String sid=(String) session2.getAttribute("cid");
	        sql="select * from book";
	        ResultSet rs = stmt.executeQuery(sql);
	        String city=null;
	        String zdate=null;
	        String znumber=null;
	        String id=null;
	        int count=0;
	        while(rs.next())
	        {
	        	id=rs.getString(1);
	        	count=count+1;
	        	if(id.equals(sid))
	        	{
	        		break;
	        	}
	        }
	        RequestDispatcher rd = request.getRequestDispatcher("assigned.html");
    		rd.include(request, response);
    		rs.absolute(count);
	        rs.updateString("status","dassign");
	        rs.updateRow();
	        String sql1;
	        HttpSession session1=request.getSession();
	        String dnumber=(String) session1.getAttribute("adnumber");
	        sql1="update adddriver set customerid='"+id+"' where number='"+dnumber+"'";
	        stmt1.executeUpdate(sql1);
	        
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
