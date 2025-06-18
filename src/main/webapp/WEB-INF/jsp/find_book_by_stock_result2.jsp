<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>할인 적용 결과</title>
</head>
<body>
<h2>할인 적용 결과</h2>
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
    <p>할인율이 성공적으로 적용되었습니다.</p>
</c:if>
<c:if test="${empty books}">
    <p>검색 결과가 없습니다.</p>
</c:if>
</body>
</html>
