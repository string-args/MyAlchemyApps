package Servlet;

import Bean.AlchemyConnector;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "FServlet", urlPatterns = {"/FServlet"})
public class FServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		AlchemyConnector connector = new AlchemyConnector();
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey(connector.getAPIKey());
		
		String input_url = (String) request.getAttribute("furl");

		Map<String,Object> params = new HashMap<String,Object>();
		params.put(AlchemyLanguage.BASE_URL, input_url);

		DocumentTitle title = service.getTitle(params);
		request.setAttribute("title", title);
		
		DocumentAuthors authors = service.getAuthors(params);
		request.setAttribute("authors", authors);
		
		Language language = service.getLanguage(params);
		request.setAttribute("language", language);
		
		//Taxonomies taxonomy = service.getTaxonomy(params);
		//request.setAttribute("taxonomy", taxonomy);
		
		DocumentSentiment sentiment = service.getSentiment(params);
		request.setAttribute("sentiment",sentiment);
			
		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(200);
		request.getRequestDispatcher("index.jsp").forward(request,response);		
    }
	

}
