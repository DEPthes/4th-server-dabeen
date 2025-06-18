<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>University Course</title>
</head>
<body>
<c var="course_cnt">
    <h2>Course Data와 Tuple의 총 갯수</h2>
    <p>튜플의 총 갯수: ${course_cnt}</p>
</c>
<table width="500" border="1">
    <tr>
        <td width="100">CNO</td>
        <td width="100">CNAME</td>
        <td width="100">CREDIT</td>
        <td width="100">SESSIONS</td>
    </tr>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td width="100">${course.getCno()}</td>
            <td width="100">${course.getCname()}</td>
            <td width="100">${course.getCredit()}</td>
            <td width="100">${course.getSessions()}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>