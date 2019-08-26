package com.jk.dao;

import com.jk.model.UserBean;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from t_user where username=#{username} and password=#{password}")
    UserBean findUserById(UserBean userBean);
}
