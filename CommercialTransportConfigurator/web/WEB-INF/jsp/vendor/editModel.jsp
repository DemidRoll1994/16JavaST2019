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
                <form action="${saveModel}" method="post" id="Signup-Form"
                      role="form">
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
                    <div class="form-group">

                            ///////todo на эту странциу нужно передавтаь список
                            всех вариантов для этой
                            опции и выбраные варианты. то есть нужнопоменять
                            передаваемые на страницу данные
                            <%--> creates multiple choice box for every option that can be assigned to the model (all options) <--%>
                            <c:forEach items="${fullOptions}"
                                       var="fullOption">
                                <select multiple class="form-control"
                                        id="exampleFormControlSelect2">

                                        <%--> creates all option values for option<--%>
                                    <c:forEach
                                            items="${model.availableOptions.value}"
                                            var="selectedOption">
                                        <c:if test="${fullOption.id
                                                  == selectedOption.id}">
                                            <option
                                                    <c:if test="${option == user.status}">
                                                        selected
                                                    </c:if>
                                            >${option.value}</option>

                                        </c:if>
                                    </c:forEach>
                                    <option
                                            <c:if test="${option == user.status}">
                                                selected
                                            </c:if>
                                    >${option.value}</option>


                                    <c:forEach
                                            items="${model.availableOptions.value}"
                                            var="selectedOption">
                                        <c:if test="${fullOption.id
                                                  == selectedOption.id}">
                                            <option
                                                    <c:if test="${option == user.status}">
                                                        selected
                                                    </c:if>
                                            >${option.value}</option>

                                        </c:if>
                                    </c:forEach>
                                    <option
                                            <c:if test="${option == user.status}">
                                                selected
                                            </c:if>
                                    >${option.value}</option>
                                </select>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">Example
                                textarea</label>
                            <textarea class="form-control"
                                      id="exampleFormControlTextarea1"
                                      rows="3"></textarea>
                        </div>


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
                </div>-->
                    <button type="submit"
                            class="btn btn-success btn-block btn-lg">update
                        model data
                    </button>
                </form>
            </div>
            <div class="col-4"></div>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
</body>
</html>