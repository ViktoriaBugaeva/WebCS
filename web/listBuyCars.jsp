<%-- 
    Document   : listBuyCar
    Created on : 24.12.2019, 18:46:21
    Author     : vi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Автомобильный салон "СКОРОСТЬ"</title>
    </head>
    <body>
        <h1>Автомобильный салон "СКОРОСТЬ"</h1>
    <body>
         <p>${info}</p>
        <a href="index.jsp">Главная</a><br>
        Список проданных автомобилей:<br>
        <form action="buyCar" method="POST">
            <ul>
                <c:forEach var="history" items="${listHistories}">
                    <li> Покупатель: ${history.buyer.name} ${history.buyer.lastname} купил ${history.car.marka} ${history.car.model}
                </c:forEach>
            </ul><br>
        </form>  
    </body>
</html>
