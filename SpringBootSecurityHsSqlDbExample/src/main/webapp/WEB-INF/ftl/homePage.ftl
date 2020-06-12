<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="com.espark.adarsh.domain.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<!--
* @author Adarsh Kumar
* @author $LastChangedBy: Adarsh Kumar$
* @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
* @Espark @copyright all right reserve
-->
<head>
    <meta charset="utf-8">
    <title>Home page</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>

<div class="example">
    <div class="container">
        <div class="jumbotron">
            <nav role="navigation">
                <ul>
                <#if !currentUser??>
                    <div class="alert alert-info">
                        <center><h3> WELCOME TO ESPARK HOME</h3></center>
                    </div>
                    <a class="btn alert-info btn-block" href="/login">Log in</a>
                </#if>
                <#if currentUser??>
                    <div class="alert alert-info">
                        <center><h3> WELCOME TO ESPARK APPLICATION HOME</h3></center>
                    </div>

                    <form action="/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button class="btn alert-info btn-block" type="submit" width="100">Log Out</button>
                    </form>
                </#if>
                </ul>

            </nav>
        </div>
    </div>
</div>
<div class="container">
<#if currentUser??>
    <a class="btn alert-info btn-block" href="/user/${currentUser.id}" width="100">View My Details</a>
</#if>
<#if currentUser?? && currentUser.role == "ADMIN">
    <a class="btn alert-info btn-block" href="/user/create" width="100">Register User</a>
    <a class="btn alert-info btn-block" href="/users" width="100">List Users</a>
</#if>
</div>
</body>
</html>