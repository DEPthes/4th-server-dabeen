package com.example.database.controller;

import com.example.database.domain.University.*;
import com.example.database.repository.University.EnrollRepository;
import com.example.database.service.University.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 대학 정보 시스템의 웹 요청을 처리하는 컨트롤러
 * 학생, 교수, 과목, 수강 정보 등을 관리
 */
@Controller
@RequestMapping("/university")
@Slf4j
public class UniversityController {

    @Autowired ProfessorService professorService;
    @Autowired StudentService studentService;
    @Autowired CourseService courseService;
    @Autowired LectureService lectureService;
    @Autowired EnrollService enrollService;

    /**
     * 1-(a) 각 테이블의 데이터 조회 및 개수 출력 메서드들
     */
    @GetMapping("/student")
    public ModelAndView student() {
        ModelAndView mv = new ModelAndView("count_student");
        mv.addObject("students", studentService.findAll());
        mv.addObject("student_cnt", studentService.count());
        return mv;
    }

    @GetMapping("/professor")
    public ModelAndView professor() {
        ModelAndView mv = new ModelAndView("count_professor");
        mv.addObject("professors", professorService.findAll());
        mv.addObject("professor_cnt", professorService.count());
        return mv;
    }

    @GetMapping("/course")
    public ModelAndView course() {
        ModelAndView mv = new ModelAndView("count_course");
        mv.addObject("courses", courseService.findAll());
        mv.addObject("course_cnt", courseService.count());
        return mv;
    }

    @GetMapping("/lecture")
    public ModelAndView lecture() {
        ModelAndView mv = new ModelAndView("count_lecture");
        mv.addObject("lectures", lectureService.findAll());
        mv.addObject("lecture_cnt", lectureService.count());
        return mv;
    }

    @GetMapping("/enroll")
    public ModelAndView enroll() {
        ModelAndView mv = new ModelAndView("count_enroll");
        mv.addObject("enrolls", enrollService.findAll());
        mv.addObject("enroll_cnt", enrollService.count());
        return mv;
    }

    /**
     * 1-(b) 특정 학생의 수강 과목 정보 조회
     * @param sno 학번
     * @return 해당 학생의 수강 과목 이름, 학점, 시험 점수
     */
    @GetMapping("/enroll/{sno}")
    public ModelAndView findCourseBySno(@PathVariable("sno") String sno) {
        ModelAndView mv = new ModelAndView("find_course_by_sno_result");
        List<EnrollRepository.CourseBySnameInterface> result = enrollService.findCourseBySno(sno);
        mv.addObject("find_course_by_sno", result);
        return mv;
    }

    /**
     * 1-(c) 잘못된 학점 확인 및 수정
     * 60점 미만: F, 60~69: D, 70~79: C, 80~89: B, 90 이상: A
     */
    @GetMapping("/check_wrong_grades")
    public ModelAndView checkWrongGrades() {
        ModelAndView mv = new ModelAndView("check_wrong_grades");
        List<EnrollRepository.FindMisGradeInterface> wrongGrades = enrollService.findMisGrade();
        mv.addObject("wrong_grades", wrongGrades);
        return mv;
    }

    @PostMapping("/update_grade_result")
    public ModelAndView updateGradeResult(@RequestParam("sno") String sno, @RequestParam("cno") String cno) {
        ModelAndView mv = new ModelAndView("update_grade_result");
        
        try {
            // 수정 전 데이터 저장
            EnrollRepository.FindMisGradeInterface beforeUpdate = enrollService.findUpdatedGrade(sno, cno);
            if (beforeUpdate != null) {
                // 학점 수정
                enrollService.updateMisGrade(sno, cno);
                
                // 수정 후 데이터 저장
                EnrollRepository.FindMisGradeInterface afterUpdate = enrollService.findUpdatedGrade(sno, cno);
                mv.addObject("after_update", afterUpdate);
                
                log.info("학점 수정 완료 - 학번: {}, 과목번호: {}", sno, cno);
                log.info("수정된 데이터: {}", afterUpdate);
            } else {
                mv.addObject("error", "해당하는 학생의 수강 정보를 찾을 수 없습니다.");
                log.warn("학생 수강 정보 없음 - 학번: {}, 과목번호: {}", sno, cno);
            }
        } catch (Exception e) {
            mv.addObject("error", "학점 수정 중 오류가 발생했습니다: " + e.getMessage());
            log.error("학점 수정 중 오류 발생", e);
        }
        
        return mv;
    }

    /**
     * 1-(d) 과목별 최고/최저 점수 학생 정보 조회
     */
    @GetMapping("/course/score-stats")
    public ModelAndView getCourseScoreStats() {
        ModelAndView mv = new ModelAndView("course_score_stats");
        mv.addObject("courseStats", enrollService.findCourseMinMaxScore());
        return mv;
    }

    /**
     * 1-(e) 학생별 총학점과 평균 점수 조회
     */
    @GetMapping("/student/credit-stats")
    public ModelAndView getStudentCreditStats() {
        ModelAndView mv = new ModelAndView("student_credit_stats");
        mv.addObject("studentStats", enrollService.findStudentCreditsAndScore());
        return mv;
    }

    /**
     * 1-(f) 과목별 평균 점수와 학점별 학생 수 조회
     */
    @GetMapping("/course/grade-stats")
    public ModelAndView getCourseGradeStats() {
        ModelAndView mv = new ModelAndView("course_grade_stats");
        mv.addObject("gradeStats", enrollService.findCourseGradeDetailStats());
        return mv;
    }

    /**
     * 1-(g) 학과별 개설 강좌 수 조회
     */
    @GetMapping("/department/lecture-stats")
    public ModelAndView getDeptLectureStats() {
        ModelAndView mv = new ModelAndView("dept_lecture_stats");
        mv.addObject("lectureStats", enrollService.findDeptLectureCount());
        return mv;
    }

    /**
     * 1-(h) 학과별, 학년별 학생 수 조회
     */
    @GetMapping("/department/student-stats")
    public ModelAndView getDeptStudentStats() {
        ModelAndView mv = new ModelAndView("dept_student_stats");
        mv.addObject("studentStats", enrollService.findDeptYearStudentCount());
        return mv;
    }
}