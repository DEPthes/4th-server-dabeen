package com.example.database.repository.University;

import com.example.database.domain.University.EnrollEntity;
import com.example.database.domain.University.EnrollId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollEntity, EnrollId> {

    @Override
    @Query(
        value = "SELECT * FROM ENROLL ORDER BY SNO, CNO",
        nativeQuery = true
    )
    List<EnrollEntity> findAll();

    // 1-(b) 특정 학생의 수강 과목 이름, 학점, 시험 점수
    @Query(
        value = "SELECT C.CNAME as cname, E.GRADE as grade, E.EXAM as exam " +
                "FROM ENROLL E, COURSE C " +
                "WHERE E.CNO = C.CNO AND E.SNO = :SNO",
        nativeQuery = true
    )
    List<CourseBySnameInterface> findCourseBySno(@Param("SNO") String sno);

    interface CourseBySnameInterface {
        String getCname();
        String getGrade();
        String getExam();
    }

    // 1-(c) 잘못된 학점 확인
    interface FindMisGradeInterface {
        String getSname();
        String getSno();
        String getCno();
        String getExam();
        String getGrade();
    }

    @Query(value = 
        "SELECT S.SNAME as sname, E.SNO as sno, E.CNO as cno, E.EXAM as exam, E.GRADE as grade " +
        "FROM STUDENT S, ENROLL E " +
        "WHERE S.SNO = E.SNO AND " +
        "((CAST(E.EXAM AS INTEGER) >= 90 AND E.GRADE != 'A') OR " +
        "(CAST(E.EXAM AS INTEGER) >= 80 AND CAST(E.EXAM AS INTEGER) < 90 AND E.GRADE != 'B') OR " +
        "(CAST(E.EXAM AS INTEGER) >= 70 AND CAST(E.EXAM AS INTEGER) < 80 AND E.GRADE != 'C') OR " +
        "(CAST(E.EXAM AS INTEGER) >= 60 AND CAST(E.EXAM AS INTEGER) < 70 AND E.GRADE != 'D') OR " +
        "(CAST(E.EXAM AS INTEGER) < 60 AND E.GRADE != 'F'))",
        nativeQuery = true)
    List<FindMisGradeInterface> findMisGrade();

    // 1-(c) 학점 수정
    @Modifying
    @Query(value = 
        "UPDATE ENROLL SET GRADE = " +
        "CASE " +
        "  WHEN CAST(EXAM AS INTEGER) >= 90 THEN 'A' " +
        "  WHEN CAST(EXAM AS INTEGER) >= 80 THEN 'B' " +
        "  WHEN CAST(EXAM AS INTEGER) >= 70 THEN 'C' " +
        "  WHEN CAST(EXAM AS INTEGER) >= 60 THEN 'D' " +
        "  ELSE 'F' " +
        "END " +
        "WHERE SNO = :sno AND CNO = :cno",
        nativeQuery = true)
    void updateMisGrade(@Param("sno") String sno, @Param("cno") String cno);

    // 1-(c) 수정된 학점 조회
    @Query(value = 
        "SELECT S.SNAME as sname, E.SNO as sno, E.CNO as cno, E.EXAM as exam, E.GRADE as grade " +
        "FROM STUDENT S, ENROLL E " +
        "WHERE S.SNO = E.SNO AND E.SNO = :sno AND E.CNO = :cno",
        nativeQuery = true)
    FindMisGradeInterface findUpdatedGrade(@Param("sno") String sno, @Param("cno") String cno);

    // 1-(d) 과목별 최고 점수
    @Query(
        value = "SELECT SNO, PNO, SNAME, YEAR, DEPT, CNO, GRADE, " +
                "MAX(EXAM) OVER(PARTITION BY CNO) AS EXAM " +
                "FROM STUDENT NATURAL JOIN ENROLL",
        nativeQuery = true
    )
    List<FindTopScorerInterface> findTopScorer();

    interface FindTopScorerInterface {
        String getSname();
        String getSno();
        String getPno();
        String getYear();
        String getCno();
        String getExam();
    }

    // 1-(d) 과목별 최저 점수
    @Query(
        value = "SELECT SNO, PNO, SNAME, YEAR, DEPT, CNO, GRADE, " +
                "MIN(EXAM) OVER(PARTITION BY CNO) AS EXAM " +
                "FROM STUDENT NATURAL JOIN ENROLL",
        nativeQuery = true
    )
    List<FindLowestScorerInterface> findLowestScorer();

    interface FindLowestScorerInterface {
        String getSname();
        String getSno();
        String getPno();
        String getYear();
        String getCno();
        String getExam();
    }

    // 1-(e) 학생별 총 학점 및 평균 점수
    @Query(
        value = "SELECT SNO, SUM(CREDIT) AS sumCredit, AVG(EXAM) AS avgExam " +
                "FROM ENROLL NATURAL JOIN COURSE " +
                "GROUP BY SNO ORDER BY SNO",
        nativeQuery = true
    )
    List<SumCreditAvgExamBySnoInterface> sumCreditAvgExamBySno();

    interface SumCreditAvgExamBySnoInterface {
        String getSno();
        String getSumCredit();
        String getAvgExam();
    }

    // 1-(f) 과목별 평균 점수, 학점별 학생 수
    @Query(
        value = "SELECT CNO, CNAME, avgExam, E.GRADE AS grade, COUNT(E.SNO) AS countSno " +
                "FROM ENROLL E NATURAL JOIN COURSE NATURAL JOIN ( " +
                "SELECT CNO, AVG(EXAM) AS avgExam FROM ENROLL GROUP BY CNO ) " +
                "GROUP BY CNO, CNAME, avgExam, E.GRADE ORDER BY CNO",
        nativeQuery = true
    )
    List<AvgExamCountInterface> avgExamStudent();

    interface AvgExamCountInterface {
        String getCno();
        String getCname();
        String getAvgExam();
        String getGrade();
        String getCountSno();
    }

    // 1-(g) 학과별 개설 강좌 수
    @Query(value = """
            SELECT p.PDEPT as dname,
                   COUNT(DISTINCT l.CNO) as lecture_count
            FROM PROFESSOR p
            LEFT JOIN LECTURE l ON p.PNO = l.PNO
            GROUP BY p.PDEPT
            ORDER BY p.PDEPT
            """, nativeQuery = true)
    List<DeptLectureCountInterface> findDeptLectureCount();

    interface DeptLectureCountInterface {
        String getDname();
        int getLectureCount();
    }

    // 1-(h) 학과/학년별 학생 수
    @Query(
        value = "SELECT S.DEPT AS dept, S.YEAR AS year, COUNT(*) AS countSno " +
                "FROM STUDENT S " +
                "GROUP BY S.DEPT, S.YEAR ORDER BY S.DEPT, S.YEAR",
        nativeQuery = true
    )
    List<CountSnoByDeptYearInterface> countSnoByDeptYear();

    interface CountSnoByDeptYearInterface {
        String getDept();
        String getYear();
        String getCountSno();
    }

    // (추가용) ENROLL 테이블에 튜플 삽입
    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO ENROLL(SNO, CNO, GRADE, EXAM) " +
                "VALUES (:sno, :cno, :grade, :exam)",
        nativeQuery = true
    )
    void saveOne(
        @Param("sno") String sno,
        @Param("cno") String cno,
        @Param("grade") String grade,
        @Param("exam") String exam
    );

    // d) 과목별 최고/최저 점수 학생 정보
    @Query(value = """
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
            GROUP BY c.cno, c.cname, s_max.sno, s_max.sname, s_min.sno, s_min.sname
            """, nativeQuery = true)
    List<CourseMinMaxScoreInterface> findCourseMinMaxScore();

    // e) 학생별 총학점과 평균 점수
    @Query(value = """
            SELECT s.sno, s.sname,
                   SUM(c.credit) as total_credits,
                   AVG(e.exam) as avg_score
            FROM Student s
            JOIN Enroll e ON s.sno = e.sno
            JOIN Course c ON e.cno = c.cno
            GROUP BY s.sno, s.sname
            """, nativeQuery = true)
    List<StudentCreditsAndScoreInterface> findStudentCreditsAndScore();

    // f) 과목별 평균 점수와 학점별 학생 수 (개선된 버전)
    @Query(value = """
            SELECT 
                c.cno, c.cname, 
                ROUND(AVG(CAST(e.exam AS DECIMAL)), 2) as avg_exam,
                e.grade, COUNT(e.sno) as count_students
            FROM Course c
            JOIN Enroll e ON c.cno = e.cno
            GROUP BY c.cno, c.cname, e.grade
            ORDER BY c.cno, e.grade
            """, nativeQuery = true)
    List<CourseGradeDetailStatsInterface> findCourseGradeDetailStats();

    interface CourseGradeDetailStatsInterface {
        String getCno();
        String getCname();
        double getAvgExam();
        String getGrade();
        int getCountStudents();
    }

    // h) 학과별, 학년별 학생 수
    @Query(value = """
            SELECT STD.DEPT as dname,
                   STD.YEAR as year,
                   COUNT(*) as student_count
            FROM STUDENT STD
            GROUP BY STD.DEPT, STD.YEAR
            ORDER BY STD.DEPT, STD.YEAR
            """, nativeQuery = true)
    List<DeptYearStudentCountInterface> findDeptYearStudentCount();

    interface CourseMinMaxScoreInterface {
        String getCno();
        String getCname();
        int getMaxScore();
        int getMinScore();
        String getMaxSno();
        String getMaxSname();
        String getMinSno();
        String getMinSname();
    }

    interface StudentCreditsAndScoreInterface {
        String getSno();
        String getSname();
        int getTotalCredits();
        double getAvgScore();
    }

    interface CourseGradeStatsInterface {
        String getCno();
        String getCname();
        double getAvgScore();
        int getCountA();
        int getCountB();
        int getCountC();
        int getCountD();
        int getCountF();
    }

    interface DeptYearStudentCountInterface {
        String getDname();
        int getYear();
        int getStudentCount();
    }
}