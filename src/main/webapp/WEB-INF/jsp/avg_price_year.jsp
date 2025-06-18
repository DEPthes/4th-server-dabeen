<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>전체 도서의 평균 가격 및 년도별 평균 가격</title>
</head>
<body>
<h2>전체 도서의 평균 가격 및 년도별 평균 가격</h2>
<p>전체 도서의 평균: ${avg_price_year}</p>

<table width="300" border="1">
    <tr>
        <td width="150">YEAR</td>
        <td width="150">AVG(PRICE)</td>
    </tr>
    <c:forEach items="${avg_price_book_by_year}" var="avg_price_book_by_year">
        <tr>
            <td width="150">${avg_price_book_by_year.getYear()}</td>
            <td width="150">${avg_price_book_by_year.getAvgPrice()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>