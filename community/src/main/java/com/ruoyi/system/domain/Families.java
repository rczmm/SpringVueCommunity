package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 家庭信息对象 families
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public class Families extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 家庭ID */
    private Long familyID;

    /** 户主姓名 */
    @Excel(name = "户主姓名")
    private String headName;

    /** 家庭人数 */
    @Excel(name = "家庭人数")
    private Long familySize;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String address;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setFamilyID(Long familyID) 
    {
        this.familyID = familyID;
    }

    public Long getFamilyID() 
    {
        return familyID;
    }
    public void setHeadName(String headName) 
    {
        this.headName = headName;
    }

    public String getHeadName() 
    {
        return headName;
    }
    public void setFamilySize(Long familySize) 
    {
        this.familySize = familySize;
    }

    public Long getFamilySize() 
    {
        return familySize;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("familyID", getFamilyID())
            .append("headName", getHeadName())
            .append("familySize", getFamilySize())
            .append("address", getAddress())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
