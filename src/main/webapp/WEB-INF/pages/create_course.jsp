<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Create course</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
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
                    <c:if test = "${user.getRole() != com.vasylieva.elective.model.status.Role.ADMIN}">
                        <a class="dropdown-item" href="/user/courses">Profile</a>
                    </c:if>
                    <a class="dropdown-item" href="/logout">Log out</a>
                </div>
            </div>
        </c:if>
    </div>
</nav>

<div class="container course-create">
    <div class="row">
        <div class="col-lg-8 col-centered">
            <div class="card mt-3 tab-card">
                <div class="card-header tab-card-header">
                    <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link" id="one-tab" data-toggle="tab" href="#one" role="tab" aria-controls="One" aria-selected="true">Українська</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="two-tab" data-toggle="tab" href="#two" role="tab" aria-controls="Two" aria-selected="false">Русский</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="three-tab" data-toggle="tab" href="#three" role="tab" aria-controls="Three" aria-selected="false">English</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="four-tab" data-toggle="tab" href="#four" role="tab" aria-controls="Four" aria-selected="false">Questionnaire</a>
                        </li>
                    </ul>
                </div>

                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active p-3" id="one" role="tabpanel" aria-labelledby="one-tab">
                        <div class="col-lg-12 text-center">
                            <h2 class="card-title my-5">Опис курсу українською</h2>
                        </div>
                        <hr/>
                        <form action="" method="post" name="course-ua">
                            <div class="form-group">
                                <label for="name">Назва курсу</label>
                                <input type="text" name="name" class="form-control input-lg" id="name">
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlSelect1">Категорія</label>
                                <select class="form-control" id="exampleFormControlSelect1">
                                    <option>Програмування</option>
                                    <option>Дизайн</option>
                                    <option>Матиматика</option>
                                    <option>Алгоритми</option>
                                    <option>Архітектура</option>
                                    <option>Бізнес</option>
                                    <option>Хмарні обчислення</option>
                                    <option>Громадські науки</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="q3">Опис курсу</label>
                                <textarea type="text" name="q3" class="form-control " id="q3"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="q4">Тривалість курсу (годин)</label>
                                <input type="number" name="q4" class="form-control" id="q4">
                            </div>
                            <div class="form-group">
                                <label for="q6">Навички, яких студент набуваеє по завершенні</label>
                                <textarea type="text" name="q6" class="form-control" id="q6" placeholder="фінансова грамотність, фінанси, інвестиції"></textarea>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label mx-3" for="defaultCheck1">
                                    Курс доступний українською
                                </label>
                            </div>
                        </form>
                    </div>


                    <div class="tab-pane fade p-3" id="two" role="tabpanel" aria-labelledby="two-tab">
                        <h5 class="card-title">Tab Card Two</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>

                    <div class="tab-pane fade p-3" id="three" role="tabpanel" aria-labelledby="three-tab">
                        <h5 class="card-title">Tab Card Two</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>

                    <div class="tab-pane fade p-3" id="four" role="tabpanel" aria-labelledby="four-tab">
                        <div class="col-lg-12 text-center">
                            <h2 class="card-title my-5">Your questions</h2>
                        </div>
                        <hr/>
                        <form action="" method="post" name="course-ua">
                            <div class="form-group">
                                <label for="q1">Question 1</label>
                                <input type="text" name="name" class="form-control input-lg" id="q1" placeholder="Your question">
                            </div>
                            <div class="col-md-12 text-center course-q-add my-5">
                                <button type="submit" class=" btn btn-outline-success tx-tfm">+</button>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="col-md-12 text-center course-submit my-5">
                    <button type="submit" class=" btn btn-outline-primary tx-tfm">Submit</button>
                </div>
            </div>
        </div>
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