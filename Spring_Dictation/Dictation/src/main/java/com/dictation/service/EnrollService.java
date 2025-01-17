package com.dictation.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dictation.mapper.EnrollMapper;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Service
public class EnrollService {
	
	@Autowired
	private EnrollMapper enrollMapper;
	
	
	//##insert,delete,update,getById,list##
	//insert
	public void insert(EnrollVO enroll) {
		enrollMapper.insert(enroll);
	}

	//according to id delete
	public void delete(EnrollVO enroll) {
		enrollMapper.delete(enroll);
	}

	//선생님이 강좌삭제했을때
	public void lecture_delete(int lecture_no) {
		enrollMapper.lecture_delete(lecture_no);
	}
		
	//according to user Of id modify
	public void update(EnrollVO enroll) {		
		enrollMapper.update(enroll);
	}
	
	//선생님이 학생을 승인시켜줌
	public void update_request(int lecture_no, String user_id) {
		EnrollVO enroll = new EnrollVO();
		enroll.setLecture_no(lecture_no);
		enroll.setUser_id(user_id);
		
		enrollMapper.update_request(enroll);
	}

	//according to id query
	public EnrollVO getById(String user_id) {
		return enrollMapper.getById(user_id);
	}
	
	//해당 강좌에 대해 학생이 통과한 단계번호 알기위함
	public int what_pass_course(EnrollVO enroll) {
		return enrollMapper.what_pass_course(enroll);
	}

	//All queries
	public List<EnrollVO> list(){
		return enrollMapper.list();
	}
	
	//신청현황 리스트
	public List<UserVO> list_request(int lecture_no){
		return enrollMapper.list_request(lecture_no);
	}
	

}
