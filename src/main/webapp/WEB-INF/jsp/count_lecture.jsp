<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>University Lecture</title>
</header>
<body>
<h2> Lecture Data와 Tuple의 총 갯수 </h2>
<c var="lecture_cnt">
    <p>튜플의 총 갯수: ${lecture_cnt}</p>
</c>
<table width="500" border="1">
    <tr>
        <td width="100">CNO</td>
        <td width="100">CNAME</td>
        <td width="100">CREDIT</td>
        <td width="100">SESSIONS</td>
    </tr>
    <c:forEach items="${lectures}" var="lecture">
        <tr>
            <td width="100">${lecture.getCno()}</td>
            <td width="100">${lecture.getPno()}</td>
            <td width="100">${lecture.getLec_time()}</td>
            <td width="100">${lecture.getRoom()}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>