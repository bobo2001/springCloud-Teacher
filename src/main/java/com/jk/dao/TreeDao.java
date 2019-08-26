package com.jk.dao;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface TreeDao {
    @Select("select * from t_tree where pid = #{value}")
    List<TreeBean> findMyTreeListByPid(Integer id);

    @Select(" select count(*)  from  t_project ")
    int findUserCount(BookBean bookBean);


    List<BookBean> findUserPage(HashMap<String, Object> hashMap2);

    void saveOrUpdate(BookBean bookBean);

    void updateRole(BookBean bookBean);

    void delBook(Integer[] ids);

    @Select("select * from t_project where id = #{value}")
    BookBean findBookById(Integer id);
}
