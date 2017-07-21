<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="com.espark.adarsh.web.security.UserDetailsImpl" -->
<!DOCTYPE html>
<!--
* @author Adarsh Kumar
* @author $LastChangedBy: Adarsh Kumar$
* @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
* @Espark @copyright all right reserve
-->
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
    <title>SPRING BOOT TOKEN AUTHENTICATION HOME PAGE </title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

</head>
<body>

<div bgcolor="#add8e6">
    <nav role="navigation">
        <ul>
        <#if !currentUser??>
            <li><a href="/login">Log in</a></li>
        </#if>

        <#if currentUser?? >
            <li>
                <form action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit">Log out</button>
                </form>
            </li>
        </#if>
        <#if currentUser?? && currentUser.role == "ROLE_SUPERADMIN">
            <li><a href="/user/create">Create a new user</a></li>
            <li><a href="/users">View all users</a></li>
        </#if>
        </ul>
    </nav>
    <div class="bs-example">
        <div class="container">
            <div class="jumbotron">
                <center>   <h3>WELCOME TO THE APPLICATION</h3></center>
            </div>
            <p style="font-size: x-large" >
                Sample application with spring boot (security for custom token authentication for rest web services)
            </p>
        </div>
    </div>
    <iframe src="http://adarshkumarsingh83.blogspot.in/" seamless width="100%" height="100%">
    </iframe>
</div>
</body>
</html>