package Servlet;

import Bean.AlchemyConnector;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentText;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentTitle;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Language;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentAuthors;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Taxonomies;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        DocumentText text = service.getText(params);
		//For authors
		DocumentAuthors authors = service.getAuthors(params);
		//For taxonomy
		Taxonomies taxonomy = service.getTaxonomy(params);
		
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
			JSONArray arrtax = objtax.getJSONArray("taxonomy");
			Map<String,String> taxparams = new HashMap<String,String>();
			for (int i = 0; i < arrtax.length(); i++){
				String parseLabel = arrtax.getJSONObject(i).getString("label");
				taxparams.put("Label",parseLabel);
				String parseScore = arrtax.getJSONObject(i).getString("score");
				taxparams.put("Score",parseScore);
				String parseConfident = arrtax.getJSONObject(i).getString("confident");
				taxparams.put("Confident",parseConfident);
			}
			request.setAttribute("taxonomy", taxparams);

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
        response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
