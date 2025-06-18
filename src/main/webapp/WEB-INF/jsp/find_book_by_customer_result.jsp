<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>특정 고객 선택시, 해당 고객이 주문한 도서, 해당 도서의 출판사, 저자의 정보</title>
</head>
<body>
<h2>특정 고객 선택시, 해당 고객이 주문한 도서, 해당 도서의 출판사, 저자의 정보 결과</h2>
<table width="800" border="1">
    <tr>
        <td width="200">고객이메일</td>
        <td width="200">책제목</td>
        <td width="200">출판사명</td>
        <td width="200">작가이름</td>
    </tr>
    <c:forEach items="${find_book_by_customer}" var="find_book_by_customer">
        <tr>
            <td width="200">${find_book_by_customer.getEmail()}</td>
            <td width="200">${find_book_by_customer.getBookName()}</td>
            <td width="200">${find_book_by_customer.getPublisherName()}</td>
            <td width="200">${find_book_by_customer.getAuthorName()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>