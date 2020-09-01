package com.dictation.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.CourseVO;

@Repository
@Mapper
public interface CourseMapper {	//courseMapper.xml���� �̸�,��ɾ� ����	
	
	//insert
	public void insert(CourseVO course);

	//according to id delete
	public void delete(CourseVO course);	//�ϴ��� lecture_no�� ���� ���������ϵ��� ����.
	
	//�������� ���»���������
	public void lecture_delete(int lecture_no);

	//according to user Of id modify
	public void update(CourseVO course);
	
	//�޾ƾ��� ���� ����
	public void dic_modify_question(CourseVO course);
	//�޾ƾ��� ���� ����
	public void dic_modify_file(CourseVO course);
	
	//������ �޾ƾ��� �Ϸ��ư
	public void finish_yes(CourseVO course);

	//according to id query
	public CourseVO getById(CourseVO course);	//�ϴ��� lecture_no����

	//���¿� ���� �޾ƾ��� �Ϸ�ó���� �ܰ�鸸 ��ȯ 
	public List<Integer> finish_yes_cl(int lecture_no);
	
	//All queries
	public List<CourseVO> list();
	
	//search file_nm for file download
	public String getFileNm(String save_file_nm);
	
	//������ȭ�� ����� �޾ƾ��� �ִ�ܰ�
	public int max_dic_course(int lecture_no);
	
	//������- �޾ƾ��� ���� ������
	public List<CourseVO> dic_answers(CourseVO course);
	
}
