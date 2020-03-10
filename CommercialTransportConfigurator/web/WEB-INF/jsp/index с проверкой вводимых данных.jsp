<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/superhero/bootstrap.min.css"
          rel="stylesheet">
    <style>
        body {
            padding-top: 70px;
        }
        .modal-header, .modal-body, .modal-footer{
            padding: 25px;
        }
        .modal-footer{
            text-align: center;
        }
        #signup-modal-content, #forgot-password-modal-content{
            display: none;
        }

        /** Валидация */

        input.parsley-error{
            border-color:#843534;
            box-shadow: none;
        }
        input.parsley-error:focus{
            border-color:#843534;
            box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483
        }
        .parsley-errors-list.filled {
            opacity: 1;
            color: #a94442;
            display: none;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script>
        $(document).ready(function(){
            <%--$('#Login-Form').parsley();
            $('#Signup-Form').parsley();
            $('#Forgot-Password-Form').parsley();--%>
            $('#signupModal').click(function(){
                $('#login-modal-content').fadeOut('fast', function(){
                    $('#signup-modal-content').fadeIn('fast');
                });
            });

            $('#loginModal').click(function(){
                $('#signup-modal-content').fadeOut('fast', function(){
                    $('#login-modal-content').fadeIn('fast');
                });
            });

            $('#FPModal').click(function(){
                $('#login-modal-content').fadeOut('fast', function(){
                    $('#forgot-password-modal-content').fadeIn('fast');
                });
            });

            $('#loginModal1').click(function(){
                $('#forgot-password-modal-content').fadeOut('fast', function(){
                    $('#login-modal-content').fadeIn('fast');
                });
            });

        });
    </script>
</head>
<body>
<!-- HEADER -->
<header>


    <div class="row">
        <nav class="fixed-top navbar navbar-expand navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#"><span
                        class="glyphicon glyphicon-cog"
                        aria-hidden="true"></span> Конфигуратор </a>
                <button class="navbar-toggler" type="button"
                        data-toggle="collapse" data-target="#navbarsExample07"
                        aria-controls="navbarsExample07" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExample07">
                    <ul class="navbar-nav mr-auto">
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
                        <li class="nav-item">
                            <a class="nav-link" href="#">Hello, {$authorizedUser.login}</a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="btn btn-link" role="button">Регистрация</a>
                        </li>
                        <li class="nav-item">
                            <a href="#login-signup-modal" class="btn btn-primary" data-toggle="modal">Login</a>
                        </li>
                    </ul>


                </div>
            </div>
        </nav>
    </div>

    <div id="login-signup-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">

            <!-- содержимое модального окна login -->
            <div class="modal-content" id="login-modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-lock"></span> Login Now!</h4>
                </div>

                <c:url value="/register.html" var="registerUrl"/>
                <c:url value="/login.html" var="loginUrl"/>
                <c:url value="/forgetPass.html" var="forgetPassUrl"/>
                <div class="modal-body">
                    <form action="${loginUrl}" method="post" id="Login-Form" role="form">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
                                <input name="login" id="login" type="login" class="form-control input-lg" placeholder="Enter Login" >
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                                <input name="password" id="login-password" type="password" class="form-control input-lg" placeholder="Enter Password" required data-parsley-length="[6, 99]" data-parsley-trigger="keyup">
                            </div>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" value="" checked>Remember me</label>
                        </div>
                        <button type="submit" class="btn btn-success btn-block btn-lg">LOGIN</button>
                    </form>
                </div>

                <div class="modal-footer">
                    <p>
                        <a id="FPModal" href="javascript:void(0)">Forgot Password?</a> |
                        <a id="signupModal" href="javascript:void(0)">Register Here!</a>
                    </p>
                </div>

            </div>
            <!-- содержимое модального окна login -->

            <!-- содержимое модального окна signup -->
            <div class="modal-content" id="signup-modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-lock"></span> Signup Now!</h4>
                </div>

                <div class="modal-body">
                    <form action="${registerUrl}" method="post" id="Signup-Form" role="form">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
                                <input name="email" id="email" type="email" class="form-control input-lg" placeholder="Enter Email" required data-parsley-type="email">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                                <input name="password" id="passwd" type="password" class="form-control input-lg" placeholder="Enter Password" required data-parsley-length="[6, 10]" data-parsley-trigger="keyup">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                                <input name="password" id="confirm-passwd" type="password" class="form-control input-lg" placeholder="Retype Password" required data-parsley-equalto="#passwd" data-parsley-trigger="keyup">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-success btn-block btn-lg">CREATE ACCOUNT!</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <p>Already a Member ? <a id="loginModal" href="javascript:void(0)">Login Here!</a></p>
                </div>
            </div>
            <!-- содержимое модального окна signup -->

            <!-- содержимое модального окна forgot password -->
            <div class="modal-content" id="forgot-password-modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-lock"></span> Recover Password!</h4>
                </div>

                <div class="modal-body">
                    <form action="${forgotPassUrl}" method="post" id="Forgot-Password-Form" role="form">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
                                <input name="email" id="email" type="email" class="form-control input-lg" placeholder="Enter Email" required data-parsley-type="email">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-success btn-block btn-lg">
                            <span class="glyphicon glyphicon-send"></span> SUBMIT
                        </button>
                    </form>
                </div>

                <div class="modal-footer">
                    <p>Remember Password ? <a id="loginModal1" href="javascript:void(0)">Login Here!</a></p>
                </div>

            </div>
            <!-- содержимое модального окна forgot password -->

        </div>
    </div>





</header>
<!-- ОСНОВНОЕ СОДЕРЖИМОЕ -->
<main>
    <br><br><br>
<%--
    <c:out value="${sessionScope.authorizedUser.login}" />
    Hello, ${sessionScope.authorizedUser.login}
    <H2>Вход в систему</H2>
    <c:url value="/login.html" var="loginUrl"/>
    <FORM action="${loginUrl}" method="post">
        <LABEL for="login">Имя пользователя:</LABEL>
        <INPUT type="text" id="login" name="login" value="${param.login}">
        <LABEL for="password">Пароль:</LABEL>
        <INPUT type="password" id="password" name="password">
        <BUTTON type="submit">Войти</BUTTON>
    </FORM>




    <H2>Регистрация:</H2>
    <c:url value="/register.html" var="registerUrl"/>
    <FORM action="${registerUrl}" method="post">
        <LABEL for="reglogin">Имя пользователя:</LABEL>
        <INPUT type="text" id="reglogin" name="login" value="${param.login}">
        <LABEL for="regemail">e-mail:</LABEL>
        <INPUT type="text" id="regemail" name="email">
        <LABEL for="regpassword">Пароль:</LABEL>
        <INPUT type="password" id="regpassword" name="password">
        <BUTTON type="submit">Войти</BUTTON>
    </FORM>
--%>


    <div class="row">

        <div class="col-2"></div>
        <div class="col-8">

            <div id="carouselIndicators" class="carousel slide"
                 data-ride="carousel" align="center">
                <ol class="carousel-indicators">
                    <li data-target="#carouselIndicators"
                        data-slide-to="0" class="active"></li>
                    <li data-target="#carouselIndicators"
                        data-slide-to="1"></li>
                    <li data-target="#carouselIndicators"
                        data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block img-fluid"
                             src="<c:url
                             value="/resources/img/index-bg-1.jpg"/>"
                             alt="Первый слайд">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid"
                             src="<c:url
                             value="/resources/img/index-bg-2.jpg"/>"
                             alt="Второй слайд">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid"
                             src="<c:url
                             value="/resources/img/index-bg-3.jpg"/>"
                             alt="Второй слайд">
                    </div>
                </div>
                <a class="carousel-control-prev"
                   href="#carouselIndicators" role="button"
                   data-slide="prev">
                    <span class="carousel-control-prev-icon"
                          aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next"
                   href="#carouselIndicators" role="button"
                   data-slide="next">
                    <span class="carousel-control-next-icon"
                          aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <!--
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a href="#" class="navbar-brand">название меню</a>
                    </div>
                    <div>
                        <ul class ="nav navbar-nav">
                            <li><a href="#"> войти</a> </li>
                            <li><a href="#"> выйти</a> </li>
                            <li><a href="#"> войти и выйти</a> </li>
                        </ul>
                    </div>
                </div>
            </nav>
            -->


        </div>
        <div class="col-2"></div>
    </div>


</main>
<ctg:footer/>

</body>
</html>
