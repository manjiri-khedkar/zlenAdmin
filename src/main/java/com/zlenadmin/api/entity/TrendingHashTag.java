package com.zlenadmin.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "trending_hash_tags", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class TrendingHashTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id" , insertable = false, updatable = false)
	private long id;
	
	@Column(name = "hash_tag" , insertable = false, updatable = false)
	private String hash_tag;
	
	@Column(name = "created_at" , insertable = false, updatable = false)
	private Date created_at;
	
	@Column(name = "url" , insertable = false, updatable = false)
	private String url;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getHash_tag() {
		return hash_tag;
	}

	public void setHash_tag(String hash_tag) {
		this.hash_tag = hash_tag;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
