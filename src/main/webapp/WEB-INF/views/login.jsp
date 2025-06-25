<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 - 대학 온라인 도서관</title>
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
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">로그인</h3>
                    </div>
                    <div class="card-body">
                        <c:if test="${param.registered != null}">
                            <div class="alert alert-success">
                                회원가입이 완료되었습니다. 로그인해주세요.
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                ${error}
                            </div>
                        </c:if>
                        
                        <form:form modelAttribute="loginDto" method="post">
                            <div class="mb-3">
                                <label for="email" class="form-label">이메일</label>
                                <form:input path="email" type="email" class="form-control" id="email" required="true"/>
                                <form:errors path="email" cssClass="text-danger"/>
                            </div>
                            
                            <div class="mb-3">
                                <label for="password" class="form-label">비밀번호</label>
                                <form:password path="password" class="form-control" id="password" required="true"/>
                                <form:errors path="password" cssClass="text-danger"/>
                            </div>
                            
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">로그인</button>
                            </div>
                        </form:form>
                        
                        <hr>
                        
                        <div class="d-grid gap-2">
                            <a href="/oauth2/authorization/kakao" class="btn btn-warning">
                                <img src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png" 
                                     alt="카카오 로그인" style="width: 20px; height: 20px; margin-right: 8px;">
                                카카오로 로그인
                            </a>
                        </div>
                        
                        <div class="text-center mt-3">
                            <p>계정이 없으신가요? <a href="/register">회원가입</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 