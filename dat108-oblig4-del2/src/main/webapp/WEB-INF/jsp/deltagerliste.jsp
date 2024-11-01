<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/simple.css">
    <title>Deltagerliste</title>
    <style>
       .highlighted {
          background-color: lightgreen !important;
          color: black !important;
        }
     </style>
</head>
<body>
    <p>Du er innlogget som <c:out value="${username} / ${fornavn} ${etternavn}"/></p><br>
    <h2>Deltagerliste</h2>
    <table>
        <tr>
            <th>Kjønn</th>
            <th align="left">Navn</th>
            <th align="left">Mobil</th>
        </tr>
        <c:forEach var="deltager" items="${deltagere}">
            <tr class="${deltager.mobil == username ? 'highlighted' : ''}">
                <td align="center">${deltager.kjonn == "mann" ? "&#9794;" : "&#9792;"}</td>
                <td>${deltager.fornavn}&nbsp;${deltager.etternavn}</td>
                <td>${fn:substring(deltager.mobil, 0, 3)}&nbsp;
                    ${fn:substring(deltager.mobil, 3, 5)}&nbsp;
                    ${fn:substring(deltager.mobil, 5, 8)}</td>
            </tr>
        </c:forEach>

    </table>
    <form action="loggut" method="post">
        <input type="submit" value="Logg ut">
    </form>
    
</body>
</html>