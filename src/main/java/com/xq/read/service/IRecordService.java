package com.xq.read.service;

import com.xq.read.pojo.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 浏览记录 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface IRecordService extends IService<Record> {

    /**
     * 添加浏览记录
     * @param record
     * @return
     */
    boolean insertRecord(Record record);

}
