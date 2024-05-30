package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Residents;

/**
 * 居民信息Mapper接口
 * 
 * @author rcz
 * @date 2024-05-29
 */
public interface ResidentsMapper 
{
    /**
     * 查询居民信息
     * 
     * @param residentID 居民信息主键
     * @return 居民信息
     */
    public Residents selectResidentsByResidentID(Long residentID);

    /**
     * 查询居民信息列表
     * 
     * @param residents 居民信息
     * @return 居民信息集合
     */
    public List<Residents> selectResidentsList(Residents residents);

    /**
     * 新增居民信息
     * 
     * @param residents 居民信息
     * @return 结果
     */
    public int insertResidents(Residents residents);

    /**
     * 修改居民信息
     * 
     * @param residents 居民信息
     * @return 结果
     */
    public int updateResidents(Residents residents);

    /**
     * 删除居民信息
     * 
     * @param residentID 居民信息主键
     * @return 结果
     */
    public int deleteResidentsByResidentID(Long residentID);

    /**
     * 批量删除居民信息
     * 
     * @param residentIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResidentsByResidentIDs(Long[] residentIDs);
}
