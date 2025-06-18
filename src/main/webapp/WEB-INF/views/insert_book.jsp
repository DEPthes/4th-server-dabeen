<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서 등록</title>
</head>
<body>
    <h1>도서 등록</h1>
    
    <% if (request.getParameter("error") != null) { %>
        <p>도서 등록 중 오류가 발생했습니다.</p>
    <% } %>
    
    <% if (request.getParameter("success") != null) { %>
        <p>도서가 등록되었습니다.</p>
    <% } %>

    <form action="/onlinebookstore/insert_book" method="post">
        <p>
            <label for="isbn">ISBN:</label>
            <input type="number" id="isbn" name="isbn" required>
        </p>
        <p>
            <label for="title">도서명:</label>
            <input type="text" id="title" name="title" required>
        </p>
        <p>
            <label for="year">출판년도:</label>
            <input type="number" id="year" name="year" required>
        </p>
        <p>
            <label for="price">가격:</label>
            <input type="number" id="price" name="price" required>
        </p>
        <p>
            <label for="authorName">저자 이름:</label>
            <input type="text" id="authorName" name="authorName" required>
        </p>
        <p>
            <label for="authorAddress">저자 주소:</label>
            <input type="text" id="authorAddress" name="authorAddress" required>
        </p>
        <p>
            <label for="authorUrl">저자 URL:</label>
            <input type="text" id="authorUrl" name="authorUrl" required>
        </p>
        <p>
            <label for="code">창고 코드:</label>
            <input type="text" id="code" name="code" required>
        </p>
        <p>
            <label for="wareAddress">창고 주소:</label>
            <input type="text" id="wareAddress" name="wareAddress" required>
        </p>
        <p>
            <label for="warePhone">창고 전화번호:</label>
            <input type="text" id="warePhone" name="warePhone" required>
        </p>
        <p>
            <label for="num">재고 수량:</label>
            <input type="number" id="num" name="num" required>
        </p>
        <p>
            <button type="submit">등록</button>
        </p>
    </form>
</body>
</html> 