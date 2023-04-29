/*price range*/

$('#sl2').slider();

var RGBChange = function () {
    $('#RGB').css('background', 'rgb(' + r.getValue() + ',' + g.getValue() + ',' + b.getValue() + ')')
};

/*scroll to top*/

$(document).ready(function () {
    $(function () {
        $.scrollUp({
            scrollName: 'scrollUp', // Element ID
            scrollDistance: 300, // Distance from top/bottom before showing element (px)
            scrollFrom: 'top', // 'top' or 'bottom'
            scrollSpeed: 300, // Speed back to top (ms)
            easingType: 'linear', // Scroll to top easing (see http://easings.net/)
            animation: 'fade', // Fade, slide, none
            animationSpeed: 200, // Animation in speed (ms)
            scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
            //scrollTarget: false, // Set a custom target element for scrolling to the top
            scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
            scrollTitle: false, // Set a custom <a> title if required.
            scrollImg: false, // Set true to use image
            activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
            zIndex: 2147483647 // Z-Index for the overlay
        });
    });

    $(".e1W0hK li a:first").addClass("tab-default-open");
    // $(".tab-default-open a").click();
    $(".tab-default-open").click();
    // $("ul.pagination.tab.nav-tabs.XtydSN li:first-child a").addClass("tab-default-open");
    // $("ul.pagination.tab.nav-tabs.XtydSN li:first-child a.tab-default-open").click();
});

function textAbstract(el, maxlength = 50, delimiter = " ") {
    let txt = $(el).text();
    if (el == null) {
        return "";
    }
    if (txt.length <= maxlength) {
        return txt;
    }
    let t = txt.substring(0, maxlength);
    let re = /\s+\S*$/;
    let m = re.exec(t);
    t = t.substring(0, m.index);
    return t + "...";
}

// Force length of text
const maxlengthwanted = 21;

$('.productinfo p').each(function (index, element) {
    $(element).text(textAbstract(element, maxlengthwanted, " "));
});

// Change after this comment if anyone wants to reuse


// New section
// $(".oEqkPY:first").addClass("active brand-default-open");
// $(".AZlYga:first").addClass("active category-default-open");
let storedData = localStorage.getItem("data");
let data = storedData ? JSON.parse(storedData) : {'brand': 0, 'category': 0};
// Category buttons
$('.AZlYga').click(function () {
    // $(this).parent().parent().find('.active').removeClass("active");
    // $(this).addClass("active");
    data.category = $(this).val()

    $.ajax({
        url: "/shop/filter",
        type: "get",
        data: data,
        success: function () {
            if (data['brand'] !== undefined) {
                window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}`;
            } else {
                window.location = `/shop/filter?category=${data['category']}`;
            }

            // Store the data values in localStorage before changing the window location
            localStorage.setItem("data", JSON.stringify(data));
            window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}`;
        },
        error: function () {
            // Code to handle error
        }
    });
})
// Brand buttons
$('.oEqkPY').click(function () {
    // $(this).parent().parent().find('.active').removeClass("active");
    // $(this).addClass("active");
    data.brand = $(this).val()

    $.ajax({
        url: "/shop/filter",
        type: "get",
        data: data,
        success: function () {
            if (data['category'] !== undefined) {
                if (data['brand'] === '0' || data['category'] === '0') {
                    window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}`;
                } else {
                    window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}`;
                }
            } else {
                window.location = `/shop/filter?brand=${data['brand']}`;
            }

            // Store the data values in localStorage before changing the window location
            localStorage.setItem("data", JSON.stringify(data));
            window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}`;
        },
        error: function () {
            window.location = `/shop/filter?brand=0&category=0`;
        }
    });
})
// Shop filter by DESC
$(".zBgVhp").click(function () {
    data.sortBy = 'filterByDesc'

    $.ajax({
        url: "/shop/filter",
        type: "get",
        data: data,
        success: function () {
            if (data['category'] !== undefined) {
                if (data['brand'] === '0' || data['category'] === '0') {
                    window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}&sortBy=${data['sortBy']}`;
                } else {
                    window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}&sortBy=${data['sortBy']}`;
                }
            } else {
                window.location = `/shop/filter?brand=${data['brand']}`;
            }

            // Store the data values in localStorage before changing the window location
            localStorage.setItem("data", JSON.stringify(data));
            window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}&sortBy=${data['sortBy']}`;
        },
        error: function () {
            window.location = `/shop/filter?brand=0&category=0`;
        }
    });
})
// Shop filter by ASC
$(".bhinFw").click(function () {
    data.sortBy = 'filterByAsc'

    $.ajax({
        url: "/shop/filter",
        type: "get",
        data: data,
        success: function () {
            if (data['category'] !== undefined) {
                if (data['brand'] === '0' || data['category'] === '0') {
                    window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}&sortBy=${data['sortBy']}`;
                } else {
                    window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}&sortBy=${data['sortBy']}`;
                }
            } else {
                window.location = `/shop/filter?brand=${data['brand']}`;
            }

            // Store the data values in localStorage before changing the window location
            localStorage.setItem("data", JSON.stringify(data));
            window.location = `/shop/filter?brand=${data['brand']}&category=${data['category']}&sortBy=${data['sortBy']}`;
        },
        error: function () {
            window.location = `/shop/filter?brand=0&category=0`;
        }
    });
})
// Search filter by DESC
let storedDataForSearch = localStorage.getItem("data_for_search");
let data_for_search = storedDataForSearch ? JSON.parse(storedDataForSearch) : {};
$(".search_box").on("keypress","input", function (e) {
    // 13 is the enter button
    if(e.which === 13){
        // alert("The value of search box is: " + inputVal);
        data_for_search.keySearch = $(this).val()

        $.ajax({
            url: "/shop/search",
            type: "get",
            data: data_for_search,
            success: function () {
                window.location = `/shop/search?keySearch=${data_for_search['keySearch']}`;

                // Store the data values in localStorage before changing the window location
                localStorage.setItem("data_for_search", JSON.stringify(data_for_search));
                window.location = `/shop/search?keySearch=${data_for_search['keySearch']}`;
            },
            error: function () {
                window.location = `/shop/filter?brand=0&category=0`;
            }
        });
    }
})
$(".OewQPG").click(function () {
    data_for_search.sortBy = 'filterByDesc'

    $.ajax({
        url: "/shop/search",
        type: "get",
        data: data_for_search,
        success: function () {
            window.location = `/shop/search?keySearch=${data_for_search['keySearch']}&sortBy=${data_for_search['sortBy']}`;

            // Store the data values in localStorage before changing the window location
            localStorage.setItem("data_for_search", JSON.stringify(data_for_search));
            window.location = `/shop/search?keySearch=${data_for_search['keySearch']}&sortBy=${data_for_search['sortBy']}`;
        },
        error: function () {
            window.location = `/shop/filter?brand=0&category=0`;
        }
    });
})
// Search filter by ASC
$(".xxEGwG").click(function () {
    data_for_search.sortBy = 'filterByAsc'

    $.ajax({
        url: "/shop/search",
        type: "get",
        data: data_for_search,
        success: function () {
            window.location = `/shop/search?keySearch=${data_for_search['keySearch']}&sortBy=${data_for_search['sortBy']}`;

            // Store the data values in localStorage before changing the window location
            localStorage.setItem("data_for_search", JSON.stringify(data_for_search));
            window.location = `/shop/search?keySearch=${data_for_search['keySearch']}&sortBy=${data_for_search['sortBy']}`;
        },
        error: function () {
            window.location = `/shop/filter?brand=0&category=0`;
        }
    });
})