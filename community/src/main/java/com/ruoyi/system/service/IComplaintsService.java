package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Complaints;

/**
 * 投诉管理Service接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface IComplaintsService 
{
    /**
     * 查询投诉管理
     * 
     * @param complaintID 投诉管理主键
     * @return 投诉管理
     */
    public Complaints selectComplaintsByComplaintID(Long complaintID);

    /**
     * 查询投诉管理列表
     * 
     * @param complaints 投诉管理
     * @return 投诉管理集合
     */
    public List<Complaints> selectComplaintsList(Complaints complaints);

    /**
     * 新增投诉管理
     * 
     * @param complaints 投诉管理
     * @return 结果
     */
    public int insertComplaints(Complaints complaints);

    /**
     * 修改投诉管理
     * 
     * @param complaints 投诉管理
     * @return 结果
     */
    public int updateComplaints(Complaints complaints);

    /**
     * 批量删除投诉管理
     * 
     * @param complaintIDs 需要删除的投诉管理主键集合
     * @return 结果
     */
    public int deleteComplaintsByComplaintIDs(Long[] complaintIDs);

    /**
     * 删除投诉管理信息
     * 
     * @param complaintID 投诉管理主键
     * @return 结果
     */
    public int deleteComplaintsByComplaintID(Long complaintID);
}
