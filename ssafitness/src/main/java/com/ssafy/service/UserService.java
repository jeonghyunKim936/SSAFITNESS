package com.ssafy.service;

import com.ssafy.vo.User;

public interface UserService {
	public User search(String email);
	public User searchByNickname(String nickname);
	public void signUp(User user);
	public User searchByID(String user_id);
	public void updateUser(User user);
	public void deleteUser(String user_id);
}
