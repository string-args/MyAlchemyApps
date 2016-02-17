package Servlet;

import Bean.AlchemyConnector;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet(name = "FServlet", urlPatterns = {"/FServlet"})
public class FServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
				response.setContentType("text/html;charset=UTF-8");
		
		AlchemyConnector connector = new AlchemyConnector();
		AlchemyLanguage al_service = new AlchemyLanguage();
		al_service.setApiKey(connector.getAPIKey());
		
		String input = (String) request.getAttribute("furl");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(AlchemyLanguage.TEXT,input);
		
		DocumentSentiment sentiment = al_service.getSentiment(params);
		request.setAttribute("sentiment",sentiment);
		response.setStatus(200);
		request.getRequestDispatcher("index.jsp").forward(request,response);		
    }
	

}
