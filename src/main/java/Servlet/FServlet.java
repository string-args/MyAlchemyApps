package Servlet;

import Bean.AlchemyConnector;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentText;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentTitle;
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
		
		Map<String,Object> params = new HashMap<String,Object>();
        params.put(AlchemyLanguage.URL, furl);
        String furl = request.getParameter("furl");
        //For title
		DocumentTitle title = service.getTitle(params);
		//For text
        DocumentText text = service.getText(params);
		try{
			JSONParser parser = new JSONParser();
			//For text
			JSONObject objtext = (JSONObject) parser.parse(text.toString());
			String parseText = (String) objtext.get("text");
			request.setAttribute("text", parseText);
			request.setAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
        response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
