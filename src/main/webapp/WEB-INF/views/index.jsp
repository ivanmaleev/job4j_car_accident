<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<c:forEach var="el" items="${list}">
    <h2><c:out value="${el}"/></h2>
</c:forEach>
</body>
</html>