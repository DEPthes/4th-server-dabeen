<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>과목별 성적 통계</title>
    <style>
        table { border-collapse: collapse; width: 100%; margin-bottom: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .course-header { background-color: #e6f3ff; }
        .grade-row td { padding-left: 20px; }
        .no-border-top { border-top: none; }
        .no-border-bottom { border-bottom: none; }
    </style>
</head>
<body>
    <h1>과목별 성적 통계</h1>
    <table>
        <tr>
            <th>과목번호</th>
            <th>과목명</th>
            <th>평균점수</th>
            <th>학점</th>
            <th>학생 수</th>
        </tr>
        <c:set var="prevCno" value="" />
        <c:forEach items="${gradeStats}" var="stat" varStatus="status">
            <c:choose>
                <c:when test="${stat.cno ne prevCno}">
                    <tr class="course-header">
                        <td>${stat.cno}</td>
                        <td>${stat.cname}</td>
                        <td><fmt:formatNumber value="${stat.avgExam}" pattern="0.00"/></td>
                        <td>${stat.grade}</td>
                        <td>${stat.countStudents}명</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr class="grade-row">
                        <td colspan="2"></td>
                        <td></td>
                        <td>${stat.grade}</td>
                        <td>${stat.countStudents}명</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            <c:set var="prevCno" value="${stat.cno}" />
        </c:forEach>
    </table>
    <p><a href="/university">메인으로 돌아가기</a></p>
</body>
</html> 