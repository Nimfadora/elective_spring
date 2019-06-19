$(function () {
    function autocomplete(inp) {
        /*the autocomplete function takes two arguments,
        the text field element and an array of possible autocompleted values:*/
        var currentFocus;
        /*execute a function when someone writes in the text field:*/
        inp.addEventListener("input", function (e) {
            var a, b, i, val = this.value;
            /*close any already open lists of autocompleted values*/
            closeAllLists();
            if (!val) {
                return false;
            }
            currentFocus = -1;
            var currentElement = this;

            /*append the DIV element as a child of the autocomplete container:*/
            $.get("/search/hints", {searchString: val})
                .done(function (hints) {
                    /*create a DIV element that will contain the items (values):*/
                    if(hints.length > 0) {
                        a = document.createElement("DIV");
                        a.setAttribute("id", currentElement.id + "autocomplete-list");
                        a.setAttribute("class", "autocomplete-items");
                        currentElement.parentNode.appendChild(a);
                    }
                    /*for each item in the array...*/
                    for (i = 0; i < hints.length; i++) {
                        /*create a DIV element for each matching element:*/
                        b = document.createElement("DIV");
                        /*make the matching letters bold:*/
                        b.innerHTML = "<strong>" + hints[i].substr(0, val.length) + "</strong>";
                        b.innerHTML += hints[i].substr(val.length);
                        /*insert a input field that will hold the current array item's value:*/
                        b.innerHTML += "<input type='hidden' value='" + hints[i] + "'>";
                        /*execute a function when someone clicks on the item value (DIV element):*/
                        b.addEventListener("click", function (e) {
                            /*insert the value for the autocomplete text field:*/
                            inp.value = this.getElementsByTagName("input")[0].value;
                            $( "#searchBarForm" ).submit();
                            /*close the list of autocompleted values,
                            (or any other open lists of autocompleted values:*/
                            closeAllLists();
                        });
                        a.appendChild(b);
                    }
                });
        });
        /*execute a function presses a key on the keyboard:*/
        inp.addEventListener("keydown", function (e) {
            var x = document.getElementById(this.id + "autocomplete-list");
            if (x) x = x.getElementsByTagName("div");
            if (e.keyCode == 40) {
                /*If the arrow DOWN key is pressed,
                increase the currentFocus variable:*/
                currentFocus++;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 38) { //up
                /*If the arrow UP key is pressed,
                decrease the currentFocus variable:*/
                currentFocus--;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 13) {
                /*If the ENTER key is pressed, prevent the form from being submitted,*/
                e.preventDefault();
                if (currentFocus > -1) {
                    /*and simulate a click on the "active" item:*/
                    if (x) x[currentFocus].click();
                }
            }
        });

        function addActive(x) {
            /*a function to classify an item as "active":*/
            if (!x) return false;
            /*start by removing the "active" class on all items:*/
            removeActive(x);
            if (currentFocus >= x.length) currentFocus = 0;
            if (currentFocus < 0) currentFocus = (x.length - 1);
            /*add class "autocomplete-active":*/
            x[currentFocus].classList.add("autocomplete-active");
        }

        function removeActive(x) {
            /*a function to remove the "active" class from all autocomplete items:*/
            for (var i = 0; i < x.length; i++) {
                x[i].classList.remove("autocomplete-active");
            }
        }

        function closeAllLists(elmnt) {
            /*close all autocomplete lists in the document,
            except the one passed as an argument:*/
            var x = document.getElementsByClassName("autocomplete-items");
            for (var i = 0; i < x.length; i++) {
                if (elmnt != x[i] && elmnt != inp) {
                    x[i].parentNode.removeChild(x[i]);
                }
            }
        }

        /*execute a function when someone clicks in the document:*/
        document.addEventListener("click", function (e) {
            closeAllLists(e.target);
        });
    }


    $("#show_all").click(function () {
        var desc = $("#course-desc");
        if (desc.hasClass("desc-short")) {
            $(this).text("Скрыть");
            desc.removeClass("desc-short");
        } else {
            $(this).text("Показать все");
            desc.addClass("desc-short");
        }
    });

//    lang

    $("#lang > a").click(function () {
        $(this).hide()
        $("#lang > select").show()
    });

    $("#lang > select").change(function () {
        $(this).hide()
        $("#lang > a").text($("#lang > select option:selected").text());
        $("#lang > a").show()
    });


//    login

    $("#signup").click(function () {
        $("#first").fadeOut("fast", function () {
            $("#second").fadeIn("fast");
        });
    });

    $("#signin").click(function () {
        $("#second").fadeOut("fast", function () {
            $("#first").fadeIn("fast");
        });
    });


    // $(function () {
    //     $("form[name='login']").validate({
    //         rules: {
    //
    //             email: {
    //                 required: true,
    //                 email: true
    //             },
    //             password: {
    //                 required: true,
    //
    //             }
    //         },
    //         messages: {
    //             email: "Please enter a valid email address",
    //
    //             password: {
    //                 required: "Please enter password",
    //
    //             }
    //
    //         },
    //         submitHandler: function (form) {
    //             form.submit();
    //         }
    //     });
    // });
    //
    // $("form[name='registration']").validate({
    //     rules: {
    //         firstname: "required",
    //         lastname: "required",
    //         email: {
    //             required: true,
    //             email: true
    //         },
    //         password: {
    //             required: true,
    //             minlength: 5
    //         }
    //     },
    //
    //     messages: {
    //         firstname: "Please enter your firstname",
    //         lastname: "Please enter your lastname",
    //         password: {
    //             required: "Please provide a password",
    //             minlength: "Your password must be at least 5 characters long"
    //         },
    //         email: "Please enter a valid email address"
    //     },
    //
    //     submitHandler: function (form) {
    //         form.submit();
    //     }
    // });

    autocomplete(document.getElementById("searchString"))
});