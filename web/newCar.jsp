<%-- 
    Document   : newCar
    Created on : 24.12.2019, 18:45:37
    Author     : vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить автомобиль в корзину</title>
    </head>
    <body>
        <h1>Оформить заказ</h1>
        <h2>Выбрать способ доставки</h2>
        <a href="index.jsp">Главна страница</a><br>
        <br>
        <form action="createCar" method="POST">
            Марка:<br>
            <input type="text" name="marka"><br>
            Модель:<br>
            <input type="text" name="model"><br>
            Год выпуска:<br>
            <input type="text" name="year"><br>
            Цена:<br>
            <input type="text" name="price"><br>
            Колличество:<br>
            <input type="text" name="quantity"><br>
            <br>
            <input type="submit" value="Добавить"><br>
          
        </form>
    </body>
</html>