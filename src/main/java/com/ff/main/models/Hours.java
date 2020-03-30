package com.ff.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="hours")
public class Hours implements Comparable<Hours>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hour_id")
	private long hourId;
	
	@Column(name="day_of_week")
	@NotNull
	private int dayOfWeek;
	
	@Column(name="open")
	@NotNull
	private boolean open;
	
	@Column(name="start")
	private int start;
	
	@Column(name="end")
	private int end;
	
	@OneToOne()
	@JoinColumn(name="provider_id", referencedColumnName="provider_id")
	@NotNull
	private Provider provider;
	

	public long getHourId() {
		return hourId;
	}
	public void setHourId(long hourId) {
		this.hourId = hourId;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public int compareTo(Hours o) {
		// TODO Auto-generated method stub
		if(this.dayOfWeek > o.dayOfWeek) {return 100;}
		else if(this.dayOfWeek < o.dayOfWeek) {return -100;}
		return 0;
	}
	
	
}
