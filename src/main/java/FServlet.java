import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FServlet", urlPatterns = {"/FServlet"})
public class FServlet extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception  {
   	System.out.println("Upload Servlet");
    	
    	request.setAttribute("sample","This is the Servlet!");
    	
        response.setContentType("text/html");
        response.setStatus(200);
        response.sendRedirect("index.jsp");
    }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

	 @Override
    public String getServletInfo() {
        return "Short description";
    }

}