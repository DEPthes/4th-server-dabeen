<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>대학 온라인 도서관</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="/">대학 온라인 도서관</a>
            <div class="navbar-nav ms-auto">
                <c:if test="${empty pageContext.request.userPrincipal}">
                    <a class="nav-link" href="/login">로그인</a>
                    <a class="nav-link" href="/register">회원가입</a>
                </c:if>
                <c:if test="${not empty pageContext.request.userPrincipal}">
                    <a class="nav-link" href="/dashboard">대시보드</a>
                    <a class="nav-link" href="/books">도서 목록</a>
                    <a class="nav-link" href="/logout">로그아웃</a>
                </c:if>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8">
                <h1>대학 온라인 도서관에 오신 것을 환영합니다</h1>
                <p class="lead">학생과 교수님들을 위한 온라인 도서관 시스템입니다.</p>
                
                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">도서 검색</h5>
                                <p class="card-text">다양한 도서를 검색하고 대출할 수 있습니다.</p>
                                <a href="/books" class="btn btn-primary">도서 목록 보기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">내 대출 현황</h5>
                                <p class="card-text">현재 대출 중인 도서와 반납 예정일을 확인하세요.</p>
                                <a href="/books/my-rentals" class="btn btn-success">대출 현황 보기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <h5>빠른 메뉴</h5>
                    </div>
                    <div class="card-body">
                        <ul class="list-unstyled">
                            <li><a href="/books" class="text-decoration-none">📚 도서 검색</a></li>
                            <li><a href="/books/my-rentals" class="text-decoration-none">📖 내 대출 현황</a></li>
                            <li><a href="/books/add" class="text-decoration-none">➕ 도서 등록</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 