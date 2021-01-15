<%--
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="espark" style="text-align: center">
    <div class="btn-group btn-block" role="group" aria-label="..." style="text-align: right">
        <h4>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                Welcome : ${pageContext.request.userPrincipal.name} | <a href="../logout" class='btn btn-default'>
                Logout</a>
            </c:if>
            <a href="../welcome" class="btn btn-default">Espark Home</a>
        </h4>
    </div>

    <div class="container">

        <div class="jumbotron" style="text-align: center">

            <div class="alert alert-info">
                <h4> ${title}</h4>
            </div>

            <h5> ${message}</h5>
        </div>
    </div>


</div>
</body>
</html>