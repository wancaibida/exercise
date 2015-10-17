$(function ()
{
    $.get(basePath + "index/list", {}, function (data)
    {
        var html = render(data);
        $("ul").first().html(html);
    }, "json");

    function render(data)
    {
        var html = [];
        if (data && data.statuses)
        {
            $.each(data.statuses, function (index, obj)
            {
                html.push(renderRow(obj));
            })
        }
        return html.join("");
    }

    function renderRow(row)
    {
        return "<li class=\"list-group-item\">" + row.text + "</li>";
    }

});