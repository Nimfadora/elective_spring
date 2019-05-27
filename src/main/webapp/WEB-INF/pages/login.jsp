<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
         <%--pageEncoding="ISO-8859-1"%>--%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="i18n.locale" var="lang"/>
<html>
<head>
    <title><fmt:message key="LOGIN_TITLE" bundle="${lang}"/></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="logo-container">
    <div class="text-center">
        <img class="logo" src="../img/logo.png">
    </div>
</div>
<div class="background-container">
    <div class="container  form-container">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info logIn-panel" >
                <div class="panel-heading">
                    <div class="panel-title"><fmt:message key="LOGIN_TITLE" bundle="${lang}"/></div>
                </div>
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#student"><fmt:message key="STUDENT" bundle="${lang}"/></a></li>
                    <li><a data-toggle="tab" href="#tutor"><fmt:message key="TUTOR" bundle="${lang}"/></a></li>
                </ul>
                <div class="tab-content">
                    <div id="student" class="tab-pane fade in active">
                        <div style="padding-top:30px" class="panel-body" >
                            <div style="display:none" id="login-alert-s" class="alert alert-danger col-sm-12"></div>

                            <form id="loginform-s" class="form-horizontal" role="form" action="/login/student" method="post">

                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                    <input id="login-s-username" type="email" class="form-control" name="email" value="" placeholder="<fmt:message key="EMAIL" bundle="${lang}"/>">
                                </div>

                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input id="login-s-password" type="password" class="form-control" name="password" placeholder="<fmt:message key="PASSWORD" bundle="${lang}"/>">
                                </div>


                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                        <button id="btn-s-login" type="submit" class="btn btn-success"><fmt:message key="LOGIN_VERB" bundle="${lang}"/></button>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                            <fmt:message key="HAVE_NO_ACCOUNT" bundle="${lang}"/>
                                            <a href="/signUp/student" >
                                                <fmt:message key="SIGNUP_VERB" bundle="${lang}"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div id="tutor" class="tab-pane fade">
                        <div style="padding-top:30px" class="panel-body" >
                            <div style="display:none" id="login-alert-t" class="alert alert-danger col-sm-12"></div>

                            <form id="loginform-t" class="form-horizontal" role="form" action="/login/tutor" method="post">

                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                    <input id="login-t-username" type="email" class="form-control" name="email" value="" placeholder="<fmt:message key="EMAIL" bundle="${lang}"/>">
                                </div>

                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input id="login-t-password" type="password" class="form-control" name="password" placeholder="<fmt:message key="PASSWORD" bundle="${lang}"/>">
                                </div>


                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                        <button id="btn-t-login" type="submit" class="btn btn-success"><fmt:message key="LOGIN_VERB" bundle="${lang}"/></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>