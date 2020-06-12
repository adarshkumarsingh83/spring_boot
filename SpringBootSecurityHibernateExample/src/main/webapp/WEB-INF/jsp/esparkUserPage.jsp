<!DOCTYPE html>
<!--
* @author Adarsh Kumar
* @author $LastChangedBy: Adarsh Kumar$
* @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
* @Espark @copyright all right reserve
-->
<html>
<%@page isELIgnored="false" %>
<head>
    <title>Espark User Home Page!</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font/css/font-awesome.min.css"/>

    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <div class="alert alert-info">
            <h1>${message}</h1>
        </div>
        <form action="/logout" method="post">
            <input type="submit" class="btn btn-primary btn-lg btn-block" value="LogOut"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </div>


</div>
<div class="">

    <div class="panel panel-default">
        <div class="panel-body">
            <div class="alert alert-info">
                <h3>${user} LOGIN IN ${time}</h3>
            </div>
        </div>
    </div>

</div>


</body>
</html>