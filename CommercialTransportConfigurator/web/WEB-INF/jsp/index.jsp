<%--
  Created by IntelliJ IDEA.
  User: Артём
  Date: 31.10.19
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf8" %>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" ></script>
    XML Parser
</head>
<body>
<ctg:hello role="${ user }"/>

<form name="parser-chooser" method="post" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/ControllerServlet">

        Select xml-parser:
        <select id="parser" size="1" name="parserName">
            <option value="DOM">DOM</option>
            <option value="SAX">SAX</option>
            <option value="STAX">StAX</option>
        </select>

    <br/>

    <label for="xml-document">Choose a xml-document:</label>
    <input type="file" id="xml-document" name="path"
           accept="application/xml">
    <br/>

    <div style="text-align: left">
        <input type="submit" value="Send">
    </div>
    <br/>


    <c:if test="${!empty tariffs}">
        <table class="data" border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>operator</th>
                <th>payroll</th>
                <th>creationTariffDay</th>
                <th>smsPrice</th>
                <th>prices</th>
                <th>parameters</th>
            </tr>
            <c:forEach items="${tariffs}" var="tariff">
                <tr>
                    <td>${tariff.Id}</td>
                    <td>${tariff.name}</td>
                    <td>${tariff.operator}</td>
                    <td>${tariff.payroll}</td>
                    <td>${tariff.CreationTariffDay}</td>
                    <td>${tariff.getSmsPrice}</td>
                    <td>${tariff.prices}</td>
                    <td>${tariff.parameters}</td>
                </tr>
            </c:forEach>

        </table>
    </c:if>









${errorMessage}
    <br/>
    ${wrongCommand}
    <br/>
    ${nullPage}
    <br/>
</form>

<ctg:info-time />
</body>
</html>
