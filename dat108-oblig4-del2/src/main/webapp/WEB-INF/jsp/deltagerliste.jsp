<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/simple.css">
    <title>Deltagerliste</title>
</head>
<body>
    <h2>Deltagerliste</h2>
    <table>
        <tr>
            <th>Kjønn</th>
            <th align="left">Navn</th>
            <th align="left">Mobil</th>
        </tr>
        <c:forEach var="deltager" items="${deltagere}">
            <tr>
                <td align="center">${deltager.kjonn}</td>
                <td>${deltager.fornavn}&nbsp;${deltager.etternavn}</td>
                <td>${deltager.mobil}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>