<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서 가격 통계</title>
</head>
<body>
    <h1>도서 가격 통계</h1>

    <h2>전체 도서의 평균 가격</h2>
    <p>
        <fmt:formatNumber value="${averagePrice}" pattern="#,##0" />원
    </p>

    <h2>년도별 평균 가격</h2>
    <table border="1">
        <tr>
            <th>출판년도</th>
            <th>평균 가격</th>
        </tr>
        <c:forEach items="${yearlyPrices}" var="price">
            <tr>
                <td>${price.year}년</td>
                <td><fmt:formatNumber value="${price.avgPrice}" pattern="#,##0" />원</td>
            </tr>
        </c:forEach>
    </table>

    <p>
        <a href="/onlinebookstore/insert_book">도서 등록으로 돌아가기</a>
    </p>
</body>
</html> 