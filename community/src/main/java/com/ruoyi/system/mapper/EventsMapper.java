package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Events;

/**
 * 活动Mapper接口
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public interface EventsMapper 
{
    /**
     * 查询活动
     * 
     * @param eventID 活动主键
     * @return 活动
     */
    public Events selectEventsByEventID(Long eventID);

    /**
     * 查询活动列表
     * 
     * @param events 活动
     * @return 活动集合
     */
    public List<Events> selectEventsList(Events events);

    /**
     * 新增活动
     * 
     * @param events 活动
     * @return 结果
     */
    public int insertEvents(Events events);

    /**
     * 修改活动
     * 
     * @param events 活动
     * @return 结果
     */
    public int updateEvents(Events events);

    /**
     * 删除活动
     * 
     * @param eventID 活动主键
     * @return 结果
     */
    public int deleteEventsByEventID(Long eventID);

    /**
     * 批量删除活动
     * 
     * @param eventIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEventsByEventIDs(Long[] eventIDs);
}
