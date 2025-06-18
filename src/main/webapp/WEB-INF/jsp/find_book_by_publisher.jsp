<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>출판 정보와 특정 출판사 클릭 시 해당 출판사에서 출판한 도서제목, 가격, 재고량 확인. 재고량 없을 시 재고없음</title>
</head>
<body>
<h2>출판 정보와 특정 출판사 클릭 시 해당 출판사에서 출판한 도서제목, 가격, 재고량 확인.</h2>

<table width="700" border="1">
    <tr>
        <td width="150">NAME</td>
        <td width="200">ADDRESS</td>
        <td width="150">PHONE</td>
        <td width="200">URL</td>
    </tr>
    <c:forEach items="${Publishers}" var="publisher">
        <tr>
            <td><a href="${pageContext.request.contextPath}/bookstore/find_book_by_publisher/${publisher.name}">${publisher.name}</a></td>
            <td width="200">${publisher.address}</td>
            <td width="150">${publisher.phone}</td>
            <td width="100">${publisher.url}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>