<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>작가별 도서의 총 개수, 최고, 최저, 평균 가격 확인</title>
</head>
<body>
<h2>작가별 도서의 총 개수, 최고, 최저, 평균 가격 확인</h2>
<table width="650" border="1">
    <tr>
        <td width="150">작가</td>
        <td width="150">도서의 총개수</td>
        <td width="100">최고가</td>
        <td width="100">최저가</td>
        <td width="150">평균가</td>
    </tr>
    <c:forEach items="${book_statistics_by_author}" var="book_statistics_by_author">
        <tr>
            <td width="150">${book_statistics_by_author.getName()}</td>
            <td width="150">${book_statistics_by_author.getCountBook()}</td>
            <td width="100">${book_statistics_by_author.getMaxPrice()}</td>
            <td width="100">${book_statistics_by_author.getMinPrice()}</td>
            <td width="150">${book_statistics_by_author.getAvgPrice()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>