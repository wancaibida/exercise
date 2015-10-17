$(function ()
{
    var page = 1;
    show(page);

    $("ul.pagination").find("a").click(function ()
    {
        var p = $(this).data("page");
        switch(p)
        {
            case "prev":
                page = page - 1 > 0 ? --page : 0;
                break;
            case "next":
                ++page;
                break;
            default :
                page = 1;
                break;
        }
        show(page);
    });

    function show(page)
    {
        //console.log(page);
        $.get(basePath + "index/list", {page : page}, function (data)
        {
            var html = render(data);
            $("ul").first().html(html);
        }, "json");
    }

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