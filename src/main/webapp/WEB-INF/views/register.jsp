<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 - 대학 온라인 도서관</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="/">대학 온라인 도서관</a>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">회원가입</h3>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                ${error}
                            </div>
                        </c:if>
                        
                        <form:form modelAttribute="userDto" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="email" class="form-label">이메일 *</label>
                                        <form:input path="email" type="email" class="form-control" id="email" required="true"/>
                                        <form:errors path="email" cssClass="text-danger"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="name" class="form-label">이름 *</label>
                                        <form:input path="name" class="form-control" id="name" required="true"/>
                                        <form:errors path="name" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="password" class="form-label">비밀번호 *</label>
                                        <form:password path="password" class="form-control" id="password" required="true"/>
                                        <form:errors path="password" cssClass="text-danger"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="userType" class="form-label">사용자 유형 *</label>
                                        <form:select path="userType" class="form-select" id="userType" required="true">
                                            <option value="">선택하세요</option>
                                            <option value="STUDENT">학생</option>
                                            <option value="PROFESSOR">교수</option>
                                        </form:select>
                                        <form:errors path="userType" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="department" class="form-label">학과/부서</label>
                                        <form:input path="department" class="form-control" id="department"/>
                                        <form:errors path="department" cssClass="text-danger"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="phone" class="form-label">전화번호</label>
                                        <form:input path="phone" class="form-control" id="phone"/>
                                        <form:errors path="phone" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="address" class="form-label">주소</label>
                                <form:input path="address" class="form-control" id="address"/>
                                <form:errors path="address" cssClass="text-danger"/>
                            </div>
                            
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">회원가입</button>
                            </div>
                        </form:form>
                        
                        <div class="text-center mt-3">
                            <p>이미 계정이 있으신가요? <a href="/login">로그인</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 