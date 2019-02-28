package com.icss.hr.pic.pojo;

import java.io.InputStream;
import java.util.Date;

/**
 * Õº∆¨ µÃÂ¿‡
 * 
 * @author DLETC
 *
 */
public class Pic {

	private int picId;

	private String picName;

	private String picInfo;

	private long picSize;

	private String picAuthor;

	private InputStream picData;

	private Date picDatetime;

	public Pic() {
		super();
	}

	public Pic(String picName, String picInfo, long picSize, String picAuthor, InputStream picData, Date picDatetime) {
		super();
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picData = picData;
		this.picDatetime = picDatetime;
	}

	public Pic(int picId, String picName, String picInfo, long picSize, String picAuthor, InputStream picData,
			Date picDatetime) {
		super();
		this.picId = picId;
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picData = picData;
		this.picDatetime = picDatetime;
	}

	public int getPicId() {
		return picId;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}

	public long getPicSize() {
		return picSize;
	}

	public void setPicSize(long picSize) {
		this.picSize = picSize;
	}

	public String getPicAuthor() {
		return picAuthor;
	}

	public void setPicAuthor(String picAuthor) {
		this.picAuthor = picAuthor;
	}

	public InputStream getPicData() {
		return picData;
	}

	public void setPicData(InputStream picData) {
		this.picData = picData;
	}

	public Date getPicDatetime() {
		return picDatetime;
	}

	public void setPicDatetime(Date picDatetime) {
		this.picDatetime = picDatetime;
	}

	@Override
	public String toString() {
		return "Pic [picId=" + picId + ", picName=" + picName + ", picInfo=" + picInfo + ", picSize=" + picSize
				+ ", picAuthor=" + picAuthor + ", picData=" + picData + ", picDatetime=" + picDatetime + "]";
	}
	
}