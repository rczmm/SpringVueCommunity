package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MaintenancerecordsMapper;
import com.ruoyi.system.domain.Maintenancerecords;
import com.ruoyi.system.service.IMaintenancerecordsService;

/**
 * 维修记录Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class MaintenancerecordsServiceImpl implements IMaintenancerecordsService 
{
    @Autowired
    private MaintenancerecordsMapper maintenancerecordsMapper;

    /**
     * 查询维修记录
     * 
     * @param recordID 维修记录主键
     * @return 维修记录
     */
    @Override
    public Maintenancerecords selectMaintenancerecordsByRecordID(Long recordID)
    {
        return maintenancerecordsMapper.selectMaintenancerecordsByRecordID(recordID);
    }

    /**
     * 查询维修记录列表
     * 
     * @param maintenancerecords 维修记录
     * @return 维修记录
     */
    @Override
    public List<Maintenancerecords> selectMaintenancerecordsList(Maintenancerecords maintenancerecords)
    {
        return maintenancerecordsMapper.selectMaintenancerecordsList(maintenancerecords);
    }

    /**
     * 新增维修记录
     * 
     * @param maintenancerecords 维修记录
     * @return 结果
     */
    @Override
    public int insertMaintenancerecords(Maintenancerecords maintenancerecords)
    {
        return maintenancerecordsMapper.insertMaintenancerecords(maintenancerecords);
    }

    /**
     * 修改维修记录
     * 
     * @param maintenancerecords 维修记录
     * @return 结果
     */
    @Override
    public int updateMaintenancerecords(Maintenancerecords maintenancerecords)
    {
        return maintenancerecordsMapper.updateMaintenancerecords(maintenancerecords);
    }

    /**
     * 批量删除维修记录
     * 
     * @param recordIDs 需要删除的维修记录主键
     * @return 结果
     */
    @Override
    public int deleteMaintenancerecordsByRecordIDs(Long[] recordIDs)
    {
        return maintenancerecordsMapper.deleteMaintenancerecordsByRecordIDs(recordIDs);
    }

    /**
     * 删除维修记录信息
     * 
     * @param recordID 维修记录主键
     * @return 结果
     */
    @Override
    public int deleteMaintenancerecordsByRecordID(Long recordID)
    {
        return maintenancerecordsMapper.deleteMaintenancerecordsByRecordID(recordID);
    }
}
