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


    <div class="col-2"></div>
    <div class="col-6">
        <c:url value="/profile/save.action" var="saveProfile"/>
        <form action="${saveProfile}" method="post" id="Signup-Form"
              role="form">
            <div class="form-group">
                <div class="input-group">
                    <input name="name" id="name"
                           class="form-control input-lg"
                           placeholder="Enter name" required
                           data-parsley-pattern="^[a-zа-яA-ZА-ЯёўіЁЎІ]{2,255}$"
                           data-parsley-trigger="keyup"
                           value="${user.name}">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input name="surname" id="surname"
                           class="form-control input-lg"
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
                           class="form-control input-lg"
                           placeholder="Enter Email" required
                           data-parsley-type="email"
                           value="${user.email}">
                </div>
            </div>


            <div class="form-group">
                <div class="input-group">
                    <input name="companyName" id="companyName"
                           class="form-control input-lg"
                           placeholder="Enter your company name"
                           value="${user.companyName}">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input name="phoneNumber" id="phoneNumber"
                           data-parsley-type="digits"
                           class="form-control input-lg"
                           placeholder="Enter your phone"
                           value="${user.phoneNumber}">
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <input name="address" id="address"
                           class="form-control input-lg"
                           placeholder="Enter your company address"
                           value="${user.address}">
                </div>
            </div>
            <button type="submit"
                    class="btn btn-success btn-block btn-lg">update data!
            </button>
        </form>
    </div>
    <div class="col-4"/>

</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>
