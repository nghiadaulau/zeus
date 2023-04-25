$('.options').on('click', function () {
    $('li.active').removeClass('active');
    $(this).addClass('active');
});
$('.hKVaPN').on('click', function() {
    const index = $('.hKVaPN').index(this);
    $('.modalForm').eq(index).click();
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

let map;
let marker;

function initMap(callback) {
    map = new google.maps.Map($("#map")[0], {
        zoom: 8,
    });

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                map.setCenter(pos);
                marker = new google.maps.Marker({
                    position: pos,
                    map: map,
                });
                getLocationName(pos.lat, pos.lng);
            },
            () => {
                handleLocationError(true, map.getCenter());
            }
        );
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, map.getCenter());
    }

    // Add a click listener to the map to allow the user to pick a new location
    map.addListener("click", (event) => {
        const lat = event.latLng.lat();
        const lng = event.latLng.lng();
        marker.setPosition({lat: lat, lng: lng});
        getLocationName(lat, lng);
    });
}

function handleLocationError(browserHasGeolocation, pos) {
    console.log(
        browserHasGeolocation
            ? "Error: The Geolocation service failed."
            : "Error: Your browser doesn't support geolocation."
    );
}

function getLocationName(lat, lng) {
    fetch(`https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${lat}&lon=${lng}`)
        .then(response => response.json())
        .then(data => {
            const locationName = data.display_name;
            const arrLocation = locationName.split(",");
            $("#Country").val(arrLocation[arrLocation.length - 1])
            $("#city").val(arrLocation[arrLocation.length - 3])
            $("#address").val(arrLocation.slice(0, arrLocation.length - 3).join(', '))
            console.log(locationName);
        });
}

$("#imageInput").change(function () {
    const file = this.files[0];
    const reader = new FileReader();
    reader.onload = function (e) {
        $("#newImage").attr("src", e.target.result);
    }
    reader.readAsDataURL(file);
});
