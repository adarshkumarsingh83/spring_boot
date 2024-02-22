<#-- @ftlvariable name="users" type="java.util.List<com.espark.adarsh.domain.User>" -->
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
    <title>List of Users</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/user/create">Create a new user</a></li>
    </ul>
</nav>


<div class="container">
    <h2>User Information </h2>
    <p>user data for analysis </p>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>User First Name</th>
            <th>User Last Name</th>
            <th>User Email</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a href="/user/${user.id}">${user.userEmail}</a></td>
            <td>
                <#list user.userRoles as role>
                ${role.name} &nbsp;
                </#list>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>