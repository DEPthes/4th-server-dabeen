<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>시험점수가 잘못된 학생 정보</title>
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
        form {
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <h2>시험점수가 잘못된 학생 정보, 수정요청에 정상수정</h2>
    
    <p>(단, 학점 부여방식은 다음과 같다. 60점 미만: F, 60~69: D, 70~79: C, 80~89: B, 90 이상: A)</p>

    <table>
        <tr>
            <th>이름 (SNAME)</th>
            <th>학번 (SNO)</th>
            <th>과목번호 (CNO)</th>
            <th>점수 (EXAM)</th>
            <th>학점 (GRADE)</th>
        </tr>
        <c:forEach items="${wrong_grades}" var="grade">
            <tr>
                <td>${grade.sname}</td>
                <td>${grade.sno}</td>
                <td>${grade.cno}</td>
                <td>${grade.exam}</td>
                <td>${grade.grade}</td>
            </tr>
        </c:forEach>
    </table>

    <form action="/university/update_grade_result" method="post">
        SNO: <input type="text" name="sno" required>
        CNO: <input type="text" name="cno" required>
        <button type="submit">수정</button>
    </form>
</body>
</html> 