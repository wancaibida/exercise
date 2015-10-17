<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/10/17
  Time: 15:58
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <asset:stylesheet src="bootstrap/css/bootstrap.min.css"/>
    <title>最新微博</title>
</head>

<body>

<main>
    <ul class="list-group">
    </ul>
</main>

<nav>
    <ul class="pagination">
        <li>
            <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
<asset:javascript src="jquery-2.1.3.js"/>
<asset:javascript src="index/index.js"/>
<asset:javascript src="bootstrap/js/bootstrap.min.js"/>
<script>
    var basePath = "<%=basePath%>";
</script>
</body>
</html>