-- 대학 정보 시스템 쿼리

-- 1-(a) 각 테이블의 데이터 조회
SELECT * FROM STUDENT ORDER BY SNO;
SELECT * FROM PROFESSOR;
SELECT * FROM COURSE;
SELECT * FROM LECTURE;
SELECT * FROM ENROLL ORDER BY SNO, CNO;

-- 1-(b) 특정 학생의 수강 과목 이름, 학점, 시험 점수
SELECT C.CNAME as cname, E.GRADE as grade, E.EXAM as exam 
FROM ENROLL E, COURSE C 
WHERE E.CNO = C.CNO AND E.SNO = :SNO;

-- 1-(c) 잘못된 학점 확인
SELECT S.SNAME as sname, E.SNO as sno, E.CNO as cno, E.EXAM as exam, E.GRADE as grade 
FROM STUDENT S, ENROLL E 
WHERE S.SNO = E.SNO AND 
    ((CAST(E.EXAM AS INTEGER) >= 90 AND E.GRADE != 'A') OR 
     (CAST(E.EXAM AS INTEGER) >= 80 AND CAST(E.EXAM AS INTEGER) < 90 AND E.GRADE != 'B') OR 
     (CAST(E.EXAM AS INTEGER) >= 70 AND CAST(E.EXAM AS INTEGER) < 80 AND E.GRADE != 'C') OR 
     (CAST(E.EXAM AS INTEGER) >= 60 AND CAST(E.EXAM AS INTEGER) < 70 AND E.GRADE != 'D') OR 
     (CAST(E.EXAM AS INTEGER) < 60 AND E.GRADE != 'F'));

-- 1-(c) 학점 수정
UPDATE ENROLL SET GRADE = 
CASE 
    WHEN CAST(EXAM AS INTEGER) >= 90 THEN 'A' 
    WHEN CAST(EXAM AS INTEGER) >= 80 THEN 'B' 
    WHEN CAST(EXAM AS INTEGER) >= 70 THEN 'C' 
    WHEN CAST(EXAM AS INTEGER) >= 60 THEN 'D' 
    ELSE 'F' 
END 
WHERE SNO = :sno AND CNO = :cno;

-- 1-(d) 과목별 최고/최저 점수 학생 정보
SELECT c.cno, c.cname, 
       MAX(e.exam) as max_score, MIN(e.exam) as min_score,
       s_max.sno as max_sno, s_max.sname as max_sname,
       s_min.sno as min_sno, s_min.sname as min_sname
FROM Course c
JOIN Enroll e ON c.cno = e.cno
JOIN Student s_max ON e.sno = s_max.sno AND e.exam = (
    SELECT MAX(exam) FROM Enroll WHERE cno = c.cno
)
JOIN Student s_min ON e.sno = s_min.sno AND e.exam = (
    SELECT MIN(exam) FROM Enroll WHERE cno = c.cno
)
GROUP BY c.cno, c.cname, s_max.sno, s_max.sname, s_min.sno, s_min.sname;

-- 1-(e) 학생별 총학점과 평균 점수
SELECT s.sno, s.sname,
       SUM(c.credit) as total_credits,
       AVG(e.exam) as avg_score
FROM Student s
JOIN Enroll e ON s.sno = e.sno
JOIN Course c ON e.cno = c.cno
GROUP BY s.sno, s.sname;

-- 1-(f) 과목별 평균 점수와 학점별 학생 수
SELECT 
    c.cno, c.cname, 
    ROUND(AVG(CAST(e.exam AS DECIMAL)), 2) as avg_exam,
    e.grade, COUNT(e.sno) as count_students
FROM Course c
JOIN Enroll e ON c.cno = e.cno
GROUP BY c.cno, c.cname, e.grade
ORDER BY c.cno, e.grade;

-- 1-(g) 학과별 개설 강좌 수
SELECT p.PDEPT as dname,
       COUNT(DISTINCT l.CNO) as lecture_count
FROM PROFESSOR p
LEFT JOIN LECTURE l ON p.PNO = l.PNO
GROUP BY p.PDEPT
ORDER BY p.PDEPT;

-- 1-(h) 학과별, 학년별 학생 수
SELECT STD.DEPT as dname,
       STD.YEAR as year,
       COUNT(*) as student_count
FROM STUDENT STD
GROUP BY STD.DEPT, STD.YEAR
ORDER BY STD.DEPT, STD.YEAR;

-- 온라인 서점 시스템 쿼리

-- 2-(a) 특정 작가의 도서 정보 조회
SELECT wb.NAME, b.TITLE, SUM(s.NUM) AS stockNum
FROM WRITTEN_BY wb
JOIN BOOK b ON wb.ISBN = b.ISBN
LEFT JOIN STOCKS s ON b.ISBN = s.ISBN
WHERE wb.NAME = :NAME
GROUP BY wb.NAME, b.TITLE;

