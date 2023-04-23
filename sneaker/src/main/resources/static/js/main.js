/*price range*/

 $('#sl2').slider();

	var RGBChange = function() {
	  $('#RGB').css('background', 'rgb('+r.getValue()+','+g.getValue()+','+b.getValue()+')')
	};	
		
/*scroll to top*/

$(document).ready(function(){
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
	console.log($(".panel-group .panel-default h4:first").text())
	$(".panel-group .panel-default h4:first").addClass("tab-default-open");

	$(".tab-default-open a").click();
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

// Change after this comment if anyone wanna reuse
