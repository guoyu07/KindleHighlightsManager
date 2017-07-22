(function(global, $) {
    "use strict";

    $(init);

    function init() {
        $(".home-heading").html("Kindle Highlights Manager");
        processHighlight();
    }

    function processHighlight() {
        $.get("PageHandler", function(response, status) {
            $.each(response, function(index, singleHighlight) {
                renderHighlight("author", singleHighlight);
                renderHighlight("book-name", singleHighlight);
                renderHighlight("content", singleHighlight);
            });
        });

    }

    function renderHighlight(p_className, p_payload) {
        $("<div/>", {
            "class" : "highlight-" + p_className,
            text : p_payload[p_className],
        }).appendTo(".hightlight-container");
    }
})(window, jQuery);