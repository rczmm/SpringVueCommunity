package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 关系信息对象 relationships
 * 
 * @author rcz
 * @date 2024-05-27
 */
public class Relationships extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    private Long relationshipID;

    /** 居民ID */
    @Excel(name = "居民ID")
    private Long residentID;

    /** 家庭ID */
    @Excel(name = "家庭ID")
    private Long familyID;

    /** 与户主关系 */
    @Excel(name = "与户主关系")
    private String relationshipWithHead;

    public void setRelationshipID(Long relationshipID) 
    {
        this.relationshipID = relationshipID;
    }

    public Long getRelationshipID() 
    {
        return relationshipID;
    }
    public void setResidentID(Long residentID) 
    {
        this.residentID = residentID;
    }

    public Long getResidentID() 
    {
        return residentID;
    }
    public void setFamilyID(Long familyID) 
    {
        this.familyID = familyID;
    }

    public Long getFamilyID() 
    {
        return familyID;
    }
    public void setRelationshipWithHead(String relationshipWithHead) 
    {
        this.relationshipWithHead = relationshipWithHead;
    }

    public String getRelationshipWithHead() 
    {
        return relationshipWithHead;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("relationshipID", getRelationshipID())
            .append("residentID", getResidentID())
            .append("familyID", getFamilyID())
            .append("relationshipWithHead", getRelationshipWithHead())
            .toString();
    }
}
