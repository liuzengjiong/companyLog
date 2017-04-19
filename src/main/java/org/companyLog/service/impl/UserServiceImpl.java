package org.companyLog.service.impl;


import java.util.List;
import java.util.Map;

import org.companyLog.bean.User;
import org.companyLog.dao.UserMapper;
import org.companyLog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户
     */
    @Override
    public User getUser(String id) {
        User user = userMapper.queryUser(id);
        return user;
    }

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.queryUserByUsername(username);
	}

	@Override
	public List<User> getUserByPermissionCode(String permissionCode) {
		// TODO Auto-generated method stub
		return userMapper.selectByPermissionCode(permissionCode);
	}

	@Override
	public List<User> getAllUserWithRole(Integer index,Integer rows,Map<String,String> likeCondition) {
		// TODO Auto-generated method stub
		System.out.println(index+":"+rows);
		return userMapper.queryAllUserWithRole(index,rows,likeCondition);
	}

	@Override
	public User getUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return userMapper.selectByPhone(phone);
	}

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(user);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updatePasswordById(User user) {
		// TODO Auto-generated method stub
		return userMapper.updatePasswordById(user);
	}

	@Override
	public int getAllUserCount(Map<String, String> likeCondition) {
		// TODO Auto-generated method stub
		return userMapper.queryAllUserCount(likeCondition);
	}
}
