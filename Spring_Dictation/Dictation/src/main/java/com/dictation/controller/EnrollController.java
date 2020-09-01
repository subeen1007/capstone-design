package com.dictation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dictation.service.CourseService;
import com.dictation.service.EnrollService;
import com.dictation.vo.CourseVO;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/enroll")
public class EnrollController {
	
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private CourseService courseService;
	
	
    //insert user
	@PostMapping(produces = "application/json;charset=UTF-8")
	public void insert(@RequestBody EnrollVO enroll, HttpServletRequest request) {
		
		//lecture_no
		HttpSession session = request.getSession();
		enroll.setLecture_no((int)session.getAttribute("lecture_no"));
				
		enrollService.insert(enroll);
	}


      //according to id delete
	@GetMapping(value="/delete/{user_id}")
	public void delete(@PathVariable("user_id") String user_id) {
		enrollService.delete(user_id);
	}
	//modify
	//user_id�� ���ƾ� ��
	@PostMapping(value="/update")
	public void update(@RequestBody EnrollVO enroll) { //user_id, lecture_no�� �ʼ�
		enrollService.update(enroll);
	}

	//according to id Query students
	@GetMapping(value="/get/{user_id}")
	public EnrollVO getById(@PathVariable("user_id") String user_id) {
		EnrollVO enroll = enrollService.getById(user_id);
		return enroll;
	}
	
	//All queries
	@PostMapping(value="/list")
	public List<EnrollVO> list(){
		return enrollService.list();
	}	
	
	//�����(�л����� �Ű������� ����)
	@PostMapping(value="/answer")
	public boolean[] answer(@RequestBody CourseVO[] courseList, HttpServletRequest request) throws Exception {
		//quesion, lecture_no, course_no, quesion_no�����;� ��
		//���� lecture_no, user_id�� ���ǰ����� �����;���(�ϴ� user_id�� ���Ƿ� ����)

		//���ǰ����� �л����̵� ������
		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
		String student_id = user_session.getUser_id();
		
		
		String question;
		CourseVO course;
		boolean[] answer=new boolean[courseList.length];

		for(int i=0; i<courseList.length; i++) {
			question=courseList[i].getQuestion();
			course=courseService.getById(courseList[i]);
			if(question.equals(course.getQuestion())) {
				answer[i]=true;
			}else {
				answer[i]=false;
			}
		}
		
		System.out.println("�л����̵� : "+student_id);
		
		EnrollVO enroll=new EnrollVO();
		enroll.setLecture_no(courseList[1].getLecture_no());
		enroll.setUser_id(student_id);
		
		//����(���Ŀ� ����ǥ�Ⱑ �ƴ� �ܰ�ǥ���Ҷ� ������ ����)
		int score=0;
		for(int i=0; i<answer.length; i++) {
			if(answer[i]==true) {
				score+=10;
			}
		}
		enroll.setPass_course_no(score);//�ϴ��� ������ ǥ��(���Ŀ� �ܰ�� ǥ���� ����)
		
		//enroll.setUser_id("vv");//�ӽ� ���̵�(user_id�� ���� enroll�� �־����)
		enrollService.update(enroll);
		return answer;
		
	}
}