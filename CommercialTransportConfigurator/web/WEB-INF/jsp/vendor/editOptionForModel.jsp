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


    <div class="container-fluid">
        <div class="row">
            <div class="col-2"></div>
            <div class="col-6">
                    <table class="table container">
                        <thead>
                        <tr class="row">
                            <th class="col" scope="col">OptionValue Name</th>
                            <th class="col" scope="col">OptionValue
                                Description
                            </th>
                            <th class="col" scope="col">OptionValue Price</th>
                            <th class="col" scope="col">Active</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:url value="/models/saveOptionForModel.action"
                               var="saveAction"/>
                        <c:forEach items="${activeOptions.key}"
                                   var="option">
                        <tr class="row">
                            <form action="${saveAction}" method="post" role="form">
                            <th class="col form-group" scope="row">
                                <div class="input-group">
                                    <INPUT type="hidden" name="value"
                                           value="${option.key.value}">
                                </div>
                            </th>

                            <th class="col form-group" scope="row">
                                <div class="input-group">
                                    <INPUT type="hidden" name="description"
                                           value="${option.key.description}">
                                </div>
                            </th>

                            <th class="col form-group" scope="row">
                                <div class="input-group">
                                    <INPUT type="hidden" name="price"
                                           value="${option.key.price}">
                                </div>
                            </th>

                            <th class="col form-group" scope="row">
                                <div class="input-group">
                                    <INPUT type="hidden" name="isActive"
                                           value="${option.value}">
                                </div>
                            </th>
                            <th class="col form-group" scope="row">
                                <button type="submit"
                                        class="btn btn-success btn-block btn-lg">update
                                    option data
                                </button>
                            </th>
                            </form>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
            </div>
            <div class="col-2"></div>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
</body>
</html>