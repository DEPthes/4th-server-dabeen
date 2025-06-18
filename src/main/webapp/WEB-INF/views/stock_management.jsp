<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>재고 관리</title>
</head>
<body>
    <h1>재고 관리</h1>

    <!-- 재고 검색 폼 -->
    <form action="/onlinebookstore/stock_above/search" method="get">
        <p>
            <label for="stock">최소 재고 수량:</label>
            <input type="number" id="stock" name="stock" value="${minStock}" required min="0">
            <button type="submit">검색</button>
        </p>
    </form>

    <!-- 검색 결과가 있을 경우 -->
    <c:if test="${not empty books}">
        <h2>검색 결과 (${minStock}권 이상의 재고)</h2>
        
        <table border="1">
            <tr>
                <th>ISBN</th>
                <th>제목</th>
                <th>출판년도</th>
                <th>가격</th>
                <th>재고 수량</th>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.isbn}</td>
                    <td>${book.title}</td>
                    <td>${book.year}</td>
                    <td><fmt:formatNumber value="${book.price}" pattern="#,##0" />원</td>
                    <td>${book.stock}권</td>
                </tr>
            </c:forEach>
        </table>

        <!-- 가격 할인 폼 -->
        <h2>가격 할인 적용</h2>
        <form action="/onlinebookstore/stock_above/discount" method="post">
            <input type="hidden" name="minStock" value="${minStock}">
            <p>
                <label for="discountRate">할인율 (%):</label>
                <input type="number" id="discountRate" name="discountRate" min="1" max="100" required>
                <button type="submit">할인 적용</button>
            </p>
        </form>
    </c:if>

    <!-- 성공 메시지 -->
    <c:if test="${param.success eq 'true'}">
        <p>가격 할인이 성공적으로 적용되었습니다.</p>
    </c:if>

    <!-- 에러 메시지 -->
    <c:if test="${param.error eq 'true'}">
        <p>가격 할인 적용 중 오류가 발생했습니다.</p>
    </c:if>

    <p>
        <a href="/onlinebookstore/insert_book">도서 등록으로 돌아가기</a> |
        <a href="/onlinebookstore/book_prices">도서 가격 통계 보기</a> |
        <a href="/onlinebookstore/author_statistics">작가별 통계 보기</a>
    </p>
</body>
</html> 