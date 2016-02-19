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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		AlchemyConnector connector = new AlchemyConnector();
		AlchemyLanguage service = new AlchemyLanguage();
		
		String input_url = (String) request.getParameter("furl");

		//Map<String,Object> params = new HashMap<String,Object>();
		//params.put(AlchemyLanguage.URL, input_url);

		//URL url = new URL("http://gateway-a.watsonplatform.net/calls/url/URLGetRankedTaxonomy?url=http://thelasallian.com/2016/02/19/from-manila-to-thessaloniki-the-making-of-world-debate-champions/&apikey=c0659d73365193a78936037a46b1a27c6863a893&outputMode=json");
				
		//StringBuilder sb = new StringBuilder();
		//try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		//for (String line; (line = reader.readLine()) != null;) {
		//	sb.append(line);
		//}
		//}
		
		//try{
		//	JSONParser parser = new JSONParser();
		//  JSONObject 
		//}catch (Exception e){}

		request.setAttribute("url",input_url);	
		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(200);
		request.getRequestDispatcher("index.jsp").forward(request,response);		
    }
	

}
