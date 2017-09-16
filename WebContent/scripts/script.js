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
                }
            });
            e.preventDefault();
        });
    }

    /**
     * Pass through raw response and process one by one
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
    }

    /**
     * Render the highlight content after the heading
     *
     * @param {String} p_headClassName - the target heading class for highlight to append to
     * @param {String} p_targetClassName - the target class for highlight to append to
     * @param {String} p_payload - the content to be inserted
     */
    function renderHighlight(p_headClassName, p_targetClassName, p_payload) {
        var hightlightToBeAdded = $("<div/>", {
            "class": "highlight-" + p_targetClassName,
            text: p_payload,
        })

        $(".h-heading-" + p_headClassName).after(hightlightToBeAdded);
    }

    /**
     * Render the heading for each book
     *
     * @param {String} p_targetClassName - the target class for heading to append to
     * @param {String} p_bookName - the actual content of the heading
     */
    function renderHeading(p_targetClassName, p_bookName) {
        $("<h3/>", {
            "class": "h-heading-" + p_targetClassName,
            text: p_bookName,
        }).appendTo(".hightlight-container");
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
        if (p_input.startsWith("?")) {
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