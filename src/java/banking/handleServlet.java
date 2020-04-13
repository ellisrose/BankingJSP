/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Ellis
 */
public class handleServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int amt;
        String act=null;
        String transact=req.getParameter("transaction");
        String amount = req.getParameter("amount");
        String acc = req.getParameter("AccountNo.");
        
        amt=Integer.parseInt(amount);
        
        SessionFactory session_fact = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = session_fact.openSession();
        
        Enumeration<String> paramNames=req.getParameterNames();
        int x=0;
        for(int i = 0;i<req.getParameterMap().size();i++){
            if(paramNames.nextElement().equals("Account")){
                act = req.getParameter("Account");
            }
        }
        
        if (transact.equals("1")){
            session.beginTransaction();
            
            Account a = (Account) session.get(Account.class, acc);
            a.setBalance(a.balance+amt);
            session.saveOrUpdate(a);
            
            session.close();
            
            Enumeration<String> params;
            params = req.getParameterNames();
            for(int i=0;i<req.getParameterMap().size();i++ ){
                req.removeAttribute(params.nextElement());
            }
            RequestDispatcher rd =req.getRequestDispatcher("login.html");
            rd.include(req, resp);
            
        }else if(transact.equals("2")){
            
            session.beginTransaction();
            
            Account a = (Account) session.get(Account.class, acc);
            double check  = a.getBalance();
            if((check-amt)>a.initial){
                a.setBalance(a.balance-amt);
                session.saveOrUpdate(a);
                session.close();
                Enumeration<String> params;
                params = req.getParameterNames();
                for(int i=0;i<req.getParameterMap().size();i++ ){
                    req.removeAttribute(params.nextElement());
                }
                RequestDispatcher rd =req.getRequestDispatcher("login.html");
                rd.include(req, resp);
            }else{
                session.close();
                RequestDispatcher rd =req.getRequestDispatcher("transact.html");
                rd.include(req, resp);
            }
        }else if(transact.equals("3")){
            session.beginTransaction();
            int act1=Integer.parseInt(act);
            
            Account a = (Account) session.get(Account.class, acc);
            Account b = (Account) session.get(Account.class,act1);
            double check  = a.getBalance();
            if((check-amt)>a.initial){
                a.setBalance(a.balance-amt);
                b.setBalance(b.balance+amt);
                session.saveOrUpdate(a);
                session.saveOrUpdate(b);
                session.close();
                
                Enumeration<String> params;
                params = req.getParameterNames();
                for(int i=0;i<req.getParameterMap().size();i++ ){
                    req.removeAttribute(params.nextElement());
                }
                RequestDispatcher rd =req.getRequestDispatcher("login.html");
                rd.include(req, resp);
            }else{
                session.close();
                RequestDispatcher rd =req.getRequestDispatcher("transact.html");
                rd.include(req, resp);
            }
        }else if(transact.equals("4")){
            PrintWriter out = resp.getWriter();
            
            session.beginTransaction();
            Account acc1=(Account)session.get(Account.class, acc);
            
            out.print("The current Balance of Account "+acc1.getId()+" is "+acc1.getBalance());
        }
        
        
    }
    
}
