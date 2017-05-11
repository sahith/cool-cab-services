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
public class Final extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Final() {
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
            Statement stmt2 = con.createStatement();
	        String sql;
	        int kms= Integer.parseInt(request.getParameter("km"));
	        HttpSession session=request.getSession();
            String sid=(String) session.getAttribute("key");
	        sql="select * from book";
	        ResultSet rs = stmt.executeQuery(sql);
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
    		rs.absolute(count);
	        rs.updateString("status","completed");
	        rs.updateRow();
	        String sql1;
	        String anumber=(String) session.getAttribute("dnumber");
	        sql1="update adddriver set customerid='0' where number='"+anumber+"'";
	        stmt1.executeUpdate(sql1);
	        String sql2;
	        sql2="select * from cabfares";
	        String cabtype=(String) session.getAttribute("zcabtype");
	        ResultSet rs2 = stmt2.executeQuery(sql2);
	        String ct=null;
	         int pkm = 0;
	        while(rs2.next())
	        {
	        	ct=rs2.getString(1);
	        	pkm=rs2.getInt(2);
	        	if(ct.equals(cabtype))
	        	{
	        		break;
	        	}
	        }
	        int totalcost;
	        int ckm=1;
	        int e,dcost;
	        dcost=pkm*kms;
	        e=kms;
	        totalcost=dcost+e;
	            out.println("<html><body>");
	            out.println("<center>");
	            out.println("<h2 style='color:red;'>Charges Summary</h2>");
	            out.println("<a href='driverlogin.html'>Driver Home</a>");
	            out.println("<hr>");
	            out.println("<h1 style='align:left; color:blue;'>Cab Fare---"+totalcost+"</h1>");
	            out.println("<table border='2' cellspacing='3' cellpadding='3'>");
	            out.println("<tr style='color:#330066;'>");
		        out.println("<th>Cab Type--</th>");
		        out.println("<th>"+cabtype+"</th>");
		        out.println("</tr>");
		        out.println("<tr style='color:#FF3300;'>");
		        out.println("<th>Fare per KM--</th>");
		        out.println("<th>"+pkm+"</th>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<th>Total Distance--</th>");
		        out.println("<th>"+kms+"</th>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<th>Extra charge(per km)--</th>");
		        out.println("<th>"+ckm+"</th>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<th>Fare for Distance--</th>");
		        out.println("<th>"+dcost+"</th><br/>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<th>Fare for extra charges--</th>");
		        out.println("<th>"+e+"</th><br/>");
		        out.println("</tr>");
		        out.println("<tr style='color:#0000CC;'>");
		        out.println("<th>TOTAL BILL</th>");
		        out.println("<th>"+totalcost+"</th>");
		        out.println("</tr>");
		        out.println("</table>");
		        out.println("</center>");
		        out.println("</body></html>"); 
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
