<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Profile</title>

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
    <a class="navbar-brand" href="/">Elective</a>
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

                    <c:forEach var="category" items="${categories}">
                        <a class="dropdown-item" href="/courses/<c:out value="${category}"/>?lang=<c:out value="${lang}"/>"><c:out value="${category}"/></a>
                    </c:forEach>
                </div>
            </li>
            <c:if test = "${user.role.name() eq 'AUTHOR'}">
                <li class="nav-item">
                    <a href="/courses/create" class="btn btn-outline-info mx-2">Create course</a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="/courses/search">
            <div class="input-group">
                <input type="search" name="searchString" class="form-control py-0 px-0" placeholder="Search">
                <div class="input-group-btn">
                    <button class="btn btn-dark" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
        <div id="lang" class="px-3">
            <a href="#">en</a>
            <select name="langs" style="display: none;">
                <option value="en" selected>en</option>
                <option value="ua">ua</option>
                <option value="ru">ru</option>
            </select>
        </div>
        <c:if test = "${user == null}">
            <a href="/login" class="btn btn-outline-info mx-2">Login</a>
            <a href="/signup" class="btn btn-outline-info mx-2">Signup</a>
        </c:if>

        <c:if test = "${user != null}">
            <div class="dropdown px-3">
                <button class="btn btn-outline-primary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="far fa-user"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                    <c:if test = "${user.role != com.vasylieva.elective.model.status.Role.ADMIN}">
                        <a class="dropdown-item" href="/user/courses">Profile</a>
                    </c:if>
                    <a class="dropdown-item" href="/logout">Log out</a>
                </div>
            </div>
        </c:if>
    </div>
</nav>

<c:if test = "${user.role.name() eq 'AUTHOR'}">
    <div class="col-md-12 menu-tabs">
        <hr>
        <div class="row text-center">
            <div class="col-md mx-auto menu-tab <c:if test = "${status.name() eq 'PUBLISHED'}">active</c:if>"><a class="nav-link" href="/user/courses?status=PUBLISHED">Published</a></div>
            <div class="col-md mx-auto menu-tab <c:if test = "${status.name() eq 'WIP'}">active</c:if>"><a class="nav-link" href="/user/courses?status=WIP">Work in progress</a></div>
        </div>
    </div>
</c:if>

<c:if test = "${user.role.name() eq 'STUDENT'}">
    <div class="col-md-12 menu-tabs">
        <hr>
        <div class="row text-center">
            <div class="col-md mx-auto menu-tab <c:if test = "${status.name() eq 'ACTIVE'}">active</c:if>"><a class="nav-link" href="/user/courses?status=ACTIVE">Active</a></div>
            <div class="col-md mx-auto menu-tab <c:if test = "${status.name() eq 'BOOKMARKED'}">active</c:if>"><a class="nav-link" href="/user/courses?status=BOOKMARKED">Bookmarks</a></div>
            <div class="col-md mx-auto menu-ta <c:if test = "${status.name() eq 'ARCHIVED'}">active</c:if>"><a class="nav-link" href="/user/courses?status=ARCHIVED">Archive</a></div>
        </div>
    </div>
</c:if>


<!-- Main Content -->
<div class="container search-results">
    <div class="row text-center">
        <c:if test = "${empty courses}">
            <div class="col-md-4 course-data mx-auto">
                <p>You have no courses yet :)</p>
            </div>
        </c:if>
        <c:forEach var="course" items="${courses}">
            <div class="col-md-4 course-data mx-auto">
                <div class="course-item text-center">
                    <p><c:out value="${course.title}"/></p>
                    <hr>
                    <div class="course-card">
                        <a href="/course/<c:out value="${course.id}"/>"><img alt="" src="<c:out value="${course.imgUrl}"/>"></a>
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
                            <span><c:out value="${course.rating}"/> (<c:out value="${course.studentsReviewed}"/>)</span>
                            <span><c:out value="${course.durationHours}"/></span>
                            <span><c:out value="${course.level}"/></span>
                        </div>
                    </div>
                    <c:if test = "${(course.courseStatus.name() == 'PUBLISHED') || course.courseStatus.name == 'IN_DEVELOPMENT'}">
                        <div class="student-buttons">
                            <button class="btn btn-outline-success mx-2">Update</button>
                        </div>
                    </c:if>
                    <c:if test = "${course.courseStatus.name() == 'IN_DEVELOPMENT'}">
                        <div class="student-buttons">
                            <button class="btn btn-outline-success mx-2">Delete</button>
                        </div>
                        <div class="student-buttons">
                            <button class="btn btn-outline-info mx-2">Send to moderation</button>
                        </div>
                    </c:if>
                    <c:if test = "${course.courseStatus.name() == 'IN_MODERATION'}">
                        <div class="student-buttons">
                            <button class="btn btn-outline-danger mx-2 disabled">In Moderation</button>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:forEach>
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
