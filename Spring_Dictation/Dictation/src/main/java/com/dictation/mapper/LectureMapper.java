package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.LectureVO;

@Repository
@Mapper
public interface LectureMapper {	//lectureMapper.xml���� �̸�,��ɾ� ����	
	
	//insert
	public void insert(LectureVO lecture);

	//according to id delete
	public void delete(int lecture_no);	

	//according to user Of id modify
	public void update(LectureVO lecture);

	//���¹�ȣ �����Ҷ� �ߺ��Ǵ� ���°� �ִ��� �˻��ϴ��ڵ�
	public Object lecture_no_search(int lecture_no);
	
	//������ ������ ������ ���¸�� ���� ���� �ڵ�
	public List<LectureVO> teacher_mylec(String user_id);
	
	//�л�ȭ�� ��ü���� ����Ʈ�� ���½�û����
	public List<LectureVO> student_lec_list(String user_id);
	
	//�л� ������ ������û�ؼ� ���ε� ���¸�� ���� ���� �ڵ�
	public List<LectureVO> student_mylec(String user_id);
	
	//according to id query
	public LectureVO getById(int lecture_no);

	//All queries
	public List<LectureVO> list();

	
}
