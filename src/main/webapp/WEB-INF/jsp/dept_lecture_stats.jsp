<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학과별 개설 강좌 수</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>학과별 개설 강좌 수</h1>
    <table>
        <tr>
            <th>학과명</th>
            <th>개설 강좌 수</th>
        </tr>
        <c:forEach items="${lectureStats}" var="stat">
            <tr>
                <td>${stat.dname}</td>
                <td>${stat.lectureCount}개</td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="/university">메인으로 돌아가기</a></p>
</body>
</html> 