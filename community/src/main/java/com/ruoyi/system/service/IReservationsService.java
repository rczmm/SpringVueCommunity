package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Reservations;

/**
 * 活动预约Service接口
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public interface IReservationsService 
{
    /**
     * 查询活动预约
     * 
     * @param reservationID 活动预约主键
     * @return 活动预约
     */
    public Reservations selectReservationsByReservationID(Long reservationID);

    /**
     * 查询活动预约列表
     * 
     * @param reservations 活动预约
     * @return 活动预约集合
     */
    public List<Reservations> selectReservationsList(Reservations reservations);

    /**
     * 新增活动预约
     * 
     * @param reservations 活动预约
     * @return 结果
     */
    public int insertReservations(Reservations reservations);

    /**
     * 修改活动预约
     * 
     * @param reservations 活动预约
     * @return 结果
     */
    public int updateReservations(Reservations reservations);

    /**
     * 批量删除活动预约
     * 
     * @param reservationIDs 需要删除的活动预约主键集合
     * @return 结果
     */
    public int deleteReservationsByReservationIDs(Long[] reservationIDs);

    /**
     * 删除活动预约信息
     * 
     * @param reservationID 活动预约主键
     * @return 结果
     */
    public int deleteReservationsByReservationID(Long reservationID);
}
