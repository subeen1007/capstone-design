package com.dictation.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dictation.service.UserService;
import com.dictation.vo.UserVO;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
    //insert user
	@PostMapping(produces = "application/json;charset=UTF-8")
	public void insert(@RequestBody UserVO user) {
		
		//position_cd
		user.setDae_p("003");
		if(user.getPosition_cd().equals("������")) {  
			user.setSo_p("001");
		}else if(user.getPosition_cd().equals("������")) {//����Ʈ���� �������̸� "������"���� ������ ���� �ѱ�
			user.setSo_p("002");
		}else if(user.getPosition_cd().equals("�л�")) {//����Ʈ���� �л��̸� "�л�"���� ������ ���� �ѱ�
			user.setSo_p("003");
		}
		
		//gender_cd
		user.setDae_g("002");
		System.out.println(user.getPosition_cd());
		if(user.getGender_cd().equals("G01")) {//����Ʈ���� �����̸� "G01"���� ������ ���� �ѱ�  
			user.setSo_g("001");
		}else if(user.getGender_cd().equals("G02")) {//����Ʈ���� �����̸� "G02"���� ������ ���� �ѱ�
			user.setSo_g("002");
		}
		
		userService.insert(user);
		
	}


      //according to id delete
	@GetMapping(value="/delete/{user_id}")
	public void delete(@PathVariable("user_id") String user_id) {
		userService.delete(user_id);
	}
	//modify
	//user_id�� ���ƾ� ��
	@PostMapping(value="/update")
	public void update(@RequestBody UserVO user) {
		userService.update(user);
	}

	//according to id Query students
		@GetMapping(value="/get/{user_id}")
		public UserVO getById(@PathVariable("user_id") String user_id) {
			UserVO user = userService.getById(user_id);
			return user;
		}
	
	//All queries
	@GetMapping(value="/list")
	public List<UserVO> list(){
		return userService.list();
	}	
	
	//�α���(������ UserVO�� ���ǰ����� �����ϰ�, position_cd�� ��ȯ)
	//���߿��� post�� user_id �� �ٰ�
	@GetMapping(value = "/login/{user_id}&{pw}")
	public UserVO login(@PathVariable("user_id") String user_id,@PathVariable("pw") String pw, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		UserVO user = getById(user_id);
		
		if(user.equals(null) || user == null) {
		    user.setLoginYn("0");
		    return user;
		}else if(user.getPw().equals(pw)) {//�α��μ��� &���ǰ� ��
			//if() �������ڵ�, �������ڵ� �α��ν� ����Ȯ�ΰ� ���� �ʿ�
			
			session.setAttribute("user", user);//���ǿ� UserVO����
			
			UserVO user_session=(UserVO)session.getAttribute("user");
			System.out.println("���̵� ���ǰ� :" +user_session.getUser_id());
			System.out.println("��й�ȣ ���ǰ� :" +user_session.getPw());
			System.out.println("�ź��ڵ� ���ǰ� :" +user_session.getPosition_cd());
			user.setLoginYn("1");
			return user;
		}else {
			System.out.println("���ϰ�Ȯ�οϷ�");
			session.setAttribute("login_fail", pw);
			user.setLoginYn("0");
			return user;
		}
	}

	
	//���ǰ� ���� �޼ҵ�(���ǰ����� user_id�� �����ϴ� �޼ҵ�)
	@GetMapping(value = "/user_id/{user_id}")
	public String user_session(@PathVariable("user_id") String user_id, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("user_id", user_id);
	
	    System.out.println("���ǰ� :" +session.getAttribute("user_id"));

	    return "login/user_id";
	}
	
	//���ǰ� Ȯ���� ����� �޼ҵ�(test��)
	@GetMapping(value = "/session")
	public String session(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		
	    System.out.println("user_id ���ǰ� :" +session.getAttribute("user_id"));
	    System.out.println("lecture_no ���ǰ� :" +session.getAttribute("lecture_no"));

	    // ���ǿ��� �����.
	    session.invalidate();
	    System.out.println("������ user_id ���ǰ� :" +session.getAttribute("user_id"));
	    System.out.println("������ lecture_no ���ǰ� :" +session.getAttribute("lecture_no"));
	    return "login/user_id&lecture_no";
	}

	/*
	���ǰ� ����  session.setAttribute("�̸�", "��");
	��������  session.getAttribute("�̸�");
	�Ѱ�����  session.removeAttribute("�̸�");
	�ʱ�ȭ    session.invalidate();
	*/
	
	

	
	
}