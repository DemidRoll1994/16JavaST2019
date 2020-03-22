<%--
  Header for all pages

  Created by samtsov
  Date: 17.03.2020
  Time: 15:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/superhero/bootstrap.min.css"
      rel="stylesheet">

<c:url value="/resources/css/validation.css" var="validationCssURL"/>
<link rel="stylesheet" type="text/css" href="${validationCssURL}"/>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.9.2/parsley.js"></script>
<script>
    $(document).ready(function () {
        $('#Login-Form').parsley();
        $('#Signup-Form').parsley();
        $('#Forgot-Password-Form').parsley();
        $('#signupModal').click(function () {
            $('#login-modal-content').fadeOut('fast', function () {
                $('#signup-modal-content').fadeIn('fast');
            });
        });

        $('#loginModal').click(function () {
            $('#signup-modal-content').fadeOut('fast', function () {
                $('#login-modal-content').fadeIn('fast');
            });
        });

        $('#FPModal').click(function () {
            $('#login-modal-content').fadeOut('fast', function () {
                $('#forgot-password-modal-content').fadeIn('fast');
            });
        });

        $('#loginModal1').click(function () {
            $('#forgot-password-modal-content').fadeOut('fast', function () {
                $('#login-modal-content').fadeIn('fast');
            });
        });

    });
</script>

<header>
    <div></div>
    <nav class="fixed-top navbar navbar-expand-lg navbar-dark bg-dark row"
         style="align-content: center;">

        <c:url value="/index.action" var="itemUrl"/>
        <a class="navbar-brand" href="${itemUrl}"><span
                class="glyphicon glyphicon-cog "
                aria-hidden="true"></span> Конфигуратор </a>
        <button class="navbar-toggler" type="button"
                data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav mr-auto ">
                <li class="nav-item">
                    <a class="nav-link" href="#">Модельный ряд</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Сервисы и допуслуги</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Автомобили в
                        наличии</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Финансовые решения</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"
                       href="#" id="dropdown07"
                       data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">Заказ</a>
                    <div class="dropdown-menu"
                         aria-labelledby="dropdown07">
                        <a class="dropdown-item" href="#">Конфигурация
                            1</a>
                        <a class="dropdown-item" href="#">Конфигурация
                            2</a>
                        <a class="dropdown-item" href="#">Конфигурация
                            3</a>
                    </div>
                </li>


                <c:choose>
                    <c:when test="${authorizedUser==null}">
                        <li class="nav-item">
                            <a href="#login-signup-modal"
                               class="btn btn-success" data-toggle="modal">
                                Login</a>
                        </li>
                    </c:when>
                    <c:when test="${authorizedUser!=null}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle"
                               href="#" id="dropdown08"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">Hello, ${authorizedUser.name}!</a>
                            <div class="dropdown-menu"
                                 aria-labelledby="dropdown08">
                                <c:url value="/profile/edit.action"
                                       var="editProfile"/>
                                <a class="dropdown-item" href="${editProfile}">Edit
                                    profile</a>
                                <c:url value="/logout.action" var="logout"/>
                                <a class="dropdown-item btn btn-info"
                                   href="${logout}">Logout</a>
                            </div>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </div>


    </nav>


    <c:url value="/register.action" var="registerUrl"/>
    <c:url value="/login.action" var="loginUrl"/>
    <c:url value="/forgetPass.action" var="forgetPassUrl"/>

    <div id="login-signup-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">

            <!-- содержимое модального окна login -->
            <div class="modal-content" id="login-modal-content">

                <div class="modal-header">
                    <h4 class="modal-title"><span
                            class="glyphicon glyphicon-lock"></span> Login Now!
                    </h4>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                </div>

                <div class="modal-body">
                    <form action="${loginUrl}" method="post" id="Login-Form"
                          role="form">
                        <div class="form-group">
                            <div class="input-group">
                                <input name="email" id="login-email"
                                       type="email"
                                       class="form-control input-lg"
                                       placeholder="Enter Login">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input name="password" id="login-password"
                                       type="password"
                                       class="form-control input-lg"
                                       placeholder="Enter Password" required
                                       data-parsley-pattern="(?=.*[0-9])(?=.*[!-\\/:-@\\[-`{-~])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!-\\/:-@\\[-`{-~]{6,}"
                                       data-parsley-trigger="keyup">
                            </div>
                        </div>
                        <button type="submit"
                                class="btn btn-success btn-block btn-lg">LOGIN
                        </button>
                    </form>
                </div>

                <div class="modal-footer">
                    <p>
                        <a id="FPModal" href="javascript:void(0)">Forgot
                            Password?</a> |
                        <a id="signupModal" href="javascript:void(0)">Register
                            Here!</a>
                    </p>
                </div>

            </div>
            <!-- содержимое модального окна login -->

            <!-- содержимое модального окна signup -->
            <div class="modal-content" id="signup-modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Signup Now!</h4>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                </div>

                <div class="modal-body">
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
                                <input name="password" id="passwd"
                                       type="password"
                                       class="form-control input-lg"
                                       placeholder="Enter Password" required
                                       data-parsley-pattern="(?=.*[0-9])(?=.*[!-\\/:-@\\[-`{-~])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!-\\/:-@\\[-`{-~]{6,}"
                                       data-parsley-trigger="keyup">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input name="password" id="confirm-passwd"
                                       type="password"
                                       class="form-control input-lg"
                                       placeholder="Retype Password" required
                                       data-parsley-equalto="#passwd"
                                       data-parsley-trigger="keyup">
                            </div>
                        </div>
                        <button type="submit"
                                class="btn btn-success btn-block btn-lg">CREATE
                            ACCOUNT!
                        </button>
                    </form>
                </div>
                <div class="modal-footer">
                    <p>Already a Member ? <a id="loginModal"
                                             href="javascript:void(0)">Login
                        Here!</a></p>
                </div>
            </div>
            <!-- содержимое модального окна signup -->

            <!-- содержимое модального окна forgot password -->
            <div class="modal-content" id="forgot-password-modal-content">

                <div class="modal-header">
                    <h4 class="modal-title"> Recover Password!</h4>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                </div>

                <div class="modal-body">
                    <form action="${forgotPassUrl}" method="post"
                          id="Forgot-Password-Form" role="form">
                        <div class="form-group">
                            <div class="input-group">
                                <input name="email" id="forgot-email"
                                       type="email"
                                       class="form-control input-lg"
                                       placeholder="Enter Email" required
                                       data-parsley-type="email">
                            </div>
                        </div>
                        <button type="submit"
                                class="btn btn-success btn-block btn-lg">
                            SUBMIT
                        </button>
                    </form>
                </div>

                <div class="modal-footer">
                    <p>Remember Password ? <a id="loginModal1"
                                              href="javascript:void(0)">Login
                        Here!</a></p>
                </div>
            </div>
            <!-- содержимое модального окна forgot password -->
        </div>
    </div>

</header>

<br><br><br>
