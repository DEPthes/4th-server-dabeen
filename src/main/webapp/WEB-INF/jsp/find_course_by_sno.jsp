<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>sno로 course찾기</title>
</head>
<body>
<h2>특정 Student를 선택하여 해당 Student가 수강하는 Course 이름과 학점 및 시험 점수를 검색</h2>

<table width="500" border="1">
    <tr>
        <td width="100">SNO</td>
        <td width="100">PNO</td>
        <td width="200">SNAME</td>
        <td width="100">YEAR</td>
        <td width="100">DEPT</td>
    </tr>
    <c:forEach items="${students}" var="student">
         <td><a href="${pageContext.request.contextPath}/university/find_course_by_sno/${student.sno}">${student.sno}</a></td>
            <td width="100">${student.getPno()}</td>
            <td width="100">${student.getSname()}</td>
            <td width="100">${student.getYear()}</td>
            <td width="100">${student.getDept()}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>

