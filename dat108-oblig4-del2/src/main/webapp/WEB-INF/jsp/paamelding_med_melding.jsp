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
    <h2>Påmelding</h2>
    <p style="color:red;">
            <c:forEach var="feilmelding" items="${SAfeilmeldinger}">
                ${feilmelding}<br>
            </c:forEach>
         </p>
            
    
    <fieldset>
        <form id="skjema" action="sjekkDeltager" method="post">
            <label for="fornavn">Fornavn</label>
            <input type="text" name="fornavn" id="fornavn"><br>
            <label for="etternavn">Etternavn</label>
            <input type="text" name="etternavn" id="etternavn"><br>
            <label for="mobil">Mobil (8 siffer)</label>
            <input type="text" name="mobil" id="mobil"><br>
            <label for="passord">Passord</label>
            <input type="password" name="passord" id="passord"><br>
            <label for="passordRepetert">Passord repetert</label>
            <input type="password" name="passordRepetert" id="passordRepetert"><br>
            <label for="kjonn">Kjønn</label>
            <input type="radio" name="kjonn" value="mann">Mann
            <input type="radio" name="kjonn" value="kvinne">Kvinne<br>
            <input type="submit" value="Meld meg på">
        </form>   
    </fieldset>
    

</body>
</html>
