package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Repository
@Mapper
public interface EnrollMapper {	//enrollMapper.xml���� �̸�,��ɾ� ����	
	
	//insert
	public void insert(EnrollVO enroll);

	//according to id delete
	public void delete(EnrollVO enroll);	//�ϴ��� user_id ����.
	
	//�������� ���»���������
	public void lecture_delete(int lecture_no);

	//according to user Of id modify
	public void update(EnrollVO enroll);
	
	//�������� �л��� ���ν�����
	public void update_request(EnrollVO enroll);

	//according to id query
	public EnrollVO getById(String user_id);	//�ϴ��� user_id ����.
	
	//�ش� ���¿� ���� �л��� ����� �ܰ��ȣ �˱�����
	public int what_pass_course(EnrollVO enroll);

	//All queries
	public List<EnrollVO> list();
	
	//��û��Ȳ ����Ʈ
	public List<UserVO> list_request(int lecture_no);

	
}
