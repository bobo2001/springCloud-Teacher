package com.jk.service;

import com.jk.dao.TreeDao;
import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    TreeDao treeDao;
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public HashMap<String, Object> findMyUse(Integer page, Integer rows, BookBean bookBean) {
        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("bookBean",bookBean);
        //查询总条数
        int total = treeDao.findUserCount(bookBean);
        //分页查询
        int start = (page-1)*rows;//开始条数
        hashMap2.put("start",start);
        hashMap2.put("rows",rows);
        List<BookBean> list = treeDao.findUserPage(hashMap2);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }

    @Override
    public void delBook(Integer[] ids) {
        treeDao.delBook(ids);
    }

    @Override
    public BookBean findBookById(Integer id) {
        return treeDao.findBookById(id);
    }

    @Override
    public void saveBookmon(BookBean bookBean) {
        bookBean.setId(1);
        mongoTemplate.insert(bookBean);

    }


    @Override
    public void saveaBook(BookBean bookBean) {
        if (bookBean.getId() == null) {
            treeDao.saveOrUpdate(bookBean);
        }else{
            treeDao.updateRole(bookBean);
        }
    }

    @Override
    public List<TreeBean> findMyTree() {
        Integer pid = 0;
        List<TreeBean> node = getNode(pid);
        return node;
    }


    private List<TreeBean> getNode(Integer pid) {
        List<TreeBean> findMyTreeListByPid = treeDao.findMyTreeListByPid(pid);
        for (TreeBean treeBean:findMyTreeListByPid){
            Integer id1 = treeBean.getId();
            List<TreeBean> node = getNode(id1);
            treeBean.setChildren(node);
        }
        return findMyTreeListByPid;
    }



}
