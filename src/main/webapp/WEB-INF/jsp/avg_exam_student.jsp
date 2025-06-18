<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>과목별로 전체 평균 점수와 학점별 학생 수</title>
</head>
<body>
<h2>과목별로 전체 평균 점수와 학점별 학생 수를 확인할 수 있어야 한다.</h2>

<table width="700" border="1">
    <tr>
        <td width="100">CNO</td>
        <td width="300">CNAME</td>
        <td width="100">AVG(EXAM)</td>
        <td width="100">GRADE</td>
        <td width="100">COUNT(SNO)</td>
    </tr>
    <c:forEach items="${avg_exam_student}" var="avg_exam_student">
        <tr>
            <td width="100">${avg_exam_student.getCno()}</td>
            <td width="300">${avg_exam_student.getCname()}</td>
            <td width="100">${avg_exam_student.getAvgExam()}</td>
            <td width="100">${avg_exam_student.getGrade()}</td>
            <td width="100">${avg_exam_student.getCountSno()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>