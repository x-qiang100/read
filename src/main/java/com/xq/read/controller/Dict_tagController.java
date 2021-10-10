package com.xq.read.controller;


import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Dict_tag;
import com.xq.read.pojo.Tag;
import com.xq.read.service.ICollectionService;
import com.xq.read.service.IDict_tagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 标签字典 前端控制器
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/read/tag")
public class Dict_tagController {

    @Autowired
    IDict_tagService iDict_tagService;

    private static Logger logger = LoggerFactory.getLogger(Dict_tagController.class);


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addDict_Tag(String tagName, String intro){
        Dict_tag tag = new Dict_tag();
        tag.setTagName(tagName);
        tag.setIntro(intro);
        try{
            Boolean b = iDict_tagService.insertTag(tag);
            if(b) return ServerResponse.createBySuccessMessage("添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    /**
     * 获得所有标签信息
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ServerResponse getDict_Tag(){
        try{

            ServerResponse<List<Dict_tag>> tags = iDict_tagService.getTags();
            logger.info("获取tags");
            return tags;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }




}

