package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FamiliesMapper;
import com.ruoyi.system.domain.Families;
import com.ruoyi.system.service.IFamiliesService;

/**
 * 家庭信息Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@Service
public class FamiliesServiceImpl implements IFamiliesService 
{
    @Autowired
    private FamiliesMapper familiesMapper;

    /**
     * 查询家庭信息
     * 
     * @param familyID 家庭信息主键
     * @return 家庭信息
     */
    @Override
    public Families selectFamiliesByFamilyID(Long familyID)
    {
        return familiesMapper.selectFamiliesByFamilyID(familyID);
    }

    /**
     * 查询家庭信息列表
     * 
     * @param families 家庭信息
     * @return 家庭信息
     */
    @Override
    public List<Families> selectFamiliesList(Families families)
    {
        return familiesMapper.selectFamiliesList(families);
    }

    /**
     * 新增家庭信息
     * 
     * @param families 家庭信息
     * @return 结果
     */
    @Override
    public int insertFamilies(Families families)
    {
        return familiesMapper.insertFamilies(families);
    }

    /**
     * 修改家庭信息
     * 
     * @param families 家庭信息
     * @return 结果
     */
    @Override
    public int updateFamilies(Families families)
    {
        return familiesMapper.updateFamilies(families);
    }

    /**
     * 批量删除家庭信息
     * 
     * @param familyIDs 需要删除的家庭信息主键
     * @return 结果
     */
    @Override
    public int deleteFamiliesByFamilyIDs(Long[] familyIDs)
    {
        return familiesMapper.deleteFamiliesByFamilyIDs(familyIDs);
    }

    /**
     * 删除家庭信息信息
     * 
     * @param familyID 家庭信息主键
     * @return 结果
     */
    @Override
    public int deleteFamiliesByFamilyID(Long familyID)
    {
        return familiesMapper.deleteFamiliesByFamilyID(familyID);
    }
}
