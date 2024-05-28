package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Complaints;

/**
 * 投诉管理Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface ComplaintsMapper 
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
     * 删除投诉管理
     * 
     * @param complaintID 投诉管理主键
     * @return 结果
     */
    public int deleteComplaintsByComplaintID(Long complaintID);

    /**
     * 批量删除投诉管理
     * 
     * @param complaintIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteComplaintsByComplaintIDs(Long[] complaintIDs);
}
