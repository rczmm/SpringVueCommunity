package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Reservations;

/**
 * 活动预约Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public interface ReservationsMapper 
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
     * 删除活动预约
     * 
     * @param reservationID 活动预约主键
     * @return 结果
     */
    public int deleteReservationsByReservationID(Long reservationID);

    /**
     * 批量删除活动预约
     * 
     * @param reservationIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReservationsByReservationIDs(Long[] reservationIDs);
}
