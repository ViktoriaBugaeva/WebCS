<%-- 
    Document   : listBuyer
    Created on : 24.12.2019, 18:43:13
    Author     : vi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список покупателей</title>
    </head>
    <body>
        <h1>Список покупателей</h1>
        <a href="index.jsp">Главна страница</a><br>
        <ol>
            <c:forEach var="buyer" items="${listBuyers}">
                <li> ${buyer.name}. ${buyer.lastname}. ${buyer.email}. ${buyer.money}</li>
            </c:forEach>
        </ol>
    </body>
</html>
