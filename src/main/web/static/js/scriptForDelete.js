
function handleDelete(id,tableName)
{
    var url = "http://localhost:8080/com_serve_main_war_exploded/"+tableName+"?id=" + id;
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("DELETE", url, true);
    xmlHttpRequest.onload = function () {
        document.location.reload();
    };
    xmlHttpRequest.send(null);
}
