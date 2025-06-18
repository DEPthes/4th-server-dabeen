package com.example.database.service.University;

import com.example.database.domain.University.EnrollEntity;
import com.example.database.repository.University.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 수강 정보 관리를 위한 서비스 클래스
 * 학생의 수강 신청, 성적 관리, 통계 등을 처리
 */
@Service
public class EnrollService {
    @Autowired
    private EnrollRepository enrollRepository;

    /**
     * 1-(a) 모든 수강 정보 조회
     */
    public List<EnrollEntity> findAll() {
        return enrollRepository.findAll();
    }

    public long count() {
        return enrollRepository.count();
    }

    /**
     * 1-(b) 특정 학생의 수강 과목 정보 조회
     * @param sno 학번
     * @return 수강 과목 이름, 학점, 시험 점수 목록
     */
    public List<EnrollRepository.CourseBySnameInterface> findCourseBySno(String sno) {
        return enrollRepository.findCourseBySno(sno);
    }

    /**
     * 1-(c) 잘못된 학점 조회
     * @return 잘못된 학점이 부여된 학생 목록
     */
    public List<EnrollRepository.FindMisGradeInterface> findMisGrade() {
        return enrollRepository.findMisGrade();
    }

    /**
     * 1-(c) 특정 학생의 특정 과목 학점 조회
     */
    public EnrollRepository.FindMisGradeInterface findUpdatedGrade(String sno, String cno) {
        return enrollRepository.findUpdatedGrade(sno, cno);
    }

    /**
     * 1-(c) 잘못된 학점 수정
     * 시험 점수에 따라 올바른 학점으로 자동 수정
     */
    @Transactional
    public void updateMisGrade(String sno, String cno) {
        enrollRepository.updateMisGrade(sno, cno);
    }

    /**
     * 1-(d) 과목별 최고/최저 점수 학생 정보 조회
     */
    public List<EnrollRepository.CourseMinMaxScoreInterface> findCourseMinMaxScore() {
        return enrollRepository.findCourseMinMaxScore();
    }

    /**
     * 1-(e) 학생별 총학점과 평균 점수 조회
     */
    public List<EnrollRepository.StudentCreditsAndScoreInterface> findStudentCreditsAndScore() {
        return enrollRepository.findStudentCreditsAndScore();
    }

    /**
     * 1-(f) 과목별 평균 점수와 학점별 학생 수 조회
     */
    public List<EnrollRepository.CourseGradeDetailStatsInterface> findCourseGradeDetailStats() {
        return enrollRepository.findCourseGradeDetailStats();
    }

    /**
     * 1-(g) 학과별 개설 강좌 수 조회
     */
    public List<EnrollRepository.DeptLectureCountInterface> findDeptLectureCount() {
        return enrollRepository.findDeptLectureCount();
    }

    /**
     * 1-(h) 학과별, 학년별 학생 수 조회
     */
    public List<EnrollRepository.DeptYearStudentCountInterface> findDeptYearStudentCount() {
        return enrollRepository.findDeptYearStudentCount();
    }
} 