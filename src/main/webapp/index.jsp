<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mystyle.css"> 
        <title>AlchemyAPI - Feature Extraction and Image Analysis</title>
    </head>
    <body>
        <div class ="main">
            <input id ="tab1" type ="radio" name="tabs" checked>
            <label for="tab1"> Feature Extraction </label>
            <input id="tab2" type="radio" name="tabs">
            <label for="tab2"> Image Analysis </label>
            <div class ="content">
                <div id ="content1">
                    <form action="FServlet" method="POST">
                        <input type="text" name="furl" value="https://en.wikipedia.org/wiki/IBM" size="80">
                        <input type="submit" value="Extract">
                    </form> <br/>
                </div>
                <div id="content2">
                    <form action="/IServet" method="POST">
                        <input type="text" name="gurl" value="http://aib.edu.au/blog/wp-content/uploads/2015/08/bill-gates-jpg.jpg" size="80">
                        <input type="submit" value="Extract">
                    </form> <br/>
                </div>
            </div>
			 <%
                      if (request.getAttribute("title") != null){
						out.println("<h1> Title: </h1>");
						out.println("<p>" + request.getAttribute("title") + "</p>");
					  }
					  if (request.getAttribute("language") != null){
						out.println("<h1> Language: </h1>");
						out.println("<p>" + request.getAttribute("language") + "</p>");
					  }
					  if (request.getAttribute("text") != null){
                        out.println("<h1>Text:</h1>");
					    out.println("<p>" + request.getAttribute("text") + "</p>");
					  }
					  if (request.getAttribute("authors") != null){
						out.println("<h1> Authors: </h1>");
						out.println("<p>" + request.getAttribute("authors") + "</p>");
					  }
					  if (request.getAttribute("taxonomy") != null){
						Map<String,String> taxmap = new HashMap<String,String>();
						List<String> keys = new ArrayList<String>(taxmap.keySet());
						for(String key: keys){
							out.println(key + " " + keys);
						}
					  }
                %>
        </div>
    </body>
</html>