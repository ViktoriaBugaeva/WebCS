<%-- 
    Document   : index
    Created on : 24.12.2019, 18:11:19
    Author     : vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Автомобильный салон "СКОРОСТЬ"</title>
    </head>
    <body>
        <h1>Добро пожаловать в наш автосалон!</h1>
        <p>${info}</p>
              <a href="showLogin">Вход</a><br>
              <br>
              <a href="newCar">Добавить автомобиль в базу данных</a><br>
              <br>
              <a href="newBuyer">Зарегистрировать покупателя</a><br>
              <br>
              <a href="listCars">Список автомобилей</a><br>
              <br>
              <a href="listBuyers">Список покупателей</a><br>
              <br>
              <a href="newOrderCar">Купить автомобиль</a><br>
              <br>
              <a href="listBuyCars">Список проданых автомобилей</a><br>
    </body>
</html>
