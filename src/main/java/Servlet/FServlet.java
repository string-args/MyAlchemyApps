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


@WebServlet(name = "FServlet", urlPatterns = {"/FServlet"})
public class FServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		AlchemyConnector connector = new AlchemyConnector();
		AlchemyLanguage service = new AlchemyLanguage();
		
		String input_url = (String) request.getAttribute("furl");

		//Map<String,Object> params = new HashMap<String,Object>();
		//params.put(AlchemyLanguage.URL, input_url);

		URL url = new URL("http://gateway-a.watsonplatform.net/calls/url/URLGetRankedTaxonomy?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		URLConnection yc = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		
		String outputLine;
		String json_output = "";
		while ((outputLine = in.readLine()) != null)
			json_output.concat(outputLine);
		in.close();
		
		//DocumentTitle title = service.getTitle(params);		
		//DocumentAuthors authors = service.getAuthors(params);
		//Language language = service.getLanguage(params);	
		//Taxonomies taxonomy = service.getTaxonomy(params);	
		//DocumentSentiment sentiment = service.getSentiment(params);
			
		request.setAttribute("title",json_output);	
		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(200);
		request.getRequestDispatcher("index.jsp").forward(request,response);		
    }
	

}
