package com.xq.read.controller;

import com.xq.read.common.ServerResponse;
import com.xq.read.service.IPreviewService;
import com.xq.read.vo.PreviewVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author xq
 * @Date 2021/9/9 下午9:26
 * @ClassName PreviewController
 * @Description 文章预览
 */

@RestController
@RequestMapping("/read/preview")
public class PreviewController {

    @Autowired
    IPreviewService iPreviewService;

    private static final Logger logger = LoggerFactory.getLogger(PreviewController.class);


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ServerResponse getIndex(@RequestParam(required = false,defaultValue = "1")Integer page){
        try{
            return iPreviewService.getIndex(page);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage() );
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return ServerResponse.createByErrorMessage("加载失败");
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ServerResponse getPageByTags(List<String> tags, @RequestParam(required = false,defaultValue = "1")Integer page){

        return ServerResponse.createByErrorMessage("加载失败");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ServerResponse getPageByUser(Integer userId, @RequestParam(required = false,defaultValue = "1")Integer page){
        System.out.println("userId = " + userId);
        try{
            ServerResponse<List<PreviewVo>> index = iPreviewService.getArticlesByUserId(userId, page);
            logger.info(index.getMsg());
            return index;
        }catch (Exception e){
            logger.error(Arrays.toString(e.getStackTrace()));
            logger.error(e.getMessage() );
        }
        return ServerResponse.createByErrorMessage("error");
    }

    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    public ServerResponse getPageByKeyword(String  keyword, @RequestParam(required = false,defaultValue = "1")Integer page){
        try{
            ServerResponse<List<PreviewVo>> index = iPreviewService.getArticlesByKeyWord(keyword, page);
            logger.info(index.getMsg());
            return index;
        }catch (Exception e){
            logger.error(Arrays.toString(e.getStackTrace()));
            logger.error(e.getMessage() );
        }
        return ServerResponse.createByErrorMessage("加载失败");
    }

}

