<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="/css/applicationStyleSheet.css" />" rel="stylesheet"
          type="text/css">
    <title>Spring Security Example</title>
</head>
<body class="security-app">
<div class="details">
    <h2>Spring Boot MVC & Security Application Example</h2>
</div>

<form action="/login" method="post">

    <div class="lc-block">
        <div>
            <input type="text" class="style-4" name="username" placeholder="User Name"/>
        </div>
        <div>
            <input type="password" class="style-4" name="password" placeholder="Password"/>
        </div>
        <div>
            <input type="submit" value="Sign In" class="button red small"/>
        </div>
        <c:if test="${param.error ne null}">
            <div class="alert-danger">Invalid username and password.</div>
        </c:if>
        <c:if test="${param.logout ne null}">
            <div class="alert-normal">You have been logged out.</div>
        </c:if>
    </div>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

</body>
</html>