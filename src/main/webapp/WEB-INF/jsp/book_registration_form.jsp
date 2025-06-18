<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>새로운 도서가 등록될 경우에는 저자와 출판사 창고별 재고량이 함께 등록</title>
</head>
<body>
<h2>새로운 도서가 등록될 경우에는 저자와 출판사 창고별 재고량이 함께 등록</h2>
<form name="insert_book_form" method="post" action="/onlinebookstore/insert_book">
    <p>ISBN: <input type="text" name="isbn"></p>
    <p>TITLE: <input type="text" name="title"></p>
    <p>YEAR: <input type="text" name="year"></p>
    <p>PRICE: <input type="text" name="price"></p>

    <p>AUTHOR_NAME: <input type="text" name="authorName"></p>
    <p>AUTHOR_ADDRESS: <input type="text" name="authorAddress"></p>
    <p>AUTHOR_URL: <input type="text" name="authorUrl"></p>

    <p>WAREHOUSE_CODE: <input type="text" name="code"></p>
    <p>WAREHOUSE_ADDRESS: <input type="text" name="wareAddress"></p>
    <p>WAREHOUSE_PHONE: <input type="text" name="warePhone"></p>

    <p>STOCKS_NUM: <input type="text" name="num"></p>

    <p><input type="submit" name="Submit" value="등록"></p>
</form>
</body>
</html>