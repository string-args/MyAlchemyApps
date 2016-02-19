package Servlet;

import Bean.AlchemyConnector;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentTitle;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentAuthors;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Language;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Taxonomies;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@WebServlet(name = "FServlet", urlPatterns = {"/FServlet"})
public class FServlet extends HttpServlet {

	private String TAXONOMY_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedTaxonomy";
	private String LANGUAGE_ENDPOINT_URL = "http://access.alchemyapi.com/calls/url/URLGetLanguage";
	private String AUTHOR_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetAuthors";
	private String SENTIMENT_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetTextSentiment";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		AlchemyConnector connector = new AlchemyConnector();
		//AlchemyLanguage service = new AlchemyLanguage();
		//service.setApiKey(connector.getAPIKey());
		
		String input_url = (String) request.getParameter("furl");
		StringBuilder sb = new StringBuilder();
		String line;
		
		URL taxonomy_url = new URL(TAXONOMY_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(taxonomy_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		request.setAttribute("taxonomy",sb.toString());
		/*
		sb = new StringBuilder();
		
		URL language_url = new URL(LANGUAGE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		reader = new BufferedReader(new InputStreamReader(language_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		request.setAttribute("language",sb.toString());
		
		sb = new StringBuilder();
		
		URL sentiment_url = new URL(SENTIMENT_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		reader = new BufferedReader(new InputStreamReader(sentiment_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		request.setAttribute("sentiment",sb.toString());
		
		sb = new StringBuilder();
		
		URL author_url = new URL(AUTHOR_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		reader = new BufferedReader(new InputStreamReader(author_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		request.setAttribute("authors",sb.toString());
		
		sb = new StringBuilder();
		//Map<String,Object> params = new HashMap<String,Object>();
		//params.put(AlchemyLanguage.URL, input_url);
		
		
		
		//request.setAttribute("title",title);
		*/


		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(200);
		request.getRequestDispatcher("index.jsp").forward(request,response);		
    }
	

}
