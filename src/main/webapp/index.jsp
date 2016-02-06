<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mystyle.css"> 
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
                    <form action="/MyAlchemyApp/FServlet" method="POST">
                        <input type="text" name="furl" value="www.ibm.com" size="110">
                        <input type="submit" value="Extract">
                    </form> <br/>
                    <p> Extract the following features: </p> <br/>
                    <table style="width: 40%;">
                        <tr>
                            <td><input type="checkbox" name="entities" > Entities</td>
                            <td><input type="checkbox" name="sentiments" > Sentiments </td>
                        </tr>
                        <tr>
                            <td> <input type="checkbox" name="keywords" > Keywords </td>
                            <td><input type="checkbox" name="concepts" > Concepts </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="relation" > Relation </td>
                            <td><input type="checkbox" name="taxonomy" > Taxonomy</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="author" > Author </td>
                            <td><input type="checkbox" name="language" > Language </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="text" > Text</td>
                            <td><input type="checkbox" name="feeds" > Feeds </td>
                        </tr>
                    </table> <br/><br/>
                </div>
                <div id="content2">
                    <form action="/IServet" method="POST">
                        <input type="text" name="gurl" value="http://aib.edu.au/blog/wp-content/uploads/2015/08/bill-gates-jpg.jpg" size="110">
                        <input type="submit" value="Extract">
                    </form> <br/>
                </div>
            </div>
        </div>
    </body>
</html>