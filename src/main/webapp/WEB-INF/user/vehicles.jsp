<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vehicle list</title>
    <link rel="stylesheet" href="<c:url value="/styles/vehicles.css"/>">
</head>
<body>
<div class="logout">
    <a href="<c:url value="/user/logout"/>">Logout</a>
</div>
<h1>Your Vehicle list</h1>
<div class="btn-container">
    <a class="btn" href="vehicles/new">Add new vehicle</a>
</div>
<c:if test="${empty vehicleList}">
    <div class="empty">It's empty!</div>
</c:if>
<c:if test="${not empty vehicleList}">
    <table>
        <thead>
        <tr>
            <th>Mark</th>
            <th>Model</th>
            <th>Type</th>
            <th>Mileage</th>
            <th>Price</th>
            <th>Edit</th>
            <th>Remove</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vehicleList}" var="vehicle">
            <tr>
                <td><c:out value="${vehicle.getMark()}"/>
                <td><c:out value="${vehicle.getModel()}"/>
                <td><c:out value="${vehicle.getType()}"/>
                <td><c:out value="${vehicle.getMileage()}"/>
                <td><c:out value="${vehicle.getPrice()}"/>
                <td>
                    <form action="vehicles/edit" method="get">
                        <input type="hidden" name="idVehicle" value="<c:out value="${vehicle.getId()}"/>"><br>
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="vehicles/remove" method="post">
                        <input type="hidden" name="idVehicle" value="<c:out value="${vehicle.getId()}"/>"><br>
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
