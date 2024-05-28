package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Handlingrecords;

/**
 * 处理记录Service接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface IHandlingrecordsService 
{
    /**
     * 查询处理记录
     * 
     * @param recordID 处理记录主键
     * @return 处理记录
     */
    public Handlingrecords selectHandlingrecordsByRecordID(Long recordID);

    /**
     * 查询处理记录列表
     * 
     * @param handlingrecords 处理记录
     * @return 处理记录集合
     */
    public List<Handlingrecords> selectHandlingrecordsList(Handlingrecords handlingrecords);

    /**
     * 新增处理记录
     * 
     * @param handlingrecords 处理记录
     * @return 结果
     */
    public int insertHandlingrecords(Handlingrecords handlingrecords);

    /**
     * 修改处理记录
     * 
     * @param handlingrecords 处理记录
     * @return 结果
     */
    public int updateHandlingrecords(Handlingrecords handlingrecords);

    /**
     * 批量删除处理记录
     * 
     * @param recordIDs 需要删除的处理记录主键集合
     * @return 结果
     */
    public int deleteHandlingrecordsByRecordIDs(Long[] recordIDs);

    /**
     * 删除处理记录信息
     * 
     * @param recordID 处理记录主键
     * @return 结果
     */
    public int deleteHandlingrecordsByRecordID(Long recordID);
}
