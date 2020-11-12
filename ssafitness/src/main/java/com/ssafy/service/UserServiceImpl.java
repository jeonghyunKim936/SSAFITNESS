package com.ssafy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.UserDAO;
import com.ssafy.vo.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO dao;
	
	@Override
	public User search(String email) {
		return dao.search(email);
	}

	@Override
	public void signUp(User user) {
		dao.signUp(user);
		return;
	}

	@Override
	public User searchByNickname(String nickname) {
		return dao.searchByNickname(nickname);
	}

	@Override
	public User searchByID(String user_id) {
		return dao.searchByID(user_id);
	}

	@Override
	public void updateUser(User user) {
		dao.updateUser(user);
		return;
		
	}

	@Override
	public void deleteUser(String user_id) {
		dao.deleteUser(user_id);
		return;
	}
	
}
