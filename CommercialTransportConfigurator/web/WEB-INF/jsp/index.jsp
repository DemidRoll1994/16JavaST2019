<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<!-- HEADER -->
<jsp:include page="header.jsp"/>
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
        </div>
        <div class="col-2"></div>
    </div>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
