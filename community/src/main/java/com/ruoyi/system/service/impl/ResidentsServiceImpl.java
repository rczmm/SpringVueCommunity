package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ResidentsMapper;
import com.ruoyi.system.domain.Residents;
import com.ruoyi.system.service.IResidentsService;

/**
 * 人口信息Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@Service
public class ResidentsServiceImpl implements IResidentsService 
{
    @Autowired
    private ResidentsMapper residentsMapper;

    /**
     * 查询人口信息
     * 
     * @param residentID 人口信息主键
     * @return 人口信息
     */
    @Override
    public Residents selectResidentsByResidentID(Long residentID)
    {
        return residentsMapper.selectResidentsByResidentID(residentID);
    }

    /**
     * 查询人口信息列表
     * 
     * @param residents 人口信息
     * @return 人口信息
     */
    @Override
    public List<Residents> selectResidentsList(Residents residents)
    {
        return residentsMapper.selectResidentsList(residents);
    }

    /**
     * 新增人口信息
     * 
     * @param residents 人口信息
     * @return 结果
     */
    @Override
    public int insertResidents(Residents residents)
    {
        return residentsMapper.insertResidents(residents);
    }

    /**
     * 修改人口信息
     * 
     * @param residents 人口信息
     * @return 结果
     */
    @Override
    public int updateResidents(Residents residents)
    {
        return residentsMapper.updateResidents(residents);
    }

    /**
     * 批量删除人口信息
     * 
     * @param residentIDs 需要删除的人口信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentsByResidentIDs(Long[] residentIDs)
    {
        return residentsMapper.deleteResidentsByResidentIDs(residentIDs);
    }

    /**
     * 删除人口信息信息
     * 
     * @param residentID 人口信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentsByResidentID(Long residentID)
    {
        return residentsMapper.deleteResidentsByResidentID(residentID);
    }
}
