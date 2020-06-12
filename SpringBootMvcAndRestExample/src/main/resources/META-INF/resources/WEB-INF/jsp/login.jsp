<%--
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body onload='document.loginForm.username.focus();'>


<div class="espark" style="text-align: center">
    <div class="container">
        <div class="jumbotron" style="text-align: center">


            <c:if test="${not empty message}">
                <div style="text-align: center">
                       <h3>${message}</h3>
                </div>
            </c:if>
            <form name='login' action="login" method='post'>
                <fieldset class="alert-success">
                    <legend><strong style="color:black;">Login Window</strong></legend>
                    <c:if test="${not empty error}">
                        <div class="alert-danger" style="text-align: center">
                               <h3> ${error}</h3>
                        </div>
                    </c:if>
                    <div class="form-group" style="text-align: left">
                        <label for="username" >Username</label>
                        <input type="text" name="username" id="username" class="form-control" required/>
                    </div>
                    <div class="form-group" style="text-align: left">
                        <label for="password" >Password</label>
                        <input type="password" name="password" id="password" class="form-control" required/>
                    </div>
                    <div class="form-actions">
                        <button type="submit" ng-disabled="form.$invalid || dataLoading"
                                class="btn btn-danger btn-block">Login
                        </button>
                    </div>
                </fieldset>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

        </div>
    </div>
</div>

</body>
</html>