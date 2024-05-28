package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Residents;

/**
 * 人口信息Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public interface ResidentsMapper 
{
    /**
     * 查询人口信息
     * 
     * @param residentID 人口信息主键
     * @return 人口信息
     */
    public Residents selectResidentsByResidentID(Long residentID);

    /**
     * 查询人口信息列表
     * 
     * @param residents 人口信息
     * @return 人口信息集合
     */
    public List<Residents> selectResidentsList(Residents residents);

    /**
     * 新增人口信息
     * 
     * @param residents 人口信息
     * @return 结果
     */
    public int insertResidents(Residents residents);

    /**
     * 修改人口信息
     * 
     * @param residents 人口信息
     * @return 结果
     */
    public int updateResidents(Residents residents);

    /**
     * 删除人口信息
     * 
     * @param residentID 人口信息主键
     * @return 结果
     */
    public int deleteResidentsByResidentID(Long residentID);

    /**
     * 批量删除人口信息
     * 
     * @param residentIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResidentsByResidentIDs(Long[] residentIDs);
}
