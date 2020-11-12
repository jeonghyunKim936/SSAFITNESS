package com.ssafy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.UserMapper;
import com.ssafy.vo.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	UserMapper mapper;
	
	@Override
	public User search(String email) {
		return mapper.search(email);
	}

	@Override
	public void signUp(User user) {
		mapper.signUp(user);
		return; 
	}

	@Override
	public User searchByNickname(String nickname) {
		return mapper.searchByNickname(nickname);
	}

	@Override
	public User searchByID(String user_id) {
		return mapper.searchByID(user_id);
	}

	@Override
	public void updateUser(User user) {
		mapper.updateUser(user);
		return;
		
	}

	@Override
	public void deleteUser(String user_id) {
		mapper.deleteUser(user_id);
		return;
	}
	
}
