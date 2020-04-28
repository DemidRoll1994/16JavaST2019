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

    <table class="table container">
        <thead>
        <tr class="row">
            <th class="col" scope="col">Id</th>
            <th class="col" scope="col">Name</th>
            <th class="col" scope="col">Photo</th>
            <th class="col" scope="col">Basic price</th>
            <th class="col" scope="col">Available options</th>
            <th class="col" scope="col">Edit model</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${models}" var="model">
            <tr class="row">
                <th class="col"scope="row">${model.id}</th>
                <td class="col">${model.name}</td>
                <td class="col"><img class="img-fluid" src="<c:url
                value="/${model.imgPath}"/>"/></td>
                <td class="col">${model.basicPrice}</td>
                <td class="col">
            <c:forEach items="${model.availableOptions}" var="option">
                ${option.value.name}<br>
            </c:forEach>
                </td>
                <td class="col">
                        <c:url value="/models/editModel.action" var="editModelAction"/>
                        <FORM id="form-" action="${editModelAction}" method="post">
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
