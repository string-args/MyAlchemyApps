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
        
        AlchemyConnector connector = new AlchemyConnector();
        
        AlchemyLanguage service = new AlchemyLanguage();
        service.setApiKey(connector.getAPIKey());
		
		//get url inputted by user
		String furl = request.getParameter("furl");
		
		Map<String,Object> params = new HashMap<String,Object>();
        params.put(AlchemyLanguage.URL, furl);
        //For title
		DocumentTitle title = service.getTitle(params);
		//For language
		Language language = service.getLanguage(params);
		//For text
        //DocumentText text = service.getText(params);
		//For authors
		DocumentAuthors authors = service.getAuthors(params);
		//For taxonomy
		Taxonomies taxonomy = service.getTaxonomy(params);
		//For concepts
		Concepts concepts = service.getConcepts(params);
		/*
		//For keywords
		Keywords keywords = service.getKeywords(params);
		//For entities
		Entities entities = service.getEntities(params);
		//For feeds
		Feeds feeds = service.getFeeds(params);
		//For sentiment
		DocumentSentiment sentiment = service.getSentiment(params);
		//For relations
		SAORelations relations = service.getRelations(params);
		*/
		try{
			JSONParser parser = new JSONParser();
			//for title
			JSONObject objtitle = (JSONObject) parser.parse(title.toString());
			String parseTitle = (String) objtitle.get("title");
			request.setAttribute("title", parseTitle);
			
			//For language
			JSONObject objlang = (JSONObject) parser.parse(language.toString());
			String parseLang = (String) objlang.get("language");
			request.setAttribute("language", parseLang);
			
			//For text
			JSONObject objtext = (JSONObject) parser.parse(text.toString());
			String parseText = (String) objtext.get("text");
			request.setAttribute("text", parseText);
			
			//For authors
			request.setAttribute("authors",authors);
			
			//For taxonomy
			JSONObject objtax = (JSONObject) parser.parse(taxonomy.toString());
			JSONArray arrtax = (JSONArray) objtax.get("taxonomy");
			request.setAttribute("taxonomy", arrtax);
			
			//For concepts
			JSONObject objcon = (JSONObject) parser.parse(concepts.toString());
			request.setAttribute("concepts",objcon);
		/*	
			//For keywords
			JSONObject objkey = (JSONObject) parser.parse(keywords.toString());
			request.setAttribute("keywords",objkey);
			
			//For entities
			JSONObject objent = (JSONObject) parser.parse(entities.toString());
			request.setAttribute("entities",objent);
			
			//For feeds
			JSONObject objfeeds = (JSONObject) parser.parse(feeds.toString());
			request.setAttribute("feeds",objfeeds);
			
			//For sentiment
			JSONObject objsent = (JSONObject) parser.parse(sentiment.toString());
			request.setAttribute("sentiment",objsent);
			
			//For relations
			JSONObject objrel = (JSONObject) parser.parse(relations.toString());
			request.setAttribute("relations",objrel);
*/
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
        response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
