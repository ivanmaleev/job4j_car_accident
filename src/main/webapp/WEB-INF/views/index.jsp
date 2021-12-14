<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<div class="container">
    <div><a href="<c:url value='/create'/>">Добавить инцидент</a></div>
    <div>
        <p>Правонарушения:</p>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Имя</th>
                <th scope="col">Описание</th>
                <th scope="col">Адрес</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${accidents}">
                <tr>
                    <th scope="row"><c:out value="${accident.id}"/></th>
                    <td><c:out value="${accident.name}"/></td>
                    <td><c:out value="${accident.text}"/></td>
                    <td><c:out value="${accident.address}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>