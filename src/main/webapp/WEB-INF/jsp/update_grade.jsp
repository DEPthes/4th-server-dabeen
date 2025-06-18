<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>시험점수가 잘못된 학생 정보, 수정요청에 정상수정</title>
</head>
<body>
<h2>시험점수가 잘못된 학생 정보, 수정요청에 정상수정</h2>
<p>(단, 학점 부여방식은 다음과 같다고 가정한다. 60점 미만: F, 60~69: D, 70~79:C, 80~89:D, 90 이상: A)</p>

<table width="500" border="1">
    <tr>
        <td width="100">이름</td>
        <td width="100">학번</td>
        <td width="100">과목번호</td>
        <td width="100">점수</td>
        <td width="100">학점</td>
    </tr>


    <c:forEach items="${update_grade}" var="update_grade">
    <tr>
        <td width="100">${update_grade.getSname()}</td>
        <td width="100">${update_grade.getSno()}</td>
        <td width="100">${update_grade.getCno()}</td>
        <td width="100">${update_grade.getExam()}</td>
        <td width="100">${update_grade.getGrade()}</td>
    </tr>
    </c:forEach>
</table>

<form name="update_grade" method="post" action="/university/update_grade_result">
    <p>SNO: <input type="text" name="sno"></p>
    <p>CNO: <input type="text" name="cno"></p>
    <p><input type="submit" name="Submit" value="수정"></p>
</form>
</body>
</html>