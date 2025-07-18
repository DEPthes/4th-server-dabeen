<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>재고가 특정 부수 이상 넘어가는 도서 검색 결과 및 가격 할인 입력</title>
</head>
<body>
<h2>재고가 특정 부수 이상 넘어가는 도서 검색 결과 및 가격 할인 입력</h2>
<c:if test="${not empty books}">
    <table border="1">
        <tr>
            <th>ISBN</th>
            <th>제목</th>
            <th>재고</th>
            <th>가격</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.isbn}</td>
                <td>${book.title}</td>
                <td>${book.sumNum}</td>
                <td>${book.price}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>할인 적용</h2>
    <form action="/onlinebookstore/stock_above/${stock}/discount" method="post">
        <p>할인율: <input type="number" name="discountRate" step="0.1" min="0" required/></p>
        <p><input type="submit" value="적용"/></p>
    </form>
</c:if>
<c:if test="${empty books}">
    <p>검색 결과가 없습니다.</p>
</c:if>
</body>
</html>
