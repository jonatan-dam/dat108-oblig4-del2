<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
<head>
    <link href="css/simple.css" rel="stylesheet" type="text/css" />
    <meta charset="UTF-8">
   <script src="js/klientsidevalidering.js" defer></script>
    <title>Påmelding</title>
</head>

<body>
    <h2>Logg inn</h2>
    <p style="color:red;">
            <c:forEach var="feilmelding" items="${SAfeilmeldinger}">
                ${feilmelding}<br>
            </c:forEach>
         </p>
            
    
    
        <form id="skjema" action="innlogging" method="post">
            <label for="mobil">Mobil:</label>
            <input type="number" name="mobil" id="mobil"><br>
            <label for="passord">Passord:</label>
            <input type="password" name="password" id="password"><br>
            <input type="submit" value="Logg inn">
        </form>   
   
    

</body>
</html>
