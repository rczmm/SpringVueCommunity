package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RelationshipsMapper;
import com.ruoyi.system.domain.Relationships;
import com.ruoyi.system.service.IRelationshipsService;

/**
 * 关系信息Service业务层处理
 * 
 * @author rcz
 * @date 2024-05-27
 */
@Service
public class RelationshipsServiceImpl implements IRelationshipsService 
{
    @Autowired
    private RelationshipsMapper relationshipsMapper;

    /**
     * 查询关系信息
     * 
     * @param relationshipID 关系信息主键
     * @return 关系信息
     */
    @Override
    public Relationships selectRelationshipsByRelationshipID(Long relationshipID)
    {
        return relationshipsMapper.selectRelationshipsByRelationshipID(relationshipID);
    }

    /**
     * 查询关系信息列表
     * 
     * @param relationships 关系信息
     * @return 关系信息
     */
    @Override
    public List<Relationships> selectRelationshipsList(Relationships relationships)
    {
        return relationshipsMapper.selectRelationshipsList(relationships);
    }

    /**
     * 新增关系信息
     * 
     * @param relationships 关系信息
     * @return 结果
     */
    @Override
    public int insertRelationships(Relationships relationships)
    {
        return relationshipsMapper.insertRelationships(relationships);
    }

    /**
     * 修改关系信息
     * 
     * @param relationships 关系信息
     * @return 结果
     */
    @Override
    public int updateRelationships(Relationships relationships)
    {
        return relationshipsMapper.updateRelationships(relationships);
    }

    /**
     * 批量删除关系信息
     * 
     * @param relationshipIDs 需要删除的关系信息主键
     * @return 结果
     */
    @Override
    public int deleteRelationshipsByRelationshipIDs(Long[] relationshipIDs)
    {
        return relationshipsMapper.deleteRelationshipsByRelationshipIDs(relationshipIDs);
    }

    /**
     * 删除关系信息信息
     * 
     * @param relationshipID 关系信息主键
     * @return 结果
     */
    @Override
    public int deleteRelationshipsByRelationshipID(Long relationshipID)
    {
        return relationshipsMapper.deleteRelationshipsByRelationshipID(relationshipID);
    }
}
