$(function() {
    var availableTags = [
        "ActionScript",
        "AppleScript",
        "Asp",
        "BASIC",
        "C",
        "C++",
        "Clojure",
        "COBOL",
        "ColdFusion",
        "Erlang",
        "Fortran",
        "Groovy",
        "Haskell",
        "Java",
        "JavaScript",
        "Lisp",
        "Perl",
        "PHP",
        "Python",
        "Ruby",
        "Scala",
        "Scheme"
    ];
    // $("#search_bar").autocomplete({
    //     source: availableTags
    // });

    $("#show_all").click(function() {
        var desc = $("#course-desc");
        if(desc.hasClass("desc-short")) {
            $(this).text("Скрыть");
            desc.removeClass("desc-short");
        } else {
            $(this).text("Показать все");
            desc.addClass("desc-short");
        }
    })
});