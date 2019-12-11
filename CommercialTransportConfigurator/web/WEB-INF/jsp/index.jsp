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
                            <a class="nav-link" href="#">Автомобили в наличии</a>
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
                                <a class="dropdown-item" href="#">Конфигурация 1</a>
                                <a class="dropdown-item" href="#">Конфигурация 2</a>
                                <a class="dropdown-item" href="#">Конфигурация 3</a>
                            </div>
                        </li>
                    </ul>


                    <a href="#" class="btn btn-link"
                       role="button">Регистрация</a>

                    <a href="#" class="btn btn-success" role="button">Войти</a>
                    </form>

                </div>
            </div>
        </nav>
    </div>

</header>
<!-- ОСНОВНОЕ СОДЕРЖИМОЕ -->
<main>
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
