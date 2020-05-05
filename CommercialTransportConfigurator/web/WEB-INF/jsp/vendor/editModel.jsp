<%@ page import="by.samtsov.bean.type.UserStatus" %>
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
                <c:url value="/models/saveModel.action" var="saveModel"/>
                <form action="${saveModel}" method="post" role="form">
                    <div class="form-group">
                        <div class="input-group">
                            <INPUT type="hidden" name="modelId"
                                   value="${model.id}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <input name="name" id="name"
                                   class="form-control input-sm"
                                   placeholder="Enter name" required
                                   value="${model.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <input name="basicPrice" id="basicPrice"
                                   class="form-control input-sm"
                                   placeholder="Enter basic price" required
                                   type="text" min="0" max="20000" step="0.01"
                                   data-parsley-validation-threshold="1"
                                   data-parsley-trigger="keyup"
                                   data-parsley-type="number"
                                   value="${model.basicPrice}"/>
                        </div>
                    </div>


                    <%-->
                                        <div class="form-group">

                                            ///////todo на эту странциу нужно передавтаь список
                                            всех вариантов для этой
                                            опции и выбраные варианты. то есть нужнопоменять
                                            передаваемые на страницу данные
                                            <%--> creates multiple choice box for every option that can be assigned to the model (all options) <-->
                        <c:forEach items="${fullOptions}" var="fullOption">

                            <select name="selectedOptionValues"
                                    multiple class="form-control"
                                    id="exampleFormControlSelect2">
                                    <%--> creates all option values for option<-->

                                <c:forEach items="${fullOption.optionValues}"
                                           var="optionValue">
                                    <option

                                            <c:forEach var="availableOptionValue"
                                                       items="${model.availableOptions.value}">
                                                <script>alert("${availableOptionValue.id} , ${optionValue.id}");</script>
                                                <c:if test="${availableOptionValue.id == optionValue.id}">
                                                    selected
                                                </c:if>
                                            </c:forEach>


                                    >${optionValue.description}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>


                        Options:
                        <div class="input-group">
                            <input name="basicPrice" id="basicPrice"
                                   class="form-control input-sm"
                                   placeholder="Enter basic price" required
                                   type="text" min="0" max="20000" step="0.01"
                                   data-parsley-validation-threshold="1"
                                   data-parsley-trigger="keyup"
                                   data-parsley-type="number"
                                   value="${model.basicPrice}"/>
                        </div>
                    </div>


                    <!--
        <div class="form-group">
            <div class="input-group">
                <select name="status"
                        class="form-control form-control-sm"
                        id="status">

                    <c:forEach items="<%=UserStatus.values()%>"
                               var="status">
                        <option
                                <c:if test="${status == user.status}">
                                    selected
                                </c:if>
                        >${status}</option>
                    </c:forEach>
                </select>
            </div>
        </div>--%>
                    <button type="submit"
                            class="btn btn-success btn-block btn-lg">update
                        model data
                    </button>
                </form>


                <table class="table container">
                    <thead>
                    <tr class="row">
                        <th class="col" scope="col">Option Name</th>
                        <th class="col" scope="col">Option Value</th>
                        <th class="col" scope="col">Option Value Description</th>
                        <th class="col" scope="col">Edit</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${model.availableOptions}" var="option">
                        <tr class="row">
                            <th class="col"scope="row"><c:out value="${option.value.name}"/></th>
                            <td class="col">
                                <c:forEach items="${option.value.optionValues}"
                                           var="optionValue">
                                    ${optionValue.value}<br>
                                </c:forEach>
                            </td>
                            <td class="col">
                                <c:forEach items="${option.value.optionValues}"
                                           var="optionValue">
                                    ${optionValue.description}<br>
                                </c:forEach>
                            </td>
                            <td class="col">
                                <c:url value="/options/editOptionForModel.action"
                                       var="editOptionForModel"/>
                                <FORM action="${editOptionForModel}"
                                      method="post">
                                    <INPUT type="hidden" name="modelId" value="${model.id}">
                                    <INPUT type="hidden" name="optionId" value="${option.value.id}">
                                    <BUTTON type="submit" class="btn btn-warning">Редактировать</BUTTON>
                                </FORM>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4"></div>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
</body>
</html>