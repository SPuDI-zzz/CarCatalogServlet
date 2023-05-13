<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Vehicle</title>
</head>
<body>
<div>
    <h1>NEW VEHICLE</h1>
    <form action="<c:url value="/user/vehicles/new"/>" method="post">
        <label>Mark:<br>
            <input type="text" name="mark" maxlength="50" size="30" required>
        </label>
        <label>Model:<br>
            <input type="text" name="model" maxlength="50" size="30" required>
        </label>
        <label>Type:<br>
            <input type="text" name="type" maxlength="50" size="30" required>
        </label>
        <label>Mileage:<br>
            <input type="number" name="mileage" placeholder="0" max="${Integer.MAX_VALUE}" min="0" required>
        </label>
        <label>Price:<br>
            <input type="number" name="price" placeholder="0" max="${Integer.MAX_VALUE}" min="0" required>
        </label>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
