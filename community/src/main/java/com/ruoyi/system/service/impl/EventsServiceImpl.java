package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EventsMapper;
import com.ruoyi.system.domain.Events;
import com.ruoyi.system.service.IEventsService;

/**
 * 活动Service业务层处理
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@Service
public class EventsServiceImpl implements IEventsService 
{
    @Autowired
    private EventsMapper eventsMapper;

    /**
     * 查询活动
     * 
     * @param eventID 活动主键
     * @return 活动
     */
    @Override
    public Events selectEventsByEventID(Long eventID)
    {
        return eventsMapper.selectEventsByEventID(eventID);
    }

    /**
     * 查询活动列表
     * 
     * @param events 活动
     * @return 活动
     */
    @Override
    public List<Events> selectEventsList(Events events)
    {
        return eventsMapper.selectEventsList(events);
    }

    /**
     * 新增活动
     * 
     * @param events 活动
     * @return 结果
     */
    @Override
    public int insertEvents(Events events)
    {
        return eventsMapper.insertEvents(events);
    }

    /**
     * 修改活动
     * 
     * @param events 活动
     * @return 结果
     */
    @Override
    public int updateEvents(Events events)
    {
        return eventsMapper.updateEvents(events);
    }

    /**
     * 批量删除活动
     * 
     * @param eventIDs 需要删除的活动主键
     * @return 结果
     */
    @Override
    public int deleteEventsByEventIDs(Long[] eventIDs)
    {
        return eventsMapper.deleteEventsByEventIDs(eventIDs);
    }

    /**
     * 删除活动信息
     * 
     * @param eventID 活动主键
     * @return 结果
     */
    @Override
    public int deleteEventsByEventID(Long eventID)
    {
        return eventsMapper.deleteEventsByEventID(eventID);
    }
}
