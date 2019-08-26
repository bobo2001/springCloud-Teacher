package com.jk.controller;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class TreeController {
    @Autowired
    private TreeService treeService;
    @Autowired
    private  RedisTemplate redisTemplate;

    @RequestMapping("quanbu")
    public String quanbu(String url){
        return url;
    }

    @RequestMapping("saveMongo")
    @ResponseBody
    public  Boolean  saveMongo(BookBean bookBean){
        try {
            treeService.saveBookmon(bookBean);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("tree")
    @ResponseBody
    public List<TreeBean> findMyTree(){
        List<Object> range = redisTemplate.opsForList().range("tree", 0, -1);
        if (range ==null || range.size() <=0) {
            List<TreeBean> findTreeList = treeService.findMyTree();
            redisTemplate.opsForList().leftPush("booktree", findTreeList);
            return findTreeList;
        } else {
            List<TreeBean> lists = (List<TreeBean>) range.get(0);
            return lists;
        }
    }

    @RequestMapping("query")
    @ResponseBody
    public HashMap<String, Object> findMyUse(Integer page, Integer rows, BookBean bookBean){
        return treeService.findMyUse(page,rows,bookBean);
    }

    @RequestMapping("saveaBook")
    @ResponseBody
    public  Boolean saveaBook(BookBean bookBean){
        try {
            treeService.saveaBook(bookBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("delBook")
    @ResponseBody
    public Boolean delUser(Integer[] ids){
        try {
            treeService.delBook(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("findBookById")
    @ResponseBody
    public  BookBean findBookById(Integer id){
        return treeService.findBookById(id);
    }



}
