<!DOCTYPE html>
<!--
* @author Adarsh Kumar
* @author $LastChangedBy: Adarsh Kumar$
* @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
* @Espark @copyright all right reserve
-->
<html>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <title>Spring Security Example </title>
    <meta charset="utf-8"/>
    <title>Espark Login </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font/css/font-awesome.min.css"/>

    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron">
    <div class="container">

        <div class="page-header">
            <div class="alert alert-info">
                <h1>Espark Spring boot Login</h1>
            </div>
        </div>

        <c:if test="${param.logout}">
            <div class="alert alert-success">
                You have been logged out.
            </div>
        </c:if>

        <c:if test="${param.error}">
            <div class="alert alert-danger">
                Invalid username and password.
            </div>
        </c:if>

        <!-- Simple Login - START -->
        <form class="col-md-12" action="/login" method="post">
            <div class="form-group">
                <input type="text" name="username" class="form-control input-lg" placeholder="username"/>
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control input-lg" placeholder="password"/>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-lg btn-block" value="Login"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </div>
</div>

</body>
</html>