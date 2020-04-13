package banking;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import banking.Account;
import banking.Customer;
import org.hibernate.cfg.Configuration;

public class MyHttpServletClass extends HttpServlet {
	
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		
//		String name = req.getParameter("username");
//		String paswd = req.getParameter("password");
//		
//		out.print("<html>"
//				+ "<body>"
//				+"<h1><b><i>"
//				+ "Welcom to "+ name + " to your account with password " + paswd+ " servlet class"
//				+"</i"
//				+"</b>"
//				+"</body>"
//				+"</html>");
//		
//	}
	
	
        @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
                SessionFactory session_fact = new AnnotationConfiguration().configure().buildSessionFactory();
                Session session = session_fact.openSession();
		
		String opt = req.getParameter("mydropdown");
		String acc = req.getParameter("AccountNo.");
                
                session.beginTransaction();
                Account account = null;
                
                
                account = (Account) session.get(Account.class, acc);
                
                session.close();
		
		if(opt.equals("1")) {
			RequestDispatcher rd = req.getRequestDispatcher("customer.html");
			rd.forward(req, resp);
		
                }else if(opt.equals("2") && account!=null){
                        RequestDispatcher rd = req.getRequestDispatcher("account.html");
                        rd.forward(req, resp);
                }else if(opt.equals("3")){
                        RequestDispatcher rd = req.getRequestDispatcher("customer.html");
                        rd.forward(req, resp);
                } else{
                        RequestDispatcher rd = req.getRequestDispatcher("login.html");
                        rd.forward(req, resp);
                }
        }
}
	
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		
//		String name = req.getParameter("username");
//		String paswd = req.getParameter("password");
//		
//		out.print("<html>"
//				+ "<body>"
//				+"<h1><b><i>"
//				+ "Welcom to "+ name + " to your account with password " + paswd+ " servlet class"
//				+"</i"
//				+"</b>"
//				+"</body>"
//				+"</html>");
//		
//	}
