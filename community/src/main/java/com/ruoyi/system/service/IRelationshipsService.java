package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.Relationships;

/**
 * 关系信息Service接口
 * 
 * @author rcz
 * @date 2024-05-27
 */
public interface IRelationshipsService 
{
    /**
     * 查询关系信息
     * 
     * @param relationshipID 关系信息主键
     * @return 关系信息
     */
    public Relationships selectRelationshipsByRelationshipID(Long relationshipID);

    /**
     * 查询关系信息列表
     * 
     * @param relationships 关系信息
     * @return 关系信息集合
     */
    public Map<Long,List<Relationships>> selectRelationshipsList(Relationships relationships);

    /**
     * 新增关系信息
     * 
     * @param relationships 关系信息
     * @return 结果
     */
    public int insertRelationships(Relationships relationships);

    /**
     * 修改关系信息
     * 
     * @param relationships 关系信息
     * @return 结果
     */
    public int updateRelationships(Relationships relationships);

    /**
     * 批量删除关系信息
     * 
     * @param relationshipIDs 需要删除的关系信息主键集合
     * @return 结果
     */
    public int deleteRelationshipsByRelationshipIDs(Long[] relationshipIDs);

    /**
     * 删除关系信息信息
     * 
     * @param relationshipID 关系信息主键
     * @return 结果
     */
    public int deleteRelationshipsByRelationshipID(Long relationshipID);
}
