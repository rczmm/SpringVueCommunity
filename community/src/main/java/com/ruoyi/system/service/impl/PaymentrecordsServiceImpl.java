package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PaymentrecordsMapper;
import com.ruoyi.system.domain.Paymentrecords;
import com.ruoyi.system.service.IPaymentrecordsService;

/**
 * 缴费记录Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-15
 */
@Service
public class PaymentrecordsServiceImpl implements IPaymentrecordsService 
{
    @Autowired
    private PaymentrecordsMapper paymentrecordsMapper;

    /**
     * 查询缴费记录
     * 
     * @param recordID 缴费记录主键
     * @return 缴费记录
     */
    @Override
    public Paymentrecords selectPaymentrecordsByRecordID(Long recordID)
    {
        return paymentrecordsMapper.selectPaymentrecordsByRecordID(recordID);
    }

    /**
     * 查询缴费记录列表
     * 
     * @param paymentrecords 缴费记录
     * @return 缴费记录
     */
    @Override
    public List<Paymentrecords> selectPaymentrecordsList(Paymentrecords paymentrecords)
    {
        return paymentrecordsMapper.selectPaymentrecordsList(paymentrecords);
    }

    /**
     * 新增缴费记录
     * 
     * @param paymentrecords 缴费记录
     * @return 结果
     */
    @Override
    public int insertPaymentrecords(Paymentrecords paymentrecords)
    {
        return paymentrecordsMapper.insertPaymentrecords(paymentrecords);
    }

    /**
     * 修改缴费记录
     * 
     * @param paymentrecords 缴费记录
     * @return 结果
     */
    @Override
    public int updatePaymentrecords(Paymentrecords paymentrecords)
    {
        return paymentrecordsMapper.updatePaymentrecords(paymentrecords);
    }

    /**
     * 批量删除缴费记录
     * 
     * @param recordIDs 需要删除的缴费记录主键
     * @return 结果
     */
    @Override
    public int deletePaymentrecordsByRecordIDs(Long[] recordIDs)
    {
        return paymentrecordsMapper.deletePaymentrecordsByRecordIDs(recordIDs);
    }

    /**
     * 删除缴费记录信息
     * 
     * @param recordID 缴费记录主键
     * @return 结果
     */
    @Override
    public int deletePaymentrecordsByRecordID(Long recordID)
    {
        return paymentrecordsMapper.deletePaymentrecordsByRecordID(recordID);
    }
}
