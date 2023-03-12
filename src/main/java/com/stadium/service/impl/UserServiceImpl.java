package com.stadium.service.impl;

import com.stadium.dao.UserMapper;
import com.stadium.entity.User;
import com.stadium.service.UserService;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    @Override
    public boolean user(String username, String password, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password);
            if (user == null) return false;
            session.setAttribute("user", user);
            return true;
        }
    }

    @Override
    public boolean register(String username, String password, String telephone, String identity) {
        try (SqlSession sqlSession = MybatisUtil.getSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.register(username, password, telephone, identity);
        }
        return false;
    }


}
