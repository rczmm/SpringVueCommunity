package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Registrations;

/**
 * 活动报名Service接口
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public interface IRegistrationsService 
{
    /**
     * 查询活动报名
     * 
     * @param registrationID 活动报名主键
     * @return 活动报名
     */
    public Registrations selectRegistrationsByRegistrationID(Long registrationID);

    /**
     * 查询活动报名列表
     * 
     * @param registrations 活动报名
     * @return 活动报名集合
     */
    public List<Registrations> selectRegistrationsList(Registrations registrations);

    /**
     * 新增活动报名
     * 
     * @param registrations 活动报名
     * @return 结果
     */
    public int insertRegistrations(Registrations registrations);

    /**
     * 修改活动报名
     * 
     * @param registrations 活动报名
     * @return 结果
     */
    public int updateRegistrations(Registrations registrations);

    /**
     * 批量删除活动报名
     * 
     * @param registrationIDs 需要删除的活动报名主键集合
     * @return 结果
     */
    public int deleteRegistrationsByRegistrationIDs(Long[] registrationIDs);

    /**
     * 删除活动报名信息
     * 
     * @param registrationID 活动报名主键
     * @return 结果
     */
    public int deleteRegistrationsByRegistrationID(Long registrationID);
}
