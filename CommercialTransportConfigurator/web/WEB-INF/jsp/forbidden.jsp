<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<jsp:include page="header.jsp"/>

<!-- ОСНОВНОЕ СОДЕРЖИМОЕ -->
<main>
    <br>
    <br><br><br><br>
    <div style="text-align: center;"><H1>Forbidden!</H1>
    <H3>Sorry, you are not authorized to access this resource</H3>
    ${message}
    </div>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
