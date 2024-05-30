package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ResidentsMapper;
import com.ruoyi.system.domain.Residents;
import com.ruoyi.system.service.IResidentsService;

/**
 * 居民信息Service业务层处理
 * 
 * @author rcz
 * @date 2024-05-29
 */
@Service
public class ResidentsServiceImpl implements IResidentsService 
{
    @Autowired
    private ResidentsMapper residentsMapper;

    /**
     * 查询居民信息
     * 
     * @param residentID 居民信息主键
     * @return 居民信息
     */
    @Override
    public Residents selectResidentsByResidentID(Long residentID)
    {
        return residentsMapper.selectResidentsByResidentID(residentID);
    }

    /**
     * 查询居民信息列表
     * 
     * @param residents 居民信息
     * @return 居民信息
     */
    @Override
    public List<Residents> selectResidentsList(Residents residents)
    {
        return residentsMapper.selectResidentsList(residents);
    }

    /**
     * 新增居民信息
     * 
     * @param residents 居民信息
     * @return 结果
     */
    @Override
    public int insertResidents(Residents residents)
    {
        return residentsMapper.insertResidents(residents);
    }

    /**
     * 修改居民信息
     * 
     * @param residents 居民信息
     * @return 结果
     */
    @Override
    public int updateResidents(Residents residents)
    {
        return residentsMapper.updateResidents(residents);
    }

    /**
     * 批量删除居民信息
     * 
     * @param residentIDs 需要删除的居民信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentsByResidentIDs(Long[] residentIDs)
    {
        return residentsMapper.deleteResidentsByResidentIDs(residentIDs);
    }

    /**
     * 删除居民信息信息
     * 
     * @param residentID 居民信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentsByResidentID(Long residentID)
    {
        return residentsMapper.deleteResidentsByResidentID(residentID);
    }
}
