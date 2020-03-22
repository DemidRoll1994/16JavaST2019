<%@ page import="by.samtsov.bean.type.Role" %>
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
    <br>
    <br><br><br><br>

    <c:forEach items="${users}" var="user">
        <div class="row">
            <div class="col-1"></div>
            <div class="col-10">
                <c:url value="/admin/saveUser.action" var="saveProfile"/>
                <form action="${saveProfile}" method="post" id="Signup-Form"
                      role="form">
                    <div class="form-group">
                        <div class="input-group">
                            <input name="name" id="name"
                                   class="form-control input-sm"
                                   placeholder="Enter name" required
                                   data-parsley-pattern="^[a-zа-яA-ZА-ЯёўіЁЎІ]{2,255}$"
                                   data-parsley-trigger="keyup"
                                   value="${user.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <input name="surname" id="surname"
                                   class="form-control input-sm"
                                   placeholder="Enter surname" required
                                   data-parsley-pattern="^([a-zа-яA-ZА-ЯёўіЁЎІ]+[',.-]?[a-zа-яA-ZА-ЯёўіЁЎІ]+){1,127}$"
                                   data-parsley-trigger="keyup"
                                   value="${user.surname}">
                        </div>
                    </div>
                    <div class=" form-group">
                        <div class="input-group">
                            <input name="email" id="signup-email"
                                   type="email"
                                   class="form-control input-sm"
                                   placeholder="Enter Email" required
                                   data-parsley-type="email"
                                   value="${user.email}">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="input-group">
                            <input name="companyName" id="companyName"
                                   class="form-control input-sm"
                                   placeholder="Enter your company name"
                                   value="${user.companyName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <input name="phoneNumber" id="phoneNumber"
                                   data-parsley-type="digits"
                                   class="form-control input-sm"
                                   placeholder="Enter your phone"
                                   value="${user.phoneNumber}">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="input-group">
                            <input name="address" id="address"
                                   class="form-control input-sm"
                                   placeholder="Enter your company address"
                                   value="${user.address}">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="role">Role</label>
                        <select class="form-control form-control-sm" id="role">
                            <c:forEach items="<%=Role.values()%>" var="role">
                                <option
                                        <c:if test="${role == user.role}">
                                            selected
                                        </c:if>
                                >${role}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="role">Role</label>
                        <select class="form-control form-control-sm" id="role">
                            <c:forEach items="<%=Role.values()%>" var="role">
                                <option
                                        <c:if test="${role == user.role}">
                                            selected
                                        </c:if>
                                >${role}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="status">Status</label>
                        <select class="form-control form-control-sm"
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
                    <button type="submit"
                            class="btn btn-success btn-block btn-lg">update
                        user data
                    </button>
                </form>
            </div>
            <div class="col-1"/>
        </div>

    </c:forEach>
</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>
