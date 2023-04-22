$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (brand, status){
            $('#idEdit').val(brand.id);
            $('#nameEdit').val(brand.name);
        });
        $('#editModal').modal();
    });
});