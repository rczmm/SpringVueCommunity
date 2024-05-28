package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Environmentalrecords;

/**
 * 环境卫生Service接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface IEnvironmentalrecordsService 
{
    /**
     * 查询环境卫生
     * 
     * @param recordID 环境卫生主键
     * @return 环境卫生
     */
    public Environmentalrecords selectEnvironmentalrecordsByRecordID(Long recordID);

    /**
     * 查询环境卫生列表
     * 
     * @param environmentalrecords 环境卫生
     * @return 环境卫生集合
     */
    public List<Environmentalrecords> selectEnvironmentalrecordsList(Environmentalrecords environmentalrecords);

    /**
     * 新增环境卫生
     * 
     * @param environmentalrecords 环境卫生
     * @return 结果
     */
    public int insertEnvironmentalrecords(Environmentalrecords environmentalrecords);

    /**
     * 修改环境卫生
     * 
     * @param environmentalrecords 环境卫生
     * @return 结果
     */
    public int updateEnvironmentalrecords(Environmentalrecords environmentalrecords);

    /**
     * 批量删除环境卫生
     * 
     * @param recordIDs 需要删除的环境卫生主键集合
     * @return 结果
     */
    public int deleteEnvironmentalrecordsByRecordIDs(Long[] recordIDs);

    /**
     * 删除环境卫生信息
     * 
     * @param recordID 环境卫生主键
     * @return 结果
     */
    public int deleteEnvironmentalrecordsByRecordID(Long recordID);
}
