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
						out.println("<h1> Taxonomy </h1>");
						out.println("<p>" + request.getAttribute("taxonomy") + "</p>");
					  } 
					  if (request.getAttribute("concepts") != null){
						out.println("<h1> Concepts: </h1>");
						out.println("p>" + request.getAttribute("concepts") + "</p>");
					  }
					  if (request.getAttribute("keywords") != null){
						out.println("<h1> Keywords: </h1>");
						out.println("<p>" + request.getAttribute("keywords") + "</p>");
					  }
					  if (request.getAttribute("entities") != null){
						out.println("<h1> Entities: </h1>");
						out.println("<p>" + request.getAttribute("entities") + "</p>");
					  }
					  if (request.getAttribute("feeds") != null){
						out.println("<h1> Feeds: </h1>");
						out.println("<p>" + request.getAttribute("feeds") + "</p>");
					  }
					  if (request.getAttribute("sentiment") != null){
						out.println("<h1> Sentiment: </h1>");
						out.println("<p>" + request.getAttribute("sentiment") + "</p>");
					  }
					  if (request.getAttribute("relations") != null){
						out.println("<h1> Relations: </h1>");
						out.println("<p>" + request.getAttribute("relations") + "</p>");
					  }
                    %>
                </div>
                <div id="content2">
                    <form action="IServet" method="POST">
                        <input type="text" name="gurl" value="http://aib.edu.au/blog/wp-content/uploads/2015/08/bill-gates-jpg.jpg" size="80">
                        <input type="submit" value="Extract">
                    </form> <br/>
					<%
					if (request.getAttribute("imagelink") != null){
						out.println("<h1>" + request.getAttribute("imagelink") + "</h1>");
					}
					%>
                </div>
            </div>
			 
        </div>
    </body>
</html>