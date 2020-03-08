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
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


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
                    </ul>


                    <a href="#" class="btn btn-link"
                       role="button">Регистрация</a>

                    <a href="#" class="btn btn-success" role="button">Войти</a>
                    <link rel="stylesheet"
                          href="https://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
                </div>
            </div>
        </nav>
    </div>

</header>
<!-- ОСНОВНОЕ СОДЕРЖИМОЕ -->
<main>


    <H1>ЯРОР</H1>
    ${error}

</main>
<ctg:footer/>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>


</body>
</html>
