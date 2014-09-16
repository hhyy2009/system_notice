/**
 * 
 */
package com.bx.notice.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luzhenghong
 *
 */
@Entity
@Table(name = "MY_TABLE")
public class MyTable implements Serializable {
	private Long id;
	private String name;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
