<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내 대출 현황 - 대학 온라인 도서관</title>
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
        <h2>내 대출 현황</h2>
        <p class="text-muted">안녕하세요, ${user.name}님!</p>

        <!-- 성공/오류 메시지 -->
        <c:if test="${param.success == 'rented'}">
            <div class="alert alert-success">
                도서가 성공적으로 대출되었습니다.
            </div>
        </c:if>
        <c:if test="${param.success == 'returned'}">
            <div class="alert alert-success">
                도서가 성공적으로 반납되었습니다.
            </div>
        </c:if>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                오류가 발생했습니다: ${param.error}
            </div>
        </c:if>

        <!-- 현재 대출 중인 도서 -->
        <div class="card mb-4">
            <div class="card-header">
                <h5 class="mb-0">현재 대출 중인 도서 (${activeRentals.size()}권)</h5>
            </div>
            <div class="card-body">
                <c:if test="${empty activeRentals}">
                    <p class="text-muted">현재 대출 중인 도서가 없습니다.</p>
                </c:if>
                <c:forEach items="${activeRentals}" var="rental">
                    <div class="row align-items-center border-bottom py-2">
                        <div class="col-md-6">
                            <h6>ISBN: ${rental.bookIsbn}</h6>
                            <small class="text-muted">
                                대출일: <fmt:formatDate value="${rental.rentalDate}" pattern="yyyy-MM-dd"/><br>
                                반납예정일: <fmt:formatDate value="${rental.dueDate}" pattern="yyyy-MM-dd"/>
                            </small>
                        </div>
                        <div class="col-md-3">
                            <c:choose>
                                <c:when test="${rental.status == 'OVERDUE'}">
                                    <span class="badge bg-danger">연체</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-primary">대출 중</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-3 text-end">
                            <form action="/books/${rental.bookIsbn}/return" method="post" style="display: inline;">
                                <button type="submit" class="btn btn-warning btn-sm">반납하기</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- 전체 대출 기록 -->
        <div class="card">
            <div class="card-header">
                <h5 class="mb-0">전체 대출 기록 (${rentals.size()}건)</h5>
            </div>
            <div class="card-body">
                <c:if test="${empty rentals}">
                    <p class="text-muted">대출 기록이 없습니다.</p>
                </c:if>
                <c:forEach items="${rentals}" var="rental">
                    <div class="row align-items-center border-bottom py-2">
                        <div class="col-md-4">
                            <h6>ISBN: ${rental.bookIsbn}</h6>
                        </div>
                        <div class="col-md-3">
                            <small class="text-muted">
                                대출일: <fmt:formatDate value="${rental.rentalDate}" pattern="yyyy-MM-dd"/>
                            </small>
                        </div>
                        <div class="col-md-3">
                            <small class="text-muted">
                                <c:if test="${rental.returnDate != null}">
                                    반납일: <fmt:formatDate value="${rental.returnDate}" pattern="yyyy-MM-dd"/>
                                </c:if>
                            </small>
                        </div>
                        <div class="col-md-2">
                            <c:choose>
                                <c:when test="${rental.status == 'RENTED'}">
                                    <span class="badge bg-primary">대출 중</span>
                                </c:when>
                                <c:when test="${rental.status == 'RETURNED'}">
                                    <span class="badge bg-success">반납 완료</span>
                                </c:when>
                                <c:when test="${rental.status == 'OVERDUE'}">
                                    <span class="badge bg-danger">연체</span>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="mt-4">
            <a href="/books" class="btn btn-primary">도서 목록으로 돌아가기</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 