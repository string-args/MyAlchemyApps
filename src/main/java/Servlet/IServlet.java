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
		
		String input_url = (String) request.getAttribute("gurl");
		URL url = new URL(input_url);
		
		ImageFaces image_faces = service.recognizeFaces(url,false);
		request.setAttribute("image_faces",image_faces);
	
		response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}

