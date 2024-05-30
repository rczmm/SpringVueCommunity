package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 居民信息对象 residents
 * 
 * @author rcz
 * @date 2024-05-29
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

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String workUnit;

    /** 职业 */
    @Excel(name = "职业")
    private String profession;

    /** 教育背景 */
    @Excel(name = "教育背景")
    private String educationLevel;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 紧急联系人 */
    @Excel(name = "紧急联系人")
    private String emergencyContact;

    /** 宗教信仰 */
    @Excel(name = "宗教信仰")
    private String religiousBelief;

    /** 健康状况 */
    @Excel(name = "健康状况")
    private String healthCondition;

    /** 子女信息 */
    @Excel(name = "子女信息")
    private String childrenInformation;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String maritalStatus;

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
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setWorkUnit(String workUnit) 
    {
        this.workUnit = workUnit;
    }

    public String getWorkUnit() 
    {
        return workUnit;
    }
    public void setProfession(String profession) 
    {
        this.profession = profession;
    }

    public String getProfession() 
    {
        return profession;
    }
    public void setEducationLevel(String educationLevel) 
    {
        this.educationLevel = educationLevel;
    }

    public String getEducationLevel() 
    {
        return educationLevel;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }
    public void setEmergencyContact(String emergencyContact) 
    {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContact() 
    {
        return emergencyContact;
    }
    public void setReligiousBelief(String religiousBelief) 
    {
        this.religiousBelief = religiousBelief;
    }

    public String getReligiousBelief() 
    {
        return religiousBelief;
    }
    public void setHealthCondition(String healthCondition) 
    {
        this.healthCondition = healthCondition;
    }

    public String getHealthCondition() 
    {
        return healthCondition;
    }
    public void setChildrenInformation(String childrenInformation) 
    {
        this.childrenInformation = childrenInformation;
    }

    public String getChildrenInformation() 
    {
        return childrenInformation;
    }
    public void setMaritalStatus(String maritalStatus) 
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() 
    {
        return maritalStatus;
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
            .append("nation", getNation())
            .append("email", getEmail())
            .append("workUnit", getWorkUnit())
            .append("profession", getProfession())
            .append("educationLevel", getEducationLevel())
            .append("note", getNote())
            .append("emergencyContact", getEmergencyContact())
            .append("religiousBelief", getReligiousBelief())
            .append("healthCondition", getHealthCondition())
            .append("childrenInformation", getChildrenInformation())
            .append("maritalStatus", getMaritalStatus())
            .toString();
    }
}
