package com.notes.dao;

import com.notes.model.User;

public interface UserDao {
	User findByEmailId(String emailId);
}
