package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Families;

/**
 * 家庭信息Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public interface FamiliesMapper 
{
    /**
     * 查询家庭信息
     * 
     * @param familyID 家庭信息主键
     * @return 家庭信息
     */
    public Families selectFamiliesByFamilyID(Long familyID);

    /**
     * 查询家庭信息列表
     * 
     * @param families 家庭信息
     * @return 家庭信息集合
     */
    public List<Families> selectFamiliesList(Families families);

    /**
     * 新增家庭信息
     * 
     * @param families 家庭信息
     * @return 结果
     */
    public int insertFamilies(Families families);

    /**
     * 修改家庭信息
     * 
     * @param families 家庭信息
     * @return 结果
     */
    public int updateFamilies(Families families);

    /**
     * 删除家庭信息
     * 
     * @param familyID 家庭信息主键
     * @return 结果
     */
    public int deleteFamiliesByFamilyID(Long familyID);

    /**
     * 批量删除家庭信息
     * 
     * @param familyIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFamiliesByFamilyIDs(Long[] familyIDs);
}
