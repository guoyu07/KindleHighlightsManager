(function(global, $) {
    "use strict";

    $(init);

    /**
     * Initialize the page: render title and book highlight content
     *
     */
    function init() {
        $(".home-heading").html("Kindle Highlights Manager");

        $("#upload-form").submit(function(e) {
            $.ajax({
                type: "POST",
                url: "UploadServlet",
                cache: false,
                data: new FormData($('#upload-form')[0]),
                processData: false,
                contentType: false,
                success: function(response, textStatus, jqXHR) {
                    processHighlight(response);
                    $("#process-highlights").hide();
                    $("#clear-highlights").show();
                }
            });
            e.preventDefault();
        });

        $("#clear-highlights").click(function(e) {
            $(".hightlight-container-grid").empty();
            $("#clear-highlights").hide();
            $("#process-highlights").show();
        });
    }

    /**
     * Pass through raw response payload and process one by one
     *
     * @param {String} p_data - the raw response from POST response
     */
    function processHighlight(p_data) {
        for (var bookname in p_data) {
            console.log(bookname);
            var processedBookName = generateDomClassName(bookname);
            renderHeading(processedBookName, bookname);
            for (var bookinfo in p_data[bookname]) {
                // renderHighlight(processedBookName, "author", p_data[bookname][bookinfo].author);
                // renderHighlight(processedBookName, "pagenum", p_data[bookname][bookinfo].pagenum);
                // renderHighlight(processedBookName, "date", p_data[bookname][bookinfo].date);
                renderHighlight(processedBookName, "content", p_data[bookname][bookinfo].content);
            }
        }

        // Optionally toggle extra functionality
        if ($(".hightlight-container-grid").text().length > 0) {
            $(".hightlight-export").show();
        }
    }

    /**
     * Render the highlight content besides the heading of the grid
     *
     * @param {String} p_headClassName - the target heading class for highlight to append to
     * @param {String} p_targetClassName - the target class for highlight to append to
     * @param {String} p_payload - the content to be inserted
     */
    function renderHighlight(p_headClassName, p_targetClassName, p_payload) {
        if ($(".heading-" + p_headClassName).find(".col-9").length) {
            $("<p/>", {
                text: p_payload,
            }).appendTo($(".heading-" + p_headClassName).find(".col-9"));
        } else {
            $("<div/>", {
                "class": "col-9",
                text: p_payload,
            }).appendTo(".heading-" + p_headClassName);
        }
    }

    /**
     * Render the heading for each book
     *
     * @param {String} p_targetClassName - the target class for heading to append to
     * @param {String} p_bookName - the actual content of the heading
     */
    function renderHeading(p_targetClassName, p_bookName) {
        $("<div/>", {
            "class": "row heading-" + p_targetClassName,
        }).appendTo(".hightlight-container-grid");

        $("<div/>", {
            "class": "col",
            html: "<h5>" + p_bookName + "<h5/>",
        }).appendTo(".heading-" + p_targetClassName);
    }

    /**
     * Convert the original book name to a class name in HTML
     *
     * @param {String} p_input - the original book name
     * @return {String} result - shortened class for that book
     */
    function generateDomClassName(p_input) {
        var originText = p_input;
        var originTextWithoutQuestionMark = removeLeadingQuestionMark(originText);
        var originTextWithoutSpace = removeSpaces(originTextWithoutQuestionMark);
        var result = originTextWithoutSpace.substring(0, 5);
        return result.trim();
    }

    /**
     * Remove the leading question mark of the input
     *
     * @param {String} p_input - the original content
     * @return {String} result - content without the leading question mark
     */
    function removeLeadingQuestionMark(p_input) {
        var result = p_input;
        if (p_input != null && p_input.startsWith("?")) {
            result = result.substr(1);
        }
        return result;
    }

    /**
     * Replace all spaces from the input
     *
     * @param {String} p_input - the original content
     * @return {String} result - content without any empty space
     */
    function removeSpaces(p_input) {
        var result = p_input;
        if (result != null) {
            result = result.replace(/\s+/g, '').toLowerCase();
        }
        return result;
    }
})(window, jQuery);