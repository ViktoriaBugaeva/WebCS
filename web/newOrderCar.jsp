<%-- 
    Document   : newOrderCar
    Created on : 24.12.2019, 18:46:06
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
        <a href="index.jsp">Главна страница</a><br>
        <br>
        <form action="createOrderCar" method="POST">
            <h1>Список автомобилей</h1>
            <select name="carId">
                <c:forEach var="car" items="${listCars}" varStatus="status">
                    <option value="${car.id}">
                        ${status.index+1}) ${car.marka}. ${car.model}. ${car.year}. ${car.price}
                    </option>
                </c:forEach>
            </select>

            <h1>Список покупателей</h1> 
            <select name="buyerId">
                <c:forEach var="buyer" items="${listBuyers}" varStatus="status">
                    <option value="${buyer.id}">
                        ${status.index+1}) ${buyer.name}. ${buyer.lastname}. ${buyer.email}. ${buyer.money}
                    </option>
                </c:forEach>
            </select><br>
            <br>
            <input type="submit" value="Купить">
        </form>  
    </body>
</html>
