<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="style/main.css" media="screen" />
    <title>Kindle Highlights Manager - Beta</title>
</head>
<body>
    <script src="resources/jquery-3.2.1.min.js"></script>
    <script src="resources/bootstrap.min.js"></script>
    <script src="scripts/script.js"></script>
    <h2 class="home-heading" style="text-align:center">Failed loading JavaScript</h2>
    <div class="file-selection">
        <!-- file selection and upload area -->
        <h3>Please select your kindle highlight file</h3>
    	    <form id="upload-form">
            <input type="file" name="highlightFile">
            <input type="submit" value="upload"/>
    	    </form>
    </div>
    <div class="hightlight-container">
    </div>
</body>
</html>