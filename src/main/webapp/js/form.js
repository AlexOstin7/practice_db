$(document).ready(function () {
    $("#ping2").click(function () {
        $.ajax({
            url:"/ping2",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                console.log(result);
                alert(result);
            }
        });
    });

    $("#addOrganization").click(function () {
        console.log('ORGANIZATION');
        var organization = {
            name: $("#name").val(),
            inn: $("#inn").val()
        };
        console.log('ORG', organization);

        $.ajax({
            url:"/organization",
            type:"POST",
            data: JSON.stringify(organization),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });

        clearFields();
    });

    $("#organizationlist").click(function () {
        $.ajax({
            url:"/organization",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                console.log(result);
                alert(JSON.stringify(result));
            }
        });
    });
});

var clearFields = function () {
    $("#name").val('');
    $("#inn").val('');
};