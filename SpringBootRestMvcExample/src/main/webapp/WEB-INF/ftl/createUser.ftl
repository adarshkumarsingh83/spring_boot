<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="com.espark.adarsh.domain.UserCreateForm" -->
<#import "/spring.ftl" as spring>
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
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>Create a new user</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<div class="container">
    <div class="jumbotron">
        <center>   <h3>USER REGISTRATION FORM</h3></center>
    </div>
    <form role="form" name="form" action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div>
            <label for="userName">User Name</label>
            <input type="userName" class="form-control" name="userName" id="userName" value="${form.userName}"
                   required autofocus placeholder="User Name"/>
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
            <label for="email">Email address</label>
            <input type="email" class="form-control" name="email" id="email" value="${form.email}" required
                   autofocus placeholder="Email"/>
        </div>

        <div>
            <label for="userPhone">User Phone</label>
            <input type="userPhone" class="form-control" name="userPhone" id="userPhone" value="${form.userPhone}"
                   required autofocus placeholder="UserPhone"/>
        </div>
        <div>
            <label for="firstName">User First Name</label>
            <input type="firstName" class="form-control" name="firstName" id="firstName" value="${form.firstName}"
                   required autofocus placeholder="FirstName"/>
        </div>
        <div>
            <label for="lastName">User Last Name</label>
            <input type="lastName" class="form-control" name="lastName" id="lastName" value="${form.lastName}"
                   required autofocus placeholder="lastName"/>
        </div>


        <div>
            <label for="role">Role</label>
            <select name="roleName" id="roleName" required>
                <option <#if form.roleName == 'ROLE_USER'>selected</#if>>ROLE_USER</option>
                <option <#if form.roleName == 'ROLE_ADMIN'>selected</#if>>ROLE_ADMIN</option>
                <option <#if form.roleName == 'ROLE_SUPERADMIN'>selected</#if>>ROLE_SUPERADMIN</option>
            </select>
        </div>

        <button type="submit">Save</button>
    </form>
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