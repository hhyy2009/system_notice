/**
 * 
 */
package com.bx.notice.core.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 通知主体对象信息，即系统通知接收方
 * @author luzhenghong
 *
 */
@Entity
@Table(name = "TB_NOTICEBODYINFO")
public class NoticeBodyInfo implements Serializable {
	private Long id;
	private NoticeBaseInfo noticeBaseInfo;
	private String BodyId;//主体对象ID，在企业客户管家中显示，此id则为企业的BX。。。信息，其他则为用户ID
	private Integer isRead;//是否已读，1：已读，0：未读，默认为0
	private String noticeTime;//通知时间
	private String updateTime;//更新时间
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = NoticeBaseInfo.class)
	@JoinColumn(name = "noticeId")
	public NoticeBaseInfo getNoticeBaseInfo() {
		return noticeBaseInfo;
	}
	public void setNoticeBaseInfo(NoticeBaseInfo noticeBaseInfo) {
		this.noticeBaseInfo = noticeBaseInfo;
	}
	
	@Column(name = "bodyid", length = 20)
	public String getBodyId() {
		return BodyId;
	}
	public void setBodyId(String bodyId) {
		BodyId = bodyId;
	}
	
	@Column(name = "isread")
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	
	@Column(name = "noticetime", length = 30)
	public String getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	@Column(name = "updatetime", length = 30)
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
