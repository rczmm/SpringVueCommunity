package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Maintenancerecords;

/**
 * 维修记录Service接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface IMaintenancerecordsService 
{
    /**
     * 查询维修记录
     * 
     * @param recordID 维修记录主键
     * @return 维修记录
     */
    public Maintenancerecords selectMaintenancerecordsByRecordID(Long recordID);

    /**
     * 查询维修记录列表
     * 
     * @param maintenancerecords 维修记录
     * @return 维修记录集合
     */
    public List<Maintenancerecords> selectMaintenancerecordsList(Maintenancerecords maintenancerecords);

    /**
     * 新增维修记录
     * 
     * @param maintenancerecords 维修记录
     * @return 结果
     */
    public int insertMaintenancerecords(Maintenancerecords maintenancerecords);

    /**
     * 修改维修记录
     * 
     * @param maintenancerecords 维修记录
     * @return 结果
     */
    public int updateMaintenancerecords(Maintenancerecords maintenancerecords);

    /**
     * 批量删除维修记录
     * 
     * @param recordIDs 需要删除的维修记录主键集合
     * @return 结果
     */
    public int deleteMaintenancerecordsByRecordIDs(Long[] recordIDs);

    /**
     * 删除维修记录信息
     * 
     * @param recordID 维修记录主键
     * @return 结果
     */
    public int deleteMaintenancerecordsByRecordID(Long recordID);
}
