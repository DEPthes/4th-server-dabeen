<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>특정 고객 선택시, 해당 고객이 주문한 도서, 해당 도서의 출판사, 저자의 정보</title>
</head>
<body>
<h2>특정 고객 선택시, 해당 고객이 주문한 도서, 해당 도서의 출판사, 저자의 정보</h2>


<table width="800" border="1">
    <tr>
        <td width="200">EMAIL</td>
        <td width="200">NAME</td>
        <td width="200">ADDRESS</td>
        <td width="200">PHONE</td>
    </tr>
    <c:forEach items="${Customers}" var="Customers">
        <tr>
            <td><a href="find_book_by_customer/${Customers.email}">${Customers.email}</a></td>
            <td width="200">${Customers.getName()}</td>
            <td width="200">${Customers.getAddress()}</td>
            <td width="200">${Customers.getPhone()}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>