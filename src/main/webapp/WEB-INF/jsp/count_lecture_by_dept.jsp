<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>학과별로 개설된 강좌(Lecture)의 수를 확인</title>
</header>
<body>
<h2>학과별로 개설된 강좌(Lecture)의 수를 확인</h2>

<table width="200" border="1">
    <tr>
        <td width="100">PDEPT</td>
        <td width="100">COUNT(CNO)</td>
    </tr>
    <c:forEach items="${count_lecture_by_dept}" var="count_lecture_by_dept">
        <tr>
            <td width="100">${count_lecture_by_dept.getPdept()}</td>
            <td width="100">${count_lecture_by_dept.getCountCno()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>