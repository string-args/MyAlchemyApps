package Servlet;

import Bean.AlchemyConnector;
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
import java.text.ParseException;


@WebServlet(name = "FServlet", urlPatterns = {"/FServlet"})
public class FServlet extends HttpServlet {

	private String TAXONOMY_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedTaxonomy";
	private String LANGUAGE_ENDPOINT_URL = "http://access.alchemyapi.com/calls/url/URLGetLanguage";
	private String AUTHOR_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetAuthors";
	private String SENTIMENT_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetTextSentiment";
	private String TITLE_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetTitle";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		AlchemyConnector connector = new AlchemyConnector();
		//AlchemyLanguage service = new AlchemyLanguage();
		//service.setApiKey(connector.getAPIKey());
		
		String input_url = (String) request.getParameter("furl");
		StringBuilder sb = new StringBuilder();
		String line;
		
		JSONParser parser = new JSONParser();
		
		URL taxonomy_url = new URL(TAXONOMY_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(taxonomy_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		try{
			JSONObject taxonomy_object = (JSONObject) parser.parse(sb.toString());
			JSONArray taxonomy_array = (JSONArray) taxonomy_object.get("taxnomony");
			String taxonomy_list = "";
			for (int i = 0; i < taxonomy_array.size(); i++){
				taxonomy_list.concat((String) taxonomy_array.get(i));
			}
			request.setAttribute("taxonomy",taxonomy_list);
		}catch(Exception ex){}

		
		sb = new StringBuilder();
		
		URL language_url = new URL(LANGUAGE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		reader = new BufferedReader(new InputStreamReader(language_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		try{
			JSONObject language_object = (JSONObject) parser.parse(sb.toString());
			String language_parse = (String) language_object.get("language");
			request.setAttribute("language",language_parse);
		}catch (Exception ex) {}

		
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
		try{
			JSONObject author_object = (JSONObject) parser.parse(sb.toString());
			JSONArray author_array = (JSONArray) author_object.get("authors");
			String author_list = "";
			for (int i = 0; i < author_array.size(); i++){
				author_list.concat(author_array.get(i) + ", ");
			}
			request.setAttribute("authors",author_list);
		}catch(Exception ex){}

		
		sb = new StringBuilder();
		
		URL title_url = new URL(TITLE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		reader = new BufferedReader(new InputStreamReader(title_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		try{
			JSONObject title_object = (JSONObject) parser.parse(sb.toString());
			String title_parse = (String) title_object.get("title");
			request.setAttribute("title",title_parse);
		}catch(Exception ex){}
		//Map<String,Object> params = new HashMap<String,Object>();
		//params.put(AlchemyLanguage.URL, input_url);
		
		
		
		//request.setAttribute("title",title);
		


		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(200);
		request.getRequestDispatcher("index.jsp").forward(request,response);		
    }
	

}
