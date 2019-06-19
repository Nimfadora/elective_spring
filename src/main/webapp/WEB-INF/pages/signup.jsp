<%--<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>-->--%>
<%--<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->--%>
<%--<!--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>-->--%>
<%--<!--<%&#45;&#45;<%@ page language="java" contentType="text/html; charset=ISO-8859-1"&#45;&#45;%>-->--%>
<%--<!--<%&#45;&#45;pageEncoding="ISO-8859-1"%>&#45;&#45;%>-->--%>
<%--<!--<fmt:requestEncoding value="UTF-8" />-->--%>
<%--<!--<fmt:setLocale value="ru_RU" scope="session"/>-->--%>
<%--<!--<fmt:setBundle basename="i18n.locale" var="lang"/>-->--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <%--<!--<title><fmt:message key="LOGIN_TITLE" bundle="${lang}"/></title>-->--%>
    <title>Signup</title>
    <meta charset="UTF-8">


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Vollkorn&display=swap" rel="stylesheet">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <!--<script src="/static/js/script.js"></script>-->

    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div id="second">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1>Signup</h1>
                        </div>
                    </div>
                    <form action="/signup" method="post" name="registration">
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" name="name" class="form-control" id="name" aria-describedby="emailHelp"
                                   placeholder="Enter full name">
                        </div>
                        <div class="form-group">
                            <label for="company">Company</label>
                            <input type="text" name="company" class="form-control" id="company"
                                   aria-describedby="emailHelp" placeholder="Enter company">
                        </div>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" name="email" class="form-control" id="email"
                                   aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="form-control"
                                   aria-describedby="emailHelp" placeholder="Enter Password">
                        </div>
                        <div class="form-group">
                            <label for="password">Repeat Password</label>
                            <input type="password" name="password" id="password-repeat" class="form-control"
                                   aria-describedby="emailHelp" placeholder="Repeat Password">
                        </div>
                        <div class="form-group">
                            <div class="text-center">
                                <p class="mx-2" style="float:left">Student</p>
                                <label class="switch">
                                    <input type="checkbox" name="role" value="AUTHOR">
                                    <span class="slider"></span>
                                </label>
                                <p class="mx-2" style="float:right">Author</p>
                            </div>
                        </div>
                        <div class="col-md-12 text-center mb-3">
                            <button type="submit" class=" btn btn-outline-primary tx-tfm">Sign up</button>
                        </div>
                        <div class="col-md-12 ">
                            <div class="login-or">
                                <hr class="hr-or">
                            </div>
                        </div>
                        <div class="col-md-12 ">
                            <div class="form-group">
                                <p class="text-center"><a href="/login" id="signin">Already have an account?</a></p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>