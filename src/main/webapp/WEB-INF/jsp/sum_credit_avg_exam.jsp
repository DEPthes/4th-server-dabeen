<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> 총 학점과 시험 점수의 평균</title>
</head>
<body>
<h2>학생별로 수강한 교과목의 총학점과 시험점수의 평균</h2>

<table width="500" border="1">
    <tr>
        <td width="100">SNO</td>
        <td width="200">SUM(CREDIT)</td>
        <td width="200">AVG(EXAM)</td>
    </tr>
    <c:forEach items="${sum_credit_avg_exam}" var="student">
        <tr>
            <td width="100">${student.getSno()}</td>
            <td width="200">${student.getSumCredit()}</td>
            <td width="200">${student.getAvgExam()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>