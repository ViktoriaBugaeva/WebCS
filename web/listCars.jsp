<%-- 
    Document   : listCar
    Created on : 24.12.2019, 18:42:50
    Author     : vi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <title>Список автомобилей</title>
    </head>
    <body>
        <h1>Список автомобилей</h1>
        <a href="index.jsp">Главна страница</a><br>
        <ol>
            <c:forEach var="car" items="${listCars}">
                <li> ${car.marka}. ${car.model}. ${car.year}. ${car.price}. ${car.count}</li>
            </c:forEach>
        </ol>
    </body>
</html>
