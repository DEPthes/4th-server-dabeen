<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>결과</title>
</head>
<body>
<h2>출판 정보와 특정 출판사 클릭 시 해당 출판사에서 출판한 도서제목, 가격, 재고량 확인. 재고량 없을 시 재고없음을 결과</h2>
<table width="500" border="1">
    <tr>
        <td width="100">출판사</td>
        <td width="200">제목</td>
        <td width="100">가격</td>
        <td width="100">재고량</td>
    </tr>
    <c:forEach items="${find_book_by_publisher}" var="find_book_by_publisher">
        <tr>
            <td width="100">${find_book_by_publisher.getName()}</td>
            <td width="200">${find_book_by_publisher.getTitle()}</td>
            <td width="100">${find_book_by_publisher.getPrice()}</td>
            <td width="100">${find_book_by_publisher.getStock()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>