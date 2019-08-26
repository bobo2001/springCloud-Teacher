package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface TreeService {

    void saveaBook(@Param("bookBean") BookBean bookBean);




    List<TreeBean> findMyTree();

    HashMap<String, Object> findMyUse(Integer page, Integer rows, BookBean bookBean);

    void delBook(Integer[] ids);

    BookBean findBookById(Integer id);

    void saveBookmon(BookBean bookBean);
}
