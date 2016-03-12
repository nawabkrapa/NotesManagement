package com.notes.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.notes.dao.AbstractDao;
import com.notes.dao.UserDao;
import com.notes.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User findByEmailId(String emailId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("emailId",emailId));
		 List result= criteria.list();
	        
	        if(result != null && !result.isEmpty()){
	        	return (User) result.get(0);
	        }
	        return null;
	}

}
