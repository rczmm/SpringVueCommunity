package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MaintenancerequestsMapper;
import com.ruoyi.system.domain.Maintenancerequests;
import com.ruoyi.system.service.IMaintenancerequestsService;

/**
 * 报修管理Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class MaintenancerequestsServiceImpl implements IMaintenancerequestsService 
{
    @Autowired
    private MaintenancerequestsMapper maintenancerequestsMapper;

    /**
     * 查询报修管理
     * 
     * @param requestID 报修管理主键
     * @return 报修管理
     */
    @Override
    public Maintenancerequests selectMaintenancerequestsByRequestID(Long requestID)
    {
        return maintenancerequestsMapper.selectMaintenancerequestsByRequestID(requestID);
    }

    /**
     * 查询报修管理列表
     * 
     * @param maintenancerequests 报修管理
     * @return 报修管理
     */
    @Override
    public List<Maintenancerequests> selectMaintenancerequestsList(Maintenancerequests maintenancerequests)
    {
        return maintenancerequestsMapper.selectMaintenancerequestsList(maintenancerequests);
    }

    /**
     * 新增报修管理
     * 
     * @param maintenancerequests 报修管理
     * @return 结果
     */
    @Override
    public int insertMaintenancerequests(Maintenancerequests maintenancerequests)
    {
        return maintenancerequestsMapper.insertMaintenancerequests(maintenancerequests);
    }

    /**
     * 修改报修管理
     * 
     * @param maintenancerequests 报修管理
     * @return 结果
     */
    @Override
    public int updateMaintenancerequests(Maintenancerequests maintenancerequests)
    {
        return maintenancerequestsMapper.updateMaintenancerequests(maintenancerequests);
    }

    /**
     * 批量删除报修管理
     * 
     * @param requestIDs 需要删除的报修管理主键
     * @return 结果
     */
    @Override
    public int deleteMaintenancerequestsByRequestIDs(Long[] requestIDs)
    {
        return maintenancerequestsMapper.deleteMaintenancerequestsByRequestIDs(requestIDs);
    }

    /**
     * 删除报修管理信息
     * 
     * @param requestID 报修管理主键
     * @return 结果
     */
    @Override
    public int deleteMaintenancerequestsByRequestID(Long requestID)
    {
        return maintenancerequestsMapper.deleteMaintenancerequestsByRequestID(requestID);
    }
}
