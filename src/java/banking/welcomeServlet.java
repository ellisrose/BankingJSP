package banking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class welcomeServlet extends HttpServlet{
        @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
                SessionFactory session_fact = new AnnotationConfiguration().configure().buildSessionFactory();
                Session session = session_fact.openSession();
		
                String opt= req.getParameter("mydropdown");
                String addr = req.getParameter("addr");
                String age = req.getParameter("age");
                String name = req.getParameter("name");
                String job = req.getParameter("mydropdown1");
                
                if(job.equals("1")){
                    job="Employed";
                }else if(job.equals("2")){
                    job="Unemployed";
                } else  if(job.equals("3")){
                    job="Retired";
                }else if(job.equals("4")){
                    job="Self-Employed";
                }else{
                    RequestDispatcher rd = req.getRequestDispatcher("customer.html");
                    rd.include(req, resp);
                }
                String acc2 = req.getParameter("AccountNo.");
                
                int age1 = Integer.parseInt(age);
                int acc=Integer.parseInt(acc2);
                
                
                if ("3".equals(opt)){
                    session.beginTransaction();
                    
                    Account a=(Account) session.get(Account.class, acc);
                            
                    Customer newCust = new Customer(addr,age1,name,job,a);
                    
                    a.addCustomers(newCust);
                    
                    session.saveOrUpdate(a);
                
                    session.persist(newCust);
                    
                    session.close();
                    
                    RequestDispatcher rd = req.getRequestDispatcher("account.html");
                    rd.include(req, resp);
                }
                
                
                
                if("1".equals(opt)){
                    String acct = req.getParameter("accType");
                    String iniit = req.getParameter("Initial Deposit");
                    
                    int initial=Integer.parseInt(iniit);
                    
                    if ("1".equals(acct)){
                        CurrentAccount acc1 = new CurrentAccount();
                        acc1.setInitial(initial);
                        session.beginTransaction();
                
                        session.persist(acc1);
                    
                        session.close();
                    }else if("2".equals(acct)){
                        SalaryAccount acc1 = new SalaryAccount();
                        acc1.setInitial(initial);
                        session.beginTransaction();
                
                        session.persist(acc1);
                    
                        session.close();
                    }else {
                        SavingsAccount acc1 = new SavingsAccount();
                        acc1.setInitial(initial);
                        session.beginTransaction();
                
                        session.persist(acc1);
                        
                        session.close();
                    }
                    
                    
                    
                    RequestDispatcher rd = req.getRequestDispatcher("account.html");
                    rd.include(req, resp);
                }
	}
	

}
