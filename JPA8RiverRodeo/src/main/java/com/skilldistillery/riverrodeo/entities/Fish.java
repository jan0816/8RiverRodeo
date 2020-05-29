package com.skilldistillery.riverrodeo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Fish {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="size_in_cm")
	private double sizeInCm;
	
	@Column(name="picture_url")
	private String pictureUrl;
	
	@OneToOne
	@JoinColumn(name="river_id")
	private River river;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="day_caught")
	@CreationTimestamp
	private LocalDate dayCaught;

	//METHODS BEGIN:
	public Fish() {
		
	}
	

	public River getRiver() {
		return river;
	}


	public void setRiver(River river) {
		this.river = river;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public double getSizeInCm() {
		return sizeInCm;
	}

	public void setSizeInCm(double sizeInCm) {
		this.sizeInCm = sizeInCm;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public LocalDate getDayCaught() {
		return dayCaught;
	}

	public void setDayCaught(LocalDate dayCaught) {
		this.dayCaught = dayCaught;
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
		Fish other = (Fish) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fish [id=");
		builder.append(id);
		builder.append(", sizeInCm=");
		builder.append(sizeInCm);
		builder.append(", pictureUrl=");
		builder.append(pictureUrl);
		builder.append(", river=");
		builder.append(river);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dayCaught=");
		builder.append(dayCaught);
		builder.append("]");
		return builder.toString();
	}
	
}
