<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>과목별 최저 점수</title>
</head>
<body>
<h2>과목별 최저 점수를 획득한 학생 정보</h2>

<table width="700" border="1">
    <tr>
        <td width="100">과목번호</td>
        <td width="200">이름</td>
        <td width="100">학번</td>
        <td width="100">지도교수번호</td>
        <td width="100">학년</td>
        <td width="100">점수</td>
    </tr>
    <c:forEach items="${lowest_scorers}" var="student">
        <tr>
            <td width="100">${student.cno}</td>
            <td width="200">${student.sname}</td>
            <td width="100">${student.sno}</td>
            <td width="100">${student.pno}</td>
            <td width="100">${student.year}</td>
            <td width="100">${student.exam}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
