package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Maintenancerequests;

/**
 * 报修管理Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface MaintenancerequestsMapper 
{
    /**
     * 查询报修管理
     * 
     * @param requestID 报修管理主键
     * @return 报修管理
     */
    public Maintenancerequests selectMaintenancerequestsByRequestID(Long requestID);

    /**
     * 查询报修管理列表
     * 
     * @param maintenancerequests 报修管理
     * @return 报修管理集合
     */
    public List<Maintenancerequests> selectMaintenancerequestsList(Maintenancerequests maintenancerequests);

    /**
     * 新增报修管理
     * 
     * @param maintenancerequests 报修管理
     * @return 结果
     */
    public int insertMaintenancerequests(Maintenancerequests maintenancerequests);

    /**
     * 修改报修管理
     * 
     * @param maintenancerequests 报修管理
     * @return 结果
     */
    public int updateMaintenancerequests(Maintenancerequests maintenancerequests);

    /**
     * 删除报修管理
     * 
     * @param requestID 报修管理主键
     * @return 结果
     */
    public int deleteMaintenancerequestsByRequestID(Long requestID);

    /**
     * 批量删除报修管理
     * 
     * @param requestIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaintenancerequestsByRequestIDs(Long[] requestIDs);
}
