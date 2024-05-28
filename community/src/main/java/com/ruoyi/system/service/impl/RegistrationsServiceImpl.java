package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RegistrationsMapper;
import com.ruoyi.system.domain.Registrations;
import com.ruoyi.system.service.IRegistrationsService;

/**
 * 活动报名Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@Service
public class RegistrationsServiceImpl implements IRegistrationsService 
{
    @Autowired
    private RegistrationsMapper registrationsMapper;

    /**
     * 查询活动报名
     * 
     * @param registrationID 活动报名主键
     * @return 活动报名
     */
    @Override
    public Registrations selectRegistrationsByRegistrationID(Long registrationID)
    {
        return registrationsMapper.selectRegistrationsByRegistrationID(registrationID);
    }

    /**
     * 查询活动报名列表
     * 
     * @param registrations 活动报名
     * @return 活动报名
     */
    @Override
    public List<Registrations> selectRegistrationsList(Registrations registrations)
    {
        return registrationsMapper.selectRegistrationsList(registrations);
    }

    /**
     * 新增活动报名
     * 
     * @param registrations 活动报名
     * @return 结果
     */
    @Override
    public int insertRegistrations(Registrations registrations)
    {
        return registrationsMapper.insertRegistrations(registrations);
    }

    /**
     * 修改活动报名
     * 
     * @param registrations 活动报名
     * @return 结果
     */
    @Override
    public int updateRegistrations(Registrations registrations)
    {
        return registrationsMapper.updateRegistrations(registrations);
    }

    /**
     * 批量删除活动报名
     * 
     * @param registrationIDs 需要删除的活动报名主键
     * @return 结果
     */
    @Override
    public int deleteRegistrationsByRegistrationIDs(Long[] registrationIDs)
    {
        return registrationsMapper.deleteRegistrationsByRegistrationIDs(registrationIDs);
    }

    /**
     * 删除活动报名信息
     * 
     * @param registrationID 活动报名主键
     * @return 结果
     */
    @Override
    public int deleteRegistrationsByRegistrationID(Long registrationID)
    {
        return registrationsMapper.deleteRegistrationsByRegistrationID(registrationID);
    }
}
