package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人口信息对象 residents
 * 
 * @author rczmm
 * @date 2024-04-14
 */
public class Residents extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 居民ID */
    private Long residentID;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateOfBirth;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idnumber;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 户籍地址 */
    @Excel(name = "户籍地址")
    private String registeredAddress;

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String residentialAddress;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setResidentID(Long residentID) 
    {
        this.residentID = residentID;
    }

    public Long getResidentID() 
    {
        return residentID;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setDateOfBirth(Date dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() 
    {
        return dateOfBirth;
    }
    public void setIdnumber(String idnumber) 
    {
        this.idnumber = idnumber;
    }

    public String getIdnumber() 
    {
        return idnumber;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setRegisteredAddress(String registeredAddress) 
    {
        this.registeredAddress = registeredAddress;
    }

    public String getRegisteredAddress() 
    {
        return registeredAddress;
    }
    public void setResidentialAddress(String residentialAddress) 
    {
        this.residentialAddress = residentialAddress;
    }

    public String getResidentialAddress() 
    {
        return residentialAddress;
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
            .append("residentID", getResidentID())
            .append("name", getName())
            .append("gender", getGender())
            .append("dateOfBirth", getDateOfBirth())
            .append("idnumber", getIdnumber())
            .append("contact", getContact())
            .append("registeredAddress", getRegisteredAddress())
            .append("residentialAddress", getResidentialAddress())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
