base_url = "http://localhost:8089/";
function delete_user(id) {
    alert("ID of user: " + id);
    $.ajax({
        url: base_url + 'persons/supprimer/' + id + '',
        method: 'POST',
        dataType: 'json',
        success: function (data, msg) {
            var text = '';
            $.each(data, function (key, val) {
                console.log(data);
            });
            //$("#tableau_prod").html(text);
        },
        error: function (msg) {
            console.table(msg);
        }
    });
}