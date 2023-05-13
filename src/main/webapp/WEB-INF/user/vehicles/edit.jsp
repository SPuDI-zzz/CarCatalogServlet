<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit vehicle</title>
</head>
<body>
<div>
    <h1>EDIT VEHICLE</h1>
    <form action="<c:url value="/user/vehicles/edit"/>" method="post">
        <input type="hidden" name="idVehicle" value="<c:out value="${vehicle.getId()}"/>"><br>
        <label>Mark:<br>
            <input type="text" name="mark" maxlength="50" size="30" value="<c:out value="${vehicle.getMark()}"/>" required>
        </label>
        <label>Model:<br>
            <input type="text" name="model" maxlength="50" size="30" value="<c:out value="${vehicle.getModel()}"/>" required>
        </label>
        <label>Type:<br>
            <input type="text" name="type" maxlength="50" size="30" value="<c:out value="${vehicle.getType()}"/>" required>
        </label>
        <label>Mileage:<br>
            <input type="number" name="mileage" placeholder="0" max="${Integer.MAX_VALUE}" min="0" value="<c:out value="${vehicle.getMileage()}"/>" required>
        </label>
        <label>Price:<br>
            <input type="number" name="price" placeholder="0" max="${Integer.MAX_VALUE}" min="0" value="<c:out value="${vehicle.getPrice()}"/>" required>
        </label>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
