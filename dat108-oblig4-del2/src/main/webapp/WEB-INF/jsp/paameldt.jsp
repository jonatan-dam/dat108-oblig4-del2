<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/simple.css">
<title>Påmeldingsbekreftelse</title>
</head>
<body>
    <h2>Påmeldingsbekreftelse</h2>
    <p>Påmeldingen er mottatt for</p>
    <p>
        ${SAdeltager.fornavn}<br />
        ${SAdeltager.etternavn}<br />
        ${SAdeltager.mobil}<br /> ${SAdeltager.kjonn}
    </p>
    <a href="deltagerliste">Gå til deltagerlisten</a>
</body>
</html>