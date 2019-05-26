<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Elective</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Vollkorn&display=swap" rel="stylesheet">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet"/>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="/js/script.js"></script>


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <a class="navbar-brand" href="#">Elective</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Categories
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Programming</a>
                    <a class="dropdown-item" href="#">Design</a>
                    <a class="dropdown-item" href="#">Math</a>
                    <a class="dropdown-item" href="#">Algorithms</a>
                    <!--<div class="dropdown-divider"></div>-->
                    <a class="dropdown-item" href="#">Architecture</a>
                    <a class="dropdown-item" href="#">Business</a>
                    <a class="dropdown-item" href="#">Cloud Computing</a>
                    <a class="dropdown-item" href="#">Social Sciences</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <div class="input-group">
                <input type="search" class="form-control py-0 px-0" placeholder="Search">
                <div class="input-group-btn">
                    <button class="btn btn-dark" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
        <button class="btn btn-outline-primary mx-2">Login</button>
        <button class="btn btn-outline-primary mx-2">Signup</button>
    </div>
</nav>

<div class="col-md-12 extended-search">
    <hr>
    <form>
        <div class="row">
            <div class="col">
                <select name="skill" class="form-control">
                    <option value="not_selected">Skill</option>
                    <option value="javascript">JavaScript</option>
                    <option value="html">HTML</option>
                    <option value="css">CSS</option>
                    <option value="css">Java</option>
                    <option value="css">Python</option>
                    <option value="css">OOP</option>
                </select>
            </div>
            <div class="col">
                <select name="level" class="form-control">
                    <option value="not_selected">Level</option>
                    <option value="beginner">Beginner</option>
                    <option value="intermediate">Intermediate</option>
                    <option value="for_all">For all</option>
                </select>
            </div>
            <div class="col">
                <select name="language" class="form-control">
                    <option value="ua">Language</option>
                    <option value="ua">Українська</option>
                    <option value="en">English</option>
                    <option value="ru">Русский</option>
                </select>
            </div>
            <div class="col">
                <select name="duration" class="form-control">
                    <option value="0-2">Duration</option>
                    <option value="0-2">0-2h</option>
                    <option value="3-7">3-6h</option>
                    <option value="8-16">7-12h</option>
                    <option value="17+">17+h</option>
                </select>
            </div>
        </div>
        <div class="row ">
            <div class="col text-center submit-ex-search">
                <button type="submit" class="btn btn-outline-success" href="#">Apply</button>
            </div>
        </div>
    </form>
</div>
<!-- Main Content -->
<div class="container search-results">
    <div class="row text-center">
        <c:forEach var="course" items="${courses}">
            <div class="col-md-4 course-data mx-auto">
                <div class="course-item text-center">
                    <p><c:out value="${course.name}"/></p>
                    <hr>
                    <div class="course-card">
                        <img alt="" src="<c:out value="${course.imgUrl}"/>">
                    </div>
                    <div class="course-info">
                        <div class="star-raiting">
                    <span class="score">
                        <div class="score-wrap">
                            <span class="stars-active" style="width:<c:out value="${course.ratingInPercents}"/>%">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                            </span>
                            <span class="stars-inactive">
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                            </span>
                        </div>
                    </span>
                        </div>
                        <div class="course-meta">
                            <span><c:out value="${course.rating}"/> (<c:out value="${course.reviews}"/>)</span>
                            <span><c:out value="${course.durationHours}"/>ч</span>
                            <span><c:out value="${course.level}"/></span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <hr>
    <!-- Pager -->
    <div class="col-lg-2 mx-auto text-center">
        <a class="btn btn-outline-primary load-more" href="#">Load more</a>
    </div>
</div>
<hr>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto text-center">
                <ul class="list-inline text-center">
                    <li class="list-inline-item">
                        <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                </span>
                        </a>
                    </li>
                </ul>
                <p class="copyright text-muted">&copy; Elective 2019</p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>