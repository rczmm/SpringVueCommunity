package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HandlingrecordsMapper;
import com.ruoyi.system.domain.Handlingrecords;
import com.ruoyi.system.service.IHandlingrecordsService;

/**
 * 处理记录Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class HandlingrecordsServiceImpl implements IHandlingrecordsService 
{
    @Autowired
    private HandlingrecordsMapper handlingrecordsMapper;

    /**
     * 查询处理记录
     * 
     * @param recordID 处理记录主键
     * @return 处理记录
     */
    @Override
    public Handlingrecords selectHandlingrecordsByRecordID(Long recordID)
    {
        return handlingrecordsMapper.selectHandlingrecordsByRecordID(recordID);
    }

    /**
     * 查询处理记录列表
     * 
     * @param handlingrecords 处理记录
     * @return 处理记录
     */
    @Override
    public List<Handlingrecords> selectHandlingrecordsList(Handlingrecords handlingrecords)
    {
        return handlingrecordsMapper.selectHandlingrecordsList(handlingrecords);
    }

    /**
     * 新增处理记录
     * 
     * @param handlingrecords 处理记录
     * @return 结果
     */
    @Override
    public int insertHandlingrecords(Handlingrecords handlingrecords)
    {
        return handlingrecordsMapper.insertHandlingrecords(handlingrecords);
    }

    /**
     * 修改处理记录
     * 
     * @param handlingrecords 处理记录
     * @return 结果
     */
    @Override
    public int updateHandlingrecords(Handlingrecords handlingrecords)
    {
        return handlingrecordsMapper.updateHandlingrecords(handlingrecords);
    }

    /**
     * 批量删除处理记录
     * 
     * @param recordIDs 需要删除的处理记录主键
     * @return 结果
     */
    @Override
    public int deleteHandlingrecordsByRecordIDs(Long[] recordIDs)
    {
        return handlingrecordsMapper.deleteHandlingrecordsByRecordIDs(recordIDs);
    }

    /**
     * 删除处理记录信息
     * 
     * @param recordID 处理记录主键
     * @return 结果
     */
    @Override
    public int deleteHandlingrecordsByRecordID(Long recordID)
    {
        return handlingrecordsMapper.deleteHandlingrecordsByRecordID(recordID);
    }
}