-- 2-(b) 출판사별 도서 정보 조회
SELECT pb.NAME, b.TITLE, b.PRICE,
       CASE WHEN SUM(s.NUM) IS NULL OR SUM(s.NUM) = 0 
            THEN '재고없음' 
            ELSE TO_CHAR(SUM(s.NUM)) 
       END AS STOCK
FROM PUBLISHED_BY pb
JOIN BOOK b ON pb.ISBN = b.ISBN
LEFT JOIN STOCKS s ON b.ISBN = s.ISBN
WHERE pb.NAME = :NAME
GROUP BY pb.NAME, b.TITLE, b.PRICE
ORDER BY b.TITLE;

-- 2-(c) 고객별 주문 도서 정보 조회
SELECT S.email AS email,
       B.TITLE AS bookName,
       P.NAME AS publisherName,
       W.NAME AS authorName
FROM BASKET_OF S, CONTAINS C, BOOK B, PUBLISHED_BY P, WRITTEN_BY W
WHERE S.EMAIL = :email AND S.basketid = C.basketid
AND C.isbn = B.isbn AND B.isbn = P.isbn AND B.isbn = W.isbn;

-- 2-(d) 새로운 도서 등록 관련 쿼리들
-- 도서 정보 등록
MERGE INTO BOOK USING DUAL 
ON (ISBN = :isbn) 
WHEN NOT MATCHED THEN 
INSERT (ISBN, TITLE, YEAR, PRICE) VALUES (:isbn, :title, :year, :price);

-- 저자 정보 등록
MERGE INTO AUTHOR USING DUAL 
ON (NAME = :authorName AND ADDRESS = :authorAddress) 
WHEN NOT MATCHED THEN 
INSERT (NAME, ADDRESS, URL) VALUES (:authorName, :authorAddress, :authorUrl);

-- 저자-도서 관계 등록
MERGE INTO WRITTEN_BY USING DUAL 
ON (NAME = :authorName AND ADDRESS = :authorAddress AND ISBN = :isbn) 
WHEN NOT MATCHED THEN 
INSERT (NAME, ADDRESS, ISBN) VALUES (:authorName, :authorAddress, :isbn);

-- 창고 정보 등록
MERGE INTO WAREHOUSE USING DUAL 
ON (CODE = :code) 
WHEN NOT MATCHED THEN 
INSERT (CODE, ADDRESS, PHONE) VALUES (:code, :wareAddress, :warePhone);

-- 재고 정보 등록
MERGE INTO STOCKS USING DUAL 
ON (ISBN = :isbn AND CODE = :code) 
WHEN NOT MATCHED THEN 
INSERT (ISBN, CODE, NUM) VALUES (:isbn, :code, :num);

-- 출판사-도서 관계 등록
MERGE INTO PUBLISHED_BY USING DUAL 
ON (NAME = :publisherName AND ISBN = :isbn) 
WHEN NOT MATCHED THEN 
INSERT (NAME, ISBN) VALUES (:publisherName, :isbn);

-- 2-(e) 도서 가격 통계
-- 전체 도서 평균 가격
SELECT AVG(NVL(PRICE, 0)) as AVG
FROM BOOK;

-- 연도별 평균 가격
SELECT YEAR, AVG(PRICE) AS avgPrice 
FROM BOOK 
GROUP BY YEAR;

-- 2-(f) 작가별 도서 통계
SELECT NAME, COUNT(ISBN) AS countBook, 
       MAX(PRICE) AS maxPrice, 
       MIN(PRICE) AS minPrice, 
       AVG(PRICE) AS avgPrice
FROM WRITTEN_BY NATURAL JOIN BOOK
GROUP BY NAME;

-- 2-(g) 재고 기반 도서 검색 및 가격 할인
-- 특정 재고량 이상 도서 검색
SELECT b.ISBN as isbn, b.TITLE as title, b.YEAR as year,
       b.PRICE as price, TO_NUMBER(NVL(SUM(s.NUM), 0)) as stock
FROM BOOK b
LEFT JOIN STOCKS s ON b.ISBN = s.ISBN
GROUP BY b.ISBN, b.TITLE, b.YEAR, b.PRICE
HAVING TO_NUMBER(NVL(SUM(s.NUM), 0)) >= :minStock
ORDER BY b.ISBN;

-- 가격 할인 적용
UPDATE BOOK
SET PRICE = PRICE * (1 - :discountRate / 100)
WHERE ISBN IN (
    SELECT b.ISBN
    FROM BOOK b
    LEFT JOIN STOCKS s ON b.ISBN = s.ISBN
    GROUP BY b.ISBN
    HAVING TO_NUMBER(NVL(SUM(s.NUM), 0)) >= :minStock
); 