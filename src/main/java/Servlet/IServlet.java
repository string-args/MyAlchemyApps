package Servlet;

import Bean.AlchemyConnector;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyVision;
import com.ibm.watson.developer_cloud.alchemy.v1.model.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet(name = "IServlet", urlPatterns = {"/IServlet"})
public class IServlet extends HttpServlet {

 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
		AlchemyConnector connector = new AlchemyConnector();
        
        AlchemyVision service = new AlchemyVision();
        service.setApiKey(connector.getAPIKey());
		
		//get url inputted by user
		String gurl = request.getParameter("gurl");
		
		URL image = new URL(gurl);
		
		//For imagelink
		ImageLink ilink = service.getImageLink(image);
		
		//For imagefaces
		ImageFaces faces = service.recognizeFaces(image,true);
		
		request.setAttribute("imagelink",ilink.toString());
		request.setAttribute("imagefaces",faces.toString());
		response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}

