package banking;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import jdk.nashorn.internal.runtime.regexp.joni.Config;

public class MyServletEx extends HttpServlet{
	
        @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            String str = req.getParameter("transaction");
            
            int strint=Integer.parseInt(str);
            
            switch(strint){
                case 1:
                    RequestDispatcher rd1 = req.getRequestDispatcher("deposit.html");
                    rd1.forward(req, resp);
                    break;
                case 2:
                    RequestDispatcher rd2 = req.getRequestDispatcher("withdraw.html");
                    rd2.forward(req, resp);
                    break;
                case 3:
                    RequestDispatcher rd3 = req.getRequestDispatcher("transfer.html");
                    rd3.forward(req, resp);
                    break;
                case 4:
                    RequestDispatcher rd4 = req.getRequestDispatcher("statement.html");
                    rd4.forward(req, resp);
                    break;
            }
        }

}
