package com.stadium.dao;

import com.stadium.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    @Insert("insert into user(username,password,telephone,identity) value(#{username}, #{password}, #{telephone}, #{identity})")
    void register(@Param("username") String username,
                  @Param("password") String password,
                  @Param("telephone") String telephone,
                  @Param("identity") String identity);

    @Update("update user set username = #{username}, password = #{password}, telephone = #{telephone}")
    void update(User user);

}
