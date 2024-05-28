package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EnvironmentalrecordsMapper;
import com.ruoyi.system.domain.Environmentalrecords;
import com.ruoyi.system.service.IEnvironmentalrecordsService;

/**
 * 环境卫生Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class EnvironmentalrecordsServiceImpl implements IEnvironmentalrecordsService 
{
    @Autowired
    private EnvironmentalrecordsMapper environmentalrecordsMapper;

    /**
     * 查询环境卫生
     * 
     * @param recordID 环境卫生主键
     * @return 环境卫生
     */
    @Override
    public Environmentalrecords selectEnvironmentalrecordsByRecordID(Long recordID)
    {
        return environmentalrecordsMapper.selectEnvironmentalrecordsByRecordID(recordID);
    }

    /**
     * 查询环境卫生列表
     * 
     * @param environmentalrecords 环境卫生
     * @return 环境卫生
     */
    @Override
    public List<Environmentalrecords> selectEnvironmentalrecordsList(Environmentalrecords environmentalrecords)
    {
        return environmentalrecordsMapper.selectEnvironmentalrecordsList(environmentalrecords);
    }

    /**
     * 新增环境卫生
     * 
     * @param environmentalrecords 环境卫生
     * @return 结果
     */
    @Override
    public int insertEnvironmentalrecords(Environmentalrecords environmentalrecords)
    {
        return environmentalrecordsMapper.insertEnvironmentalrecords(environmentalrecords);
    }

    /**
     * 修改环境卫生
     * 
     * @param environmentalrecords 环境卫生
     * @return 结果
     */
    @Override
    public int updateEnvironmentalrecords(Environmentalrecords environmentalrecords)
    {
        return environmentalrecordsMapper.updateEnvironmentalrecords(environmentalrecords);
    }

    /**
     * 批量删除环境卫生
     * 
     * @param recordIDs 需要删除的环境卫生主键
     * @return 结果
     */
    @Override
    public int deleteEnvironmentalrecordsByRecordIDs(Long[] recordIDs)
    {
        return environmentalrecordsMapper.deleteEnvironmentalrecordsByRecordIDs(recordIDs);
    }

    /**
     * 删除环境卫生信息
     * 
     * @param recordID 环境卫生主键
     * @return 结果
     */
    @Override
    public int deleteEnvironmentalrecordsByRecordID(Long recordID)
    {
        return environmentalrecordsMapper.deleteEnvironmentalrecordsByRecordID(recordID);
    }
}
