<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<jsp:include page="../header.jsp"/>

<!-- ОСНОВНОЕ СОДЕРЖИМОЕ -->
<main>
    <br>
    <br><br><br><br>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Edit</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${users}" var="user">
            <tr>

                <th scope="row">${user.email}</th>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>
                    <c:url value="/users/editUser.action" var="userEditAction"/>
                    <FORM id="form-${reader.identity}" action="${userEditAction}" method="post">
                        <INPUT type="hidden" name="userId" value="${user.id}">
                        <BUTTON type="submit" class="btn btn-warning">Редактирвоать</BUTTON>
                    </FORM>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>
