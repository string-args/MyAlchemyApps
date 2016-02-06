import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class FServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Upload Servlet");
    	
    	request.setAttribute("sample","This is the Servlet!");
    	
        response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("/src/main/webapp/index.jsp").forward(request, response);
    }
}