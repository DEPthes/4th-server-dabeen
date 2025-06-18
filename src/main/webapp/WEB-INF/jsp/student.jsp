<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student 테이블 조회</title>
    <style>
        table { 
            border-collapse: collapse; 
            width: 100%; 
        }
        th, td { 
            border: 1px solid black; 
            padding: 8px; 
            text-align: left; 
        }
        th { 
            background-color: #f2f2f2; 
        }
    </style>
</head>
<body>
    <h2>Student 테이블 조회</h2>
    
    <table>
        <tr>
            <th>학번(SNO)</th>
            <th>이름(SNAME)</th>
            <th>학년(YEAR)</th>
            <th>학과(DEPT)</th>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.sno}</td>
                <td>${student.sname}</td>
                <td>${student.year}</td>
                <td>${student.dept}</td>
            </tr>
        </c:forEach>
    </table>

    <p>총 학생 수: ${students.size()}</p>
</body>
</html> 