<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서 목록 - 대학 온라인 도서관</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="/">대학 온라인 도서관</a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="/dashboard">대시보드</a>
                <a class="nav-link" href="/books">도서 목록</a>
                <a class="nav-link" href="/books/my-rentals">내 대출 현황</a>
                <a class="nav-link" href="/logout">로그아웃</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-8">
                <h2>도서 목록</h2>
            </div>
            <div class="col-md-4 text-end">
                <a href="/books/add" class="btn btn-success">도서 등록</a>
            </div>
        </div>

        <!-- 검색 폼 -->
        <div class="row mb-4">
            <div class="col-md-12">
                <form action="/books" method="get" class="d-flex">
                    <input type="text" name="keyword" value="${keyword}" class="form-control me-2" placeholder="도서명 또는 저자명으로 검색...">
                    <button type="submit" class="btn btn-primary">검색</button>
                </form>
            </div>
        </div>

        <!-- 도서 목록 -->
        <div class="row">
            <c:forEach items="${books}" var="book">
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${book.title}</h5>
                            <p class="card-text">
                                <strong>저자:</strong> ${book.author}<br>
                                <strong>출판년도:</strong> ${book.year}<br>
                                <strong>가격:</strong> <fmt:formatNumber value="${book.price}" pattern="#,###"/>원<br>
                                <strong>상태:</strong> 
                                <c:choose>
                                    <c:when test="${book.available}">
                                        <span class="badge bg-success">대출 가능</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">대출 중</span>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <div class="card-footer">
                            <div class="d-grid gap-2">
                                <a href="/books/${book.isbn}" class="btn btn-outline-primary btn-sm">상세보기</a>
                                <c:if test="${book.available}">
                                    <form action="/books/${book.isbn}/rent" method="post" style="display: inline;">
                                        <button type="submit" class="btn btn-success btn-sm w-100">대출하기</button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty books}">
            <div class="text-center mt-5">
                <h4>검색 결과가 없습니다.</h4>
                <p>다른 키워드로 검색해보세요.</p>
            </div>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 