<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>과목별 최고/최저 점수 통계</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>과목별 최고/최저 점수 통계</h1>
    <table>
        <tr>
            <th>과목번호</th>
            <th>과목명</th>
            <th>최고점수</th>
            <th>최고점수 학생</th>
            <th>최저점수</th>
            <th>최저점수 학생</th>
        </tr>
        <c:forEach items="${courseStats}" var="stat">
            <tr>
                <td>${stat.cno}</td>
                <td>${stat.cname}</td>
                <td>${stat.maxScore}</td>
                <td>${stat.maxSno} - ${stat.maxSname}</td>
                <td>${stat.minScore}</td>
                <td>${stat.minSno} - ${stat.minSname}</td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="/university">메인으로 돌아가기</a></p>
</body>
</html> 