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

import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class Login
 */
public class Assigncab extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assigncab() {
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
	        String unumber=request.getParameter("number");
	        HttpSession session2=request.getSession();
	        session2.setAttribute("number", unumber);
	        String date=(String) session2.getAttribute("udate");
	        String acity=(String) session2.getAttribute("ucity");
	        session2.setAttribute("udate", date);
	        session2.setAttribute("ucity", acity);
	        String cabtype=null;
	        String ucity=null;
	        String number=null;
	        String status="free";
	        String udate=null;
	        String id=null;
	        String mstatus=null;
	        int flag=0;
	        int flag1=0;
	        while(rs.next())
	         {
	             //Retrieve by column name	
	        	id=rs.getString(1);
	             number=rs.getString(3);
	             cabtype=rs.getString(5);
	             ucity=rs.getString(4);
	             udate=rs.getString(8);
	             mstatus=rs.getString(10);
	             if(number.equals(unumber)&&date.equals(udate)&&acity.equals(ucity))
	             {
	            	 flag1=1;
	            	 break;
	             }
	         }
	        session2.setAttribute("cid", id);
	        Statement stmt1 = con.createStatement();
	        String sql1;
	        sql1="SELECT * FROM adddriver";
	        ResultSet rs1 = stmt.executeQuery(sql1);
	        out.println("<html><body>");
    	    out.println("<h2>Booking Summary</h2>");
	        out.println("<center>");
	        out.println("<a href=admin.html>Admin Home</a>");
	        out.println("</center>");
	        out.println("<hr>");
	        out.println("<form name='myform' method='get' action='Assignedcab'>");
	        out.println("<table>");
	        String dcabtype=null;
	        String dcity=null;
	        String dstatus=null;
	        String dname=null;
	        String dcarname=null;
	        String dcarnumber=null;
	        String dnumber=null;
	        String sid;
	        flag=0;
	        if(mstatus.equals("pending")&&flag1==1)
	        {  	
	         while(rs1.next())
	         { 
		          	sid=rs1.getString(9);
	        	dnumber=rs1.getString(3);
	        	dcarname=rs1.getString(6);
	        	dcarnumber=rs1.getString(7);
	        	dname=rs1.getString(2);
	        	dcabtype=rs1.getString(5);
	        	dcity=rs1.getString(1);
	        	dstatus=rs1.getString(8);
	        	if(cabtype.equals(dcabtype)&&ucity.equals(dcity)&&sid.equals("0"))
	        	{
	        		HttpSession session1=request.getSession();
	    	        session1.setAttribute("adnumber", dnumber);
	        		out.println("<tr style='color:blue;'>");
	        		out.println("<th>Driver name--<th>");
	        		out.println("<th>"+dname+"<th></tr>");
	        		out.println("<tr style='color:blue;'>");
	        		out.println("<th>Driver number--<th>");
	        		out.println("<th>"+dnumber+"<th></tr>");
	        		out.println("<tr style='color:blue;'>");
	        		out.println("<th>City--<th>");
	        		out.println("<th>"+dcity+"<th></tr>");
	        		out.println("<tr style='color:blue;'>");
	        		out.println("<th>Car Number--<th>");
	        		out.println("<th>"+dcarnumber+"<th></tr>");
	        		out.println("<tr style='color:blue;'>");
	        		out.println("<th>Cab  Type--<th>");
	        		out.println("<th>"+dcabtype+"<th></tr>");
	        		out.println("<tr style='color:blue;'>");
	        		out.println("<th>Car  Name--<th>");
	        		out.println("<th>"+dcarname+"<th></tr>");
	        		out.println("<tr><th><p style='margin-left:5em;'></p></th>");
	  	            out.println("<th><input type='submit' value='Assign Cab'/></th></tr>");
	  	            out.println("</table></form>");
	  	            out.println("</body></html>");
	        		flag=1;
	        		break;
	        	}
	         }
	       }
	        else if(mstatus.equals("dassign")&&flag1==1)
	        {
	        	out.println("<h3 'style=color:blue;'>Driver has been assigned already</h3>");
	        }
	        else if(mstatus.equals("completed")&&flag1==1)
	        {
	        	out.println("<h3 'style=color:blue;'>Customer has been dropped at Specified Location</h3>");
	        }
	         if(flag1==0)
		       {
	        	  out.println("<h3 'style=color:blue;'>Customer with entered number is not present</h3>");
		       }
	         else if(flag==0&&mstatus.equals("pending"))
		        {
		        	   out.println("<h3 'style=color:blue;'>No Drivers are available</h3>");
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
