<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>시험점수가 잘못된 학생 정보, 수정요청에 정상수정 완료</title>
    <style>
        table { 
            border-collapse: collapse; 
            width: 100%; 
            margin: 20px 0;
        }
        th, td { 
            border: 1px solid black; 
            padding: 8px; 
            text-align: left;
        }
        .error {
            color: red;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <h2>시험점수가 잘못된 학생 정보, 수정요청에 정상수정 완료</h2>
    
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <c:if test="${not empty after_update}">
        <h3>수정된 학생의 정보:</h3>
        <table>
            <tr>
                <th>이름 (SNAME)</th>
                <th>학번 (SNO)</th>
                <th>과목번호 (CNO)</th>
                <th>점수 (EXAM)</th>
                <th>학점 (GRADE)</th>
            </tr>
            <tr>
                <td>${after_update.sname}</td>
                <td>${after_update.sno}</td>
                <td>${after_update.cno}</td>
                <td>${after_update.exam}</td>
                <td>${after_update.grade}</td>
            </tr>
        </table>
    </c:if>

    <p><a href="/university/check_wrong_grades">목록으로 돌아가기</a></p>
</body>
</html>