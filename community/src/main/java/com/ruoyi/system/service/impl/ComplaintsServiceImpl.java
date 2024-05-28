package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ComplaintsMapper;
import com.ruoyi.system.domain.Complaints;
import com.ruoyi.system.service.IComplaintsService;

/**
 * 投诉管理Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class ComplaintsServiceImpl implements IComplaintsService 
{
    @Autowired
    private ComplaintsMapper complaintsMapper;

    /**
     * 查询投诉管理
     * 
     * @param complaintID 投诉管理主键
     * @return 投诉管理
     */
    @Override
    public Complaints selectComplaintsByComplaintID(Long complaintID)
    {
        return complaintsMapper.selectComplaintsByComplaintID(complaintID);
    }

    /**
     * 查询投诉管理列表
     * 
     * @param complaints 投诉管理
     * @return 投诉管理
     */
    @Override
    public List<Complaints> selectComplaintsList(Complaints complaints)
    {
        return complaintsMapper.selectComplaintsList(complaints);
    }

    /**
     * 新增投诉管理
     * 
     * @param complaints 投诉管理
     * @return 结果
     */
    @Override
    public int insertComplaints(Complaints complaints)
    {
        return complaintsMapper.insertComplaints(complaints);
    }

    /**
     * 修改投诉管理
     * 
     * @param complaints 投诉管理
     * @return 结果
     */
    @Override
    public int updateComplaints(Complaints complaints)
    {
        return complaintsMapper.updateComplaints(complaints);
    }

    /**
     * 批量删除投诉管理
     * 
     * @param complaintIDs 需要删除的投诉管理主键
     * @return 结果
     */
    @Override
    public int deleteComplaintsByComplaintIDs(Long[] complaintIDs)
    {
        return complaintsMapper.deleteComplaintsByComplaintIDs(complaintIDs);
    }

    /**
     * 删除投诉管理信息
     * 
     * @param complaintID 投诉管理主键
     * @return 结果
     */
    @Override
    public int deleteComplaintsByComplaintID(Long complaintID)
    {
        return complaintsMapper.deleteComplaintsByComplaintID(complaintID);
    }
}
