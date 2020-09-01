package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.UserVO;

@Repository
@Mapper
public interface UserMapper {	//userMapper.xml���� �̸�,��ɾ� ����	
	
	//insert
	public void insert(UserVO user);

	//according to id delete
	public void delete(String user_id);

	//according to user Of id modify
	public void update(UserVO user);

	//user_id �����Ҷ� �ߺ��Ǵ� user_id�� �ִ��� �˻��ϴ��ڵ�(������ �������� ���ε忡 ���)
	public String userid_no_search(String user_id);
	
	//according to id query
	public UserVO getById(String user_id);

	//All queries
	public List<UserVO> list();

	
}
