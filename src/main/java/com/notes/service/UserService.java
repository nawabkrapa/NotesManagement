package com.notes.service;

import com.notes.model.User;

public interface UserService {
	User findByEmailId(String emailId);
}
