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
    
    <!-- Quick intro with hero image -->
    <div class="intro-hero-image">
        <div class="intro-hero-text">
            <h1 class="home-heading">Failed loading JavaScript</h1>
            <p>Choose your kindle highlight file, and click "Process" button.</p>

            <!-- file selection and upload area -->
            <div class="file-selection">
                <form id="upload-form" enctype="multipart/form-data">
                    <input type="file" name="highlightFile">
                    <!-- button area -->
                    <button id="process-highlights" type="submit" class="btn btn-primary">
                        <i class="icon-user icon-white"></i>Process
                    </button>
                    <button id="clear-highlights" type="button" class="btn btn-primary" style="display: none">Clear</button>
                </form>
            </div>
        </div>
    </div>

    <div class="container">
        <!-- The area for highlights to be copied to -->
        <div class="hightlight-container-grid">
        </div>
    </div>

    <!-- The area for users to export their highlights -->
    <div class="hightlight-export" style="display: none">
        <h4>Please choose an option</h4>
    </div>
</body>

<footer class="home-heading">
    Failed loading JavaScript
</footer>
</html>