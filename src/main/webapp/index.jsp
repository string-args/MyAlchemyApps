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
                        <input type="text" name="furl" size="80">
                        <input type="submit" value="Extract">
                    </form> <br/>
					<%
                      if (request.getAttribute("title") != null){
						out.println("<h4> Title: </h4>" + request.getAttribute("title"));
						
					  }
					  if (request.getAttribute("language") != null){
						out.println("<h4> Language: </h4>"+ request.getAttribute("language"));
					  }
					  if (request.getAttribute("authors") != null){
						out.println("<h4> Authors: </h4>" + request.getAttribute("authors"));
					  }
					  if (request.getAttribute("text") != null){
                        out.println("<h4>Text:</h4>");
					    out.println("<p style = 'font-size: 10px;'> " + request.getAttribute("text") + "</p>");
					  }
					  if (request.getAttribute("taxonomy") != null){
						out.println("<h4> Taxonomy </h4>");
						out.println("<p style = 'font-size: 10px;'>" + request.getAttribute("taxonomy") + "</p>");
					  } 
					  if (request.getAttribute("concepts") != null){
						out.println("<h4> Concepts: </h4>");
						out.println("<p style = 'font-size: 10px;'>" + request.getAttribute("concepts") + "</p>");
					  }
                    %>
                </div>
                <div id="content2">
                    <form action="IServlet" method="POST">
                        <input type="text" name="gurl" value="http://aib.edu.au/blog/wp-content/uploads/2015/08/bill-gates-jpg.jpg" size="80">
                        <input type="submit" value="Extract">
                    </form> <br/>
					<%
					if (request.getAttribute("imagekeywords") != null){
						out.println("<h1>" + request.getAttribute("imagekeywords") + "</h1>");
					}
					if (request.getAttribute("imagefaces") != null){
						out.println("<h1>" + request.getAttribute("imagefaces") + "</h1>");
					}
					
					%>
                </div>
            </div>
			 
        </div>
    </body>
</html>