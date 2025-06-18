<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enroll Data와 Tuple의 총 갯수</title>
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
        .total-count {
            font-weight: bold;
            margin: 20px 0;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
    <h2>Enroll Data와 Tuple의 총 갯수</h2>
    
    <p class="total-count">튜플의 총 갯수: ${enrolls.size()}</p>

    <table>
        <tr>
            <th>SNO</th>
            <th>CNO</th>
            <th>GRADE</th>
            <th>EXAM</th>
        </tr>
        <c:forEach items="${enrolls}" var="enroll">
            <tr>
                <td>${enroll.sno}</td>
                <td>${enroll.cno}</td>
                <td>${enroll.grade}</td>
                <td>${enroll.exam}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html> 