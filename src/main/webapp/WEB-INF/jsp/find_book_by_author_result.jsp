<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>도서 검색 결과</title>
</head>
<body>
<h2>도서 검색 결과</h2>
<table width="1000" border="1">
    <tr>
        <td width="200">NAME</td>
        <td width="300">TITLE</td>
        <td width="200">STOCK</td>
    </tr>

    <c:forEach items="${find_book_by_author}" var="find_book_by_author">
        <tr>
            <td width="200">${find_book_by_author.getName()}</td>
            <td width="300">${find_book_by_author.getTitle()}</td>
            <td width="200">${find_book_by_author.getStockNum()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>