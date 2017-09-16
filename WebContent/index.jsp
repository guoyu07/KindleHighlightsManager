<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/bootstrap.min.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="style/main.css" />
    <title>Kindle Highlights Manager - Beta</title>
</head>
<body>
    <script src="resources/jquery-3.2.1.min.js"></script>
    <script src="resources/bootstrap.min.js"></script>
    <script src="scripts/script.js"></script>
    <h2 class="home-heading" style="text-align:center">Failed loading JavaScript</h2>
    
    <!-- Quick intro container -->
    <div class="container">
        <h4>Quick Intro</h4>
        <p>Simply choose your kindle highlight file, and click "Process" button.</p>
    </div>

    <!-- File selection area -->
    <div class="container">
        <div class="file-selection">
            <!-- file selection and upload area -->
        	    <form id="upload-form" enctype="multipart/form-data">
                <input type="file" name="highlightFile">
                <input id="process-highlights" type="submit" class="btn btn-primary" value="Process"/>
                <button id="clear-highlights" type="button" class="btn btn-primary" style="display: none">Clear</button>
        	    </form>
        </div>
    </div>
    <div class="hightlight-container">
    </div>
</body>
</html>