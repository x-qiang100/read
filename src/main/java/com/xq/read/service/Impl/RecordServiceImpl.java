package com.xq.read.service.Impl;

import com.xq.read.pojo.Record;
import com.xq.read.mapper.RecordMapper;
import com.xq.read.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 浏览记录 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public boolean insertRecord(Record record) {
        int insert = recordMapper.insert(record);
        return insert > 0;
    }
}
