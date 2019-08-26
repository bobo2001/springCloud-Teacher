package com.jk.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeBean implements Serializable {

    private Integer id;

    private String text;

    private String url;

    private Integer pid;

    private List<TreeBean> children;
}
