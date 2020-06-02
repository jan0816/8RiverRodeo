package com.skilldistillery.riverrodeo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	//FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String password;
	
	@Column(name = "phone_number")
	private String phoneNumber;

	private Integer rank;
	
	private String role;
	
	private boolean enabled;
	
	@Column(name = "picture_url")
	private String pictureUrl;
	
	@OneToMany(mappedBy = "team")
	private List<User> teamMembers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public List<User> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<User> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team [id=").append(id).append(", name=").append(name).append(", password=").append(password)
				.append(", phoneNumber=").append(phoneNumber).append(", rank=").append(rank).append(", role=")
				.append(role).append(", enabled=").append(enabled).append(", pictureUrl=").append(pictureUrl)
				.append(", teamMembers=").append(teamMembers).append("]");
		return builder.toString();
	}

	public Team() {
		super();
	}

	public Team(int id, String name, String password, String phoneNumber, Integer rank, String role,
			String pictureUrl) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.rank = rank;
		this.role = role;
		this.pictureUrl = pictureUrl;
	}
	
	
	
}
