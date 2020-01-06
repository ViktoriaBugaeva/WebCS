<%-- 
    Document   : showLogin
    Created on : 24.12.2019, 18:44:42
    Author     : vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход в ситему</title>
    </head>
    <body>
        <h1>Добро пожаловать!</h1>
        <p>${info}</p>
        <form action="login" method="POST">
            Login: <input type="text" name="login"><br>
            <br>
            Password: <input type="password" name="password"><br>
            <br>
            <input type="submit" value="Go">
        </form>
    </body>
</html>
