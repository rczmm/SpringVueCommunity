package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Paymentrecords;

/**
 * 缴费记录Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-15
 */
public interface PaymentrecordsMapper 
{
    /**
     * 查询缴费记录
     * 
     * @param recordID 缴费记录主键
     * @return 缴费记录
     */
    public Paymentrecords selectPaymentrecordsByRecordID(Long recordID);

    /**
     * 查询缴费记录列表
     * 
     * @param paymentrecords 缴费记录
     * @return 缴费记录集合
     */
    public List<Paymentrecords> selectPaymentrecordsList(Paymentrecords paymentrecords);

    /**
     * 新增缴费记录
     * 
     * @param paymentrecords 缴费记录
     * @return 结果
     */
    public int insertPaymentrecords(Paymentrecords paymentrecords);

    /**
     * 修改缴费记录
     * 
     * @param paymentrecords 缴费记录
     * @return 结果
     */
    public int updatePaymentrecords(Paymentrecords paymentrecords);

    /**
     * 删除缴费记录
     * 
     * @param recordID 缴费记录主键
     * @return 结果
     */
    public int deletePaymentrecordsByRecordID(Long recordID);

    /**
     * 批量删除缴费记录
     * 
     * @param recordIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePaymentrecordsByRecordIDs(Long[] recordIDs);
}
