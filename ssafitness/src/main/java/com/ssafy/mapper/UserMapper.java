package com.ssafy.mapper;


import com.ssafy.vo.User;


public interface UserMapper {
	public User search(String email);
	public void signUp(User user);
	public User searchByNickname(String nickname);
	public User searchByID(String user_id);
	public void updateUser(User user);
	public void deleteUser(String user_id);
}
