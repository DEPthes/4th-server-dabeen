<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
        }
        h1 {
            color: #333;
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
        }
        .menu {
            margin: 20px 0;
            padding: 20px;
            background: #f5f5f5;
            border-radius: 5px;
        }
        .menu h2 {
            margin-top: 0;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the DB 과제 프로젝트</h1>
        
        <div class="menu">
            <h2>University System</h2>
            <ul>
                <li>데이터 조회:
                    <a href="/university/student">학생</a> |
                    <a href="/university/professor">교수</a> |
                    <a href="/university/course">과목</a> |
                    <a href="/university/enroll">수강</a> |
                    <a href="/university/lecture">강의</a>
                </li>
                <li>학생 관련:
                    <a href="/university/find_course_by_sno">수강과목 조회</a> |
                    <a href="/university/sum_credit_avg_exam">학점 및 평균</a>
                </li>
                <li>성적 관리:
                    <a href="/university/update_grade">학점 수정</a> |
                    <a href="/university/top_scorers">최고점수</a> |
                    <a href="/university/lowest_scorers">최저점수</a>
                </li>
                <li>통계:
                    <a href="/university/avg_exam_student">과목별 평균/분포</a> |
                    <a href="/university/count_lecture_by_dept">학과별 강좌수</a> |
                    <a href="/university/count_sno_by_dept_year">학과/학년별 학생수</a>
                </li>
            </ul>
        </div>

        <div class="menu">
            <h2>Online Bookstore</h2>
            <ul>
                <li>도서 검색: <a href="/bookstore/find_book_by_author">작가별</a> | <a href="/bookstore/find_book_by_publisher">출판사별</a></li>
                <li>고객 주문: <a href="/bookstore/find_book_by_customer">조회하기</a></li>
                <li>도서 통계: <a href="/bookstore/avg_price_year">가격 통계</a> | <a href="/bookstore/book_statistics_by_author">작가별 통계</a></li>
                <li>도서 관리: <a href="/bookstore/insert_book/form">도서 등록</a></li>
            </ul>
        </div>

        <div class="menu">
            <h2>학생 및 수강신청 관리</h2>
            <a href="/university/enroll/all">전체 수강신청 조회</a>
            <a href="/university/check_wrong_grades">잘못된 성적 조회</a>
            
            <h2>통계 및 분석</h2>
            <a href="/university/course/score-stats">과목별 최고/최저 점수 통계</a>
            <a href="/university/student/credit-stats">학생별 이수학점 및 평균점수</a>
            <a href="/university/course/grade-stats">과목별 성적 통계</a>
            <a href="/university/department/lecture-stats">학과별 개설 강좌 수</a>
            <a href="/university/department/student-stats">학과별/학년별 학생 수</a>
        </div>
    </div>
</body>
</html> 