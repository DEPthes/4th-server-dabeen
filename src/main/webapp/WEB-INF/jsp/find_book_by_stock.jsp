<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>재고가 특정 부수 이상 넘어가는 도서 검색</title>
</head>
<body>
<h2>재고가 특정 부수 이상 넘어가는 도서 검색</h2>
<form action="/onlinebookstore/stock_above/search" method="get">
    <p>재고: <input type="number" name="stock" min="0" required/></p>
    <p><input type="submit" value="검색"/></p>
</form>
</body>
</html>
