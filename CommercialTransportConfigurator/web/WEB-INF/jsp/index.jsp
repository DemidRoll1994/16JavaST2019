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
    <script src="/resources/js/loginForm.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


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
                            <a class="nav-link" href="#">Hello, {$authorizedUser.name}</a>
                        </li>
                    </ul>


                    <a href="#" class="btn btn-link"
                       role="button">Регистрация</a>

                    <a href="#" class="btn btn-success" role="button">Войти</a>
                    <link rel="stylesheet"
                          href="https://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>


                    <style>
                        @charset "UTF-8";
                        .animated {
                            -webkit-animation-duration: 1s;
                            -moz-animation-duration: 1s;
                            -o-animation-duration: 1s;
                            animation-duration: 1s;
                            -webkit-animation-fill-mode: both;
                            -moz-animation-fill-mode: both;
                            -o-animation-fill-mode: both;
                            animation-fill-mode: both;
                        }

                        .animated.hinges {
                            -webkit-animation-duration: 2s;
                            -moz-animation-duration: 2s;
                            -o-animation-duration: 2s;
                            animation-duration: 2s;
                        }

                        .animated.slow {
                            -webkit-animation-duration: 3s;
                            -moz-animation-duration: 3s;
                            -o-animation-duration: 3s;
                            animation-duration: 3s;
                        }

                        .animated.snail {
                            -webkit-animation-duration: 4s;
                            -moz-animation-duration: 4s;
                            -o-animation-duration: 4s;
                            animation-duration: 4s;
                        }

                        @-webkit-keyframes shake {
                            0%, 100% {
                                -webkit-transform: translateX(0);
                            }
                            10%, 30%, 50%, 70%, 90% {
                                -webkit-transform: translateX(-10px);
                            }
                            20%, 40%, 60%, 80% {
                                -webkit-transform: translateX(10px);
                            }
                        }

                        @-moz-keyframes shake {
                            0%, 100% {
                                -moz-transform: translateX(0);
                            }
                            10%, 30%, 50%, 70%, 90% {
                                -moz-transform: translateX(-10px);
                            }
                            20%, 40%, 60%, 80% {
                                -moz-transform: translateX(10px);
                            }
                        }

                        @-o-keyframes shake {
                            0%, 100% {
                                -o-transform: translateX(0);
                            }
                            10%, 30%, 50%, 70%, 90% {
                                -o-transform: translateX(-10px);
                            }
                            20%, 40%, 60%, 80% {
                                -o-transform: translateX(10px);
                            }
                        }

                        @keyframes shake {
                            0%, 100% {
                                transform: translateX(0);
                            }
                            10%, 30%, 50%, 70%, 90% {
                                transform: translateX(-10px);
                            }
                            20%, 40%, 60%, 80% {
                                transform: translateX(10px);
                            }
                        }

                        .shake {
                            -webkit-animation-name: shake;
                            -moz-animation-name: shake;
                            -o-animation-name: shake;
                            animation-name: shake;
                        }

                        .login .modal-dialog {
                            width: 350px;
                        }

                        .login .modal-footer {
                            border-top: 0;
                            margin-top: 0px;
                            padding: 10px 20px 20px;
                        }

                        .login .modal-header {
                            border: 0 none;
                            padding: 15px 15px 15px;
                            /* padding: 11px 15px; */
                        }

                        .login .modal-body {
                            /* background-color: #eeeeee; */
                        }

                        .login .division {
                            float: none;
                            margin: 0 auto 18px;
                            overflow: hidden;
                            position: relative;
                            text-align: center;
                            width: 100%;
                        }

                        .login .division .line {
                            border-top: 1px solid #DFDFDF;
                            position: absolute;
                            top: 10px;
                            width: 34%;
                        }

                        .login .division .line.l {
                            left: 0;
                        }

                        .login .division .line.r {
                            right: 0;
                        }

                        .login .division span {
                            color: #424242;
                            font-size: 17px;
                        }

                        .login .box .social {
                            float: none;
                            margin: 0 auto 30px;
                            text-align: center;
                        }

                        .login .social .circle {
                            background-color: #EEEEEE;
                            color: #FFFFFF;
                            border-radius: 100px;
                            display: inline-block;
                            margin: 0 17px;
                            padding: 15px;
                        }

                        .login .social .circle .fa {
                            font-size: 16px;
                        }

                        .login .social .facebook {
                            background-color: #455CA8;
                            color: #FFFFFF;
                        }

                        .login .social .google {
                            background-color: #F74933;
                        }

                        .login .social .github {
                            background-color: #403A3A;
                        }

                        .login .facebook:hover {
                            background-color: #6E83CD;
                        }

                        .login .google:hover {
                            background-color: #FF7566;
                        }

                        .login .github:hover {
                            background-color: #4D4D4d;;
                        }

                        .login .forgot {
                            color: #797979;
                            margin-left: 0;
                            overflow: hidden;
                            text-align: center;
                            width: 100%;
                        }

                        .login .btn-login, .registerBox .btn-register {
                            background-color: #00BBFF;
                            border-color: #00BBFF;
                            border-width: 0;
                            color: #FFFFFF;
                            display: block;
                            margin: 0 auto;
                            padding: 15px 50px;
                            text-transform: uppercase;
                            width: 100%;
                        }

                        .login .btn-login:hover, .registerBox .btn-register:hover {
                            background-color: #00A4E4;
                            color: #FFFFFF;
                        }

                        .login .form-control {
                            border-radius: 3px;
                            background-color: rgba(0, 0, 0, 0.09);
                            box-shadow: 0 1px 0px 0px rgba(0, 0, 0, 0.09) inset;
                            color: #FFFFFF;
                        }

                        .login .form-control:hover {
                            background-color: rgba(0, 0, 0, .16);
                        }

                        .login .form-control:focus {
                            box-shadow: 0 1px 0 0 rgba(0, 0, 0, 0.04) inset;
                            background-color: rgba(0, 0, 0, 0.23);
                            color: #FFFFFF;
                        }

                        .login .box .form input[type="text"], .login .box .form input[type="password"] {
                            border-radius: 3px;
                            border: none;
                            color: #333333;
                            font-size: 16px;
                            height: 46px;
                            margin-bottom: 5px;
                            padding: 13px 12px;
                            width: 100%;
                        }


                        @media (max-width: 400px) {
                            .login .modal-dialog {
                                width: 100%;
                            }
                        }
                    </style>

                    <div class="container">
                        <div class="row">

                            <div class="col-md-4 col-md-offset-4">
                                <a class="btn btn-default" data-toggle="modal"
                                   href="javascript:void(0)"
                                   onclick="openLoginModal();">Log in</a>
                                <a class="btn btn-default" data-toggle="modal"
                                   href="javascript:void(0)"
                                   onclick="openRegisterModal();">Register</a>
                            </div>

                        </div>
                    </div>


                    <div class="modal fade login" id="loginModal">
                        <div class="modal-dialog login animated">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close"
                                            data-dismiss="modal"
                                            aria-hidden="true">&times;
                                    </button>
                                    <h4 class="modal-title">Login with</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="box">
                                        <div class="content">
                                            <div class="social">
                                                <a class="circle github"
                                                   href="#">
                                                    <i class="fa fa-github fa-fw"></i>
                                                </a>
                                                <a id="google_login"
                                                   class="circle google"
                                                   href="#">
                                                    <i class="fa fa-google-plus fa-fw"></i>
                                                </a>
                                                <a id="facebook_login"
                                                   class="circle facebook"
                                                   href="#">
                                                    <i class="fa fa-facebook fa-fw"></i>
                                                </a>
                                            </div>
                                            <div class="division">
                                                <div class="line l"></div>
                                                <span>or</span>
                                                <div class="line r"></div>
                                            </div>
                                            <div class="error"></div>
                                            <div class="form loginBox">
                                                <form method="" action=""
                                                      accept-charset="UTF-8">
                                                    <input id="email"
                                                           class="form-control"
                                                           type="text"
                                                           placeholder="Email"
                                                           name="email">
                                                    <input id="password"
                                                           class="form-control"
                                                           type="password"
                                                           placeholder="Password"
                                                           name="password">
                                                    <input class="btn btn-default btn-login"
                                                           type="button"
                                                           value="Login"
                                                           onclick="loginAjax()">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="box">
                                        <div class="content registerBox"
                                             style="display:none;">
                                            <div class="form">
                                                <form method=""
                                                      html="{:multipart=>true}"
                                                      data-remote="true"
                                                      action=""
                                                      accept-charset="UTF-8">
                                                    <input id="email"
                                                           class="form-control"
                                                           type="text"
                                                           placeholder="Email"
                                                           name="email">
                                                    <input id="password"
                                                           class="form-control"
                                                           type="password"
                                                           placeholder="Password"
                                                           name="password">
                                                    <input id="password_confirmation"
                                                           class="form-control"
                                                           type="password"
                                                           placeholder="Repeat Password"
                                                           name="password_confirmation">
                                                    <input class="btn btn-default btn-register"
                                                           type="button"
                                                           value="Create account"
                                                           name="commit">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <div class="forgot login-footer">
 <span>Looking to
 <a href="javascript: showRegisterForm();">create an account</a>
 ?</span>
                                    </div>
                                    <div class="forgot register-footer"
                                         style="display:none">
                                        <span>Already have an account?</span>
                                        <a href="javascript: showLoginForm();">Login</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <script>
                        $(document).ready(function () {
                            openLoginModal();
                        });
                    </script>

                    </form>

                </div>
            </div>
        </nav>
    </div>

</header>
<!-- ОСНОВНОЕ СОДЕРЖИМОЕ -->
<main>
    <br><br><br>
    <p><a href="#myModal1" class="btn btn-primary" data-toggle="modal">Открыть
        модальное окно 1</a></p>
    <div id="myModal1" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Введите логин и пароль</h4>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                </div>
                <div class="modal-body">
                    Введите логин и пароль
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Вход
                    </button>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">Регистрация
                    </button>
                </div>
            </div>
        </div>
    </div>



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


<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>


</body>
</html>
