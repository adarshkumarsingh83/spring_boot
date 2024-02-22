<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="com.espark.adarsh.domain.UserCreateForm" -->
<#import "/spring.ftl" as spring>
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
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>Create a new user</title>
</head>
<body>

<div class="container">
    <div class="jumbotron">
        <div class="alert alert-info">
            <h3>Espark Register User Page</h3>
        </div>
        <nav role="navigation">
            <a class="btn alert-info btn-block" href="/">Espark Home Page </a>
        </nav>
        <form role="form" name="form" action="" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div>
                <label for="email">Email address</label>
                <input type="email" class="form-control" name="email" id="email" value="${form.email}" required
                       autofocus placeholder="Email"/>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" class="form-control" required
                       placeholder="Password"/>
            </div>
            <div>
                <label for="passwordRepeated">Repeat</label>
                <input type="password" name="passwordRepeated" id="passwordRepeated" class="form-control" required
                       placeholder="Confirm Password"/>
            </div>
            <div>
                <label for="role">Role</label>
                <select name="role" id="role" required>
                    <option <#if form.role == 'USER'>selected</#if>>USER</option>
                    <option <#if form.role == 'ADMIN'>selected</#if>>ADMIN</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Save</button>
        </form>
    </div>
</div>
<@spring.bind "form" />
<#if spring.status.error>
<ul>
    <#list spring.status.errorMessages as error>
        <li>${error}</li>
    </#list>
</ul>
</#if>

</body>
</html>