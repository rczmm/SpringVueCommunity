package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ReservationsMapper;
import com.ruoyi.system.domain.Reservations;
import com.ruoyi.system.service.IReservationsService;

/**
 * 活动预约Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@Service
public class ReservationsServiceImpl implements IReservationsService 
{
    @Autowired
    private ReservationsMapper reservationsMapper;

    /**
     * 查询活动预约
     * 
     * @param reservationID 活动预约主键
     * @return 活动预约
     */
    @Override
    public Reservations selectReservationsByReservationID(Long reservationID)
    {
        return reservationsMapper.selectReservationsByReservationID(reservationID);
    }

    /**
     * 查询活动预约列表
     * 
     * @param reservations 活动预约
     * @return 活动预约
     */
    @Override
    public List<Reservations> selectReservationsList(Reservations reservations)
    {
        return reservationsMapper.selectReservationsList(reservations);
    }

    /**
     * 新增活动预约
     * 
     * @param reservations 活动预约
     * @return 结果
     */
    @Override
    public int insertReservations(Reservations reservations)
    {
        return reservationsMapper.insertReservations(reservations);
    }

    /**
     * 修改活动预约
     * 
     * @param reservations 活动预约
     * @return 结果
     */
    @Override
    public int updateReservations(Reservations reservations)
    {
        return reservationsMapper.updateReservations(reservations);
    }

    /**
     * 批量删除活动预约
     * 
     * @param reservationIDs 需要删除的活动预约主键
     * @return 结果
     */
    @Override
    public int deleteReservationsByReservationIDs(Long[] reservationIDs)
    {
        return reservationsMapper.deleteReservationsByReservationIDs(reservationIDs);
    }

    /**
     * 删除活动预约信息
     * 
     * @param reservationID 活动预约主键
     * @return 结果
     */
    @Override
    public int deleteReservationsByReservationID(Long reservationID)
    {
        return reservationsMapper.deleteReservationsByReservationID(reservationID);
    }
}
