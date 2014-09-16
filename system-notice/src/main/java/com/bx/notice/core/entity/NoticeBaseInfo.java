/**
 * 
 */
package com.bx.notice.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

/**
 * 系统通知的基本信息
 * @author luzhenghong
 *
 */
@Entity
@Table(name = "TB_NOTICEBASEINFO")
public class NoticeBaseInfo implements Serializable {
	private Long id;
	private String title; //系统通知标题
	private Integer noticeType;//通知类型，1：图文，2：调查，3：点对点，4：工作流
	private Integer showPlatform;//通知显示平台，1：企业客户管家，2：SOMP平台，3：用户APP
	private String content;//通知内容
	private Integer validTime;//通知有效期，默认新建后3个月内有效
	private Integer isTop;//是否置顶，1：是，0：否，只能有一条通知是置顶状态
	private Integer orderNum;//排序号，越小越靠前
	private String createTime;//新建日期
	private String updateTime;//修改日期
	private String createUserName;//新增人名称
	private String createUserId;//新增人ID
	private Set<NoticeBodyInfo> noticeBodyInfos = new HashSet<NoticeBodyInfo>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "title", length = 100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "noticetype")
	public Integer getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}
	@Column(name = "showplatform")
	public Integer getShowPlatform() {
		return showPlatform;
	}
	public void setShowPlatform(Integer showPlatform) {
		this.showPlatform = showPlatform;
	}
	
	@Lob
	@Column(name = "content", columnDefinition = "CLOB")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "validtime")
	public Integer getValidTime() {
		return validTime;
	}
	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}
	
	@Column(name = "istop")
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	
	@Column(name = "ordernum")
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	@Column(name = "createtime", length = 30)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "updatetime", length = 30)
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "createusername", length = 20)
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	@Column(name = "createuserid", length = 20)
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	@OneToMany(mappedBy = "noticeBaseInfo", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = NoticeBodyInfo.class)
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OrderBy("id desc")
	public Set<NoticeBodyInfo> getNoticeBodyInfos() {
		return noticeBodyInfos;
	}
	public void setNoticeBodyInfos(Set<NoticeBodyInfo> noticeBodyInfos) {
		this.noticeBodyInfos = noticeBodyInfos;
	}
}
