package com.stadium.service;

import com.stadium.dao.UserMapper;
import com.stadium.entity.User;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

import static com.stadium.utils.MybatisUtil.factory;


public interface UserService {
    boolean user(String username, String password, HttpSession session);
    boolean register(String username, String password, String telephone, String identity);
    public default void update(User user){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
    }
}
