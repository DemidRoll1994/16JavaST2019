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
    <div style="text-align: center;">
        <form action="${registerUrl}" method="post" id="Signup-Form"
              role="form">

            <div class="form-group">
                <div class="input-group">
                    <input name="name" id="name"
                           class="form-control input-lg"
                           placeholder="Enter name" required
                           data-parsley-pattern="^[a-zа-яA-ZА-ЯёўіЁЎІ]{2,255}$"
                           data-parsley-trigger="keyup">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input name="surname" id="surname"
                           class="form-control input-lg"
                           placeholder="Enter surname" required
                           data-parsley-pattern="^([a-zа-яA-ZА-ЯёўіЁЎІ]+[',.-]?[a-zа-яA-ZА-ЯёўіЁЎІ]+){1,127}$"
                           data-parsley-trigger="keyup">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input name="email" id="signup-email"
                           type="email"
                           class="form-control input-lg"
                           placeholder="Enter Email" required
                           data-parsley-type="email">
                </div>
            </div>


            <div class="form-group">
                <div class="input-group">
                    <input name="companyName" id="companyName"
                           class="form-control input-lg"
                           placeholder="Enter your company name">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input name="phoneNumber" id="phoneNumber"
                           data-parsley-type="digits"
                           class="form-control input-lg"
                           placeholder="Enter your phone">
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <input name="address" id="address"
                           class="form-control input-lg"
                           placeholder="Enter your company address">
                </div>
            </div>
            <button type="submit"
                    class="btn btn-success btn-block btn-lg">update data!
            </button>
        </form>
    </div>
</main>
<jsp:include page="../footer.jsp"/>
</body>
</html>
