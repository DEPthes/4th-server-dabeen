<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>대시보드 - 대학 온라인 도서관</title>
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

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <h1>안녕하세요, ${user.name}님!</h1>
                <p class="lead">대학 온라인 도서관 대시보드입니다.</p>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">📚 도서 검색</h5>
                        <p class="card-text">다양한 도서를 검색하고 대출할 수 있습니다.</p>
                        <a href="/books" class="btn btn-primary">도서 목록 보기</a>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">📖 내 대출 현황</h5>
                        <p class="card-text">현재 대출 중인 도서와 반납 예정일을 확인하세요.</p>
                        <a href="/books/my-rentals" class="btn btn-success">대출 현황 보기</a>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">➕ 도서 등록</h5>
                        <p class="card-text">새로운 도서를 도서관에 등록할 수 있습니다.</p>
                        <a href="/books/add" class="btn btn-info">도서 등록</a>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>사용자 정보</h5>
                    </div>
                    <div class="card-body">
                        <table class="table table-borderless">
                            <tr>
                                <td><strong>이름:</strong></td>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <td><strong>이메일:</strong></td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td><strong>사용자 유형:</strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.userType == 'STUDENT'}">학생</c:when>
                                        <c:when test="${user.userType == 'PROFESSOR'}">교수</c:when>
                                        <c:otherwise>${user.userType}</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:if test="${not empty user.department}">
                                <tr>
                                    <td><strong>학과/부서:</strong></td>
                                    <td>${user.department}</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty user.phone}">
                                <tr>
                                    <td><strong>전화번호:</strong></td>
                                    <td>${user.phone}</td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
            
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>빠른 검색</h5>
                    </div>
                    <div class="card-body">
                        <form action="/books/search" method="get">
                            <div class="input-group">
                                <input type="text" name="title" class="form-control" placeholder="도서 제목을 입력하세요">
                                <button class="btn btn-outline-secondary" type="submit">검색</button>
                            </div>
                        </form>
                        
                        <div class="mt-3">
                            <h6>인기 검색어</h6>
                            <div class="d-flex flex-wrap gap-1">
                                <a href="/books/search?title=자바" class="badge bg-secondary text-decoration-none">자바</a>
                                <a href="/books/search?title=스프링" class="badge bg-secondary text-decoration-none">스프링</a>
                                <a href="/books/search?title=데이터베이스" class="badge bg-secondary text-decoration-none">데이터베이스</a>
                                <a href="/books/search?title=알고리즘" class="badge bg-secondary text-decoration-none">알고리즘</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 