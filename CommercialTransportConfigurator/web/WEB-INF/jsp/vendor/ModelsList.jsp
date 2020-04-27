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
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Photo</th>
            <th scope="col">Basic price</th>
            <th scope="col">Available options</th>
            <th scope="col">Edit model</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${models}" var="model">
            <tr>
                <th scope="row">${model.id}</th>
                <td>${model.name}</td>
                <td><img src="<c:url value="${model.photo}"/>"/></td>
                <td>${model.basicPrice}</td>
                <td>
                    <c:url value="/vendor/editModel.action" var="editModelAction"/>
                    <FORM id="form-${model.id}" action="${editModelAction}" method="post">
                        <INPUT type="hidden" name="modelId" value="${model.id}">
                        <BUTTON type="submit" class="btn btn-warning">Редактировать</BUTTON>
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
