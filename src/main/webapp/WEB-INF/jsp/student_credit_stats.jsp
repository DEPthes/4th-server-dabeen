<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생별 이수학점 및 평균점수</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>학생별 이수학점 및 평균점수</h1>
    <table>
        <tr>
            <th>학번</th>
            <th>이름</th>
            <th>총 이수학점</th>
            <th>평균 점수</th>
        </tr>
        <c:forEach items="${studentStats}" var="stat">
            <tr>
                <td>${stat.sno}</td>
                <td>${stat.sname}</td>
                <td>${stat.totalCredits}</td>
                <td><fmt:formatNumber value="${stat.avgScore}" pattern="0.00"/></td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="/university">메인으로 돌아가기</a></p>
</body>
</html> 