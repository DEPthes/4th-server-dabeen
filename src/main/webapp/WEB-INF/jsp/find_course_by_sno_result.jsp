<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>수강 과목 조회 결과</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid black; padding: 8px; }
    </style>
</head>
<body>
    <h2>수강 과목 조회 결과</h2>
    
    <table>
        <tr>
            <th>과목명</th>
            <th>학점</th>
            <th>시험점수</th>
        </tr>
        <c:forEach items="${find_course_by_sno}" var="course">
            <tr>
                <td>${course.cname}</td>
                <td>${course.grade}</td>
                <td>${course.exam}</td>
            </tr>
        </c:forEach>
    </table>

    <p>
        <a href="/university/find_course_by_sno">돌아가기</a>
    </p>

    <!-- 디버깅 정보 -->
    <hr>
    <p>조회된 데이터 수: ${find_course_by_sno.size()}</p>
</body>
</html>