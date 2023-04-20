$('.options').on('click', function () {
    $('li.active').removeClass('active');
    $(this).addClass('active');
});

function openCity(cityName, elmnt) {
    let i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");

    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablink");

    document.getElementById(cityName).style.display = "block";

}

document.getElementById("default-open").click();
