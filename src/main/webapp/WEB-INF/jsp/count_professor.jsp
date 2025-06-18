<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>University Professor</title>
</head>
<body>
<c var="professor_cnt">
    <h2> Professor Data와 Tuple의 총 갯수 </h2>
    <p>튜플의 총 갯수: ${professor_cnt}</p>
</c>

<table width="500" border="1">
    <tr>
        <td width="100">PNO</td>
        <td width="100">PNAME</td>
        <td width="100">PMAJOR</td>
        <td width="100">PDEPT</td>
    </tr>

    <c:forEach items="${professors}" var="professor">
        <tr>
            <td width="100">${professor.getPno()}</td>
            <td width="100">${professor.getPname()}</td>
            <td width="100">${professor.getPmajor()}</td>
            <td width="100">${professor.getPdept()}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>