<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>학과별, 학년별 총 학생 수</title>
</head>
<body>
<h2>학과별, 학년별 총 학생 수를 확인</h2>

<table width="300" border="1">
    <tr>
        <td width="100">DEPT</td>
        <td width="100">YEAR</td>
        <td width="100">COUNT_SNO</td>
    </tr>
    <c:forEach items="${count_sno_by_dept_year}" var="count_sno_by_dept_year">
        <tr>
            <td width="100">${count_sno_by_dept_year.getDept()}</td>
            <td width="100">${count_sno_by_dept_year.getYear()}</td>
            <td width="100">${count_sno_by_dept_year.getCountSno()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>