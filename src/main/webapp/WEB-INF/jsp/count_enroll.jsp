<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>University Enroll</title>
</head>
<body>
<c var="enroll_cnt">
    <h2>Enroll Data와 Tuple의 총 갯수</h2>
    <p>튜플의 총 갯수: ${enroll_cnt}</p>
</c>
<table width="500" border="1">
    <tr>
        <td width="100">SNO</td>
        <td width="100">CNO</td>
        <td width="100">GRADE</td>
        <td width="100">EXAM</td>
    </tr>
    <c:forEach items="${enrolls}" var="enroll">
        <tr>
            <td width="100">${enroll.getSno()}</td>
            <td width="100">${enroll.getCno()}</td>
            <td width="100">${enroll.getGrade()}</td>
            <td width="100">${enroll.getExam()}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>