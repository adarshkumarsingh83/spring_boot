<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="/css/applicationStyleSheet.css" />" rel="stylesheet"
          type="text/css">
    <title>Spring Security Example - ProgrammingFree</title>
</head>
<body class="security-app">
<div class="details">
    <h2>Spring Boot MVC & Security Application Example</h2>
</div>
<div class="lc-block">
    <h1>Welcome!</h1>

    <div class="alert-normal">
        Click <a href="<spring:url value='/hello' />">here</a> to see a
        greeting.
    </div>
</div>
</body>
</html>
