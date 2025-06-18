<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>작가별 도서 통계</title>
</head>
<body>
    <h1>작가별 도서 통계</h1>

    <table border="1">
        <tr>
            <th>작가 이름</th>
            <th>도서 총 개수</th>
            <th>최고 가격</th>
            <th>최저 가격</th>
            <th>평균 가격</th>
        </tr>
        <c:forEach items="${authorStats}" var="stat">
            <tr>
                <td>${stat.name}</td>
                <td>${stat.countBook}권</td>
                <td><fmt:formatNumber value="${stat.maxPrice}" pattern="#,##0" />원</td>
                <td><fmt:formatNumber value="${stat.minPrice}" pattern="#,##0" />원</td>
                <td><fmt:formatNumber value="${stat.avgPrice}" pattern="#,##0" />원</td>
            </tr>
        </c:forEach>
    </table>

    <p>
        <a href="/onlinebookstore/insert_book">도서 등록으로 돌아가기</a> |
        <a href="/onlinebookstore/book_prices">도서 가격 통계 보기</a>
    </p>
</body>
</html> 