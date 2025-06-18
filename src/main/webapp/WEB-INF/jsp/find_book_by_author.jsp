<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>작가 클릭 후 도서 검색</title>
</head>
<body>
<h2>특정 작가를 선택하였을 경우 해당 작가가 작성한 도서의 제목, 해당 도서의 재고 수량 확인</h2>

<table width="700" border="1">
    <tr>
        <td width="200">NAME</td>
    </tr>

    <c:forEach items="${Authors}" var="author">
        <tr>
            <td><a href="${pageContext.request.contextPath}/bookstore/find_book_by_author/${author.name}">${author.name}</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>