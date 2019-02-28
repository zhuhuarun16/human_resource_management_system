package com.icss.hr.pic.pojo;

import java.util.Arrays;
import java.util.Date;

/**
 * Õº∆¨ µÃÂ¿‡
 * @author DLETC
 *
 */
public class Pic {

	private Integer picId;

	private String picName;

	private String picInfo;

	private Long picSize;

	private String picAuthor;

	private Date picDatetime;

	private byte[] picData;

	public Pic() {
		super();
	}

	public Pic(Integer picId, String picName, String picInfo, Long picSize, String picAuthor, Date picDatetime,
			byte[] picData) {
		super();
		this.picId = picId;
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picDatetime = picDatetime;
		this.picData = picData;
	}

	public Pic(String picName, String picInfo, Long picSize, String picAuthor, Date picDatetime, byte[] picData) {
		super();
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picDatetime = picDatetime;
		this.picData = picData;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName == null ? null : picName.trim();
	}

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo == null ? null : picInfo.trim();
	}

	public Long getPicSize() {
		return picSize;
	}

	public void setPicSize(Long picSize) {
		this.picSize = picSize;
	}

	public String getPicAuthor() {
		return picAuthor;
	}

	public void setPicAuthor(String picAuthor) {
		this.picAuthor = picAuthor == null ? null : picAuthor.trim();
	}

	public Date getPicDatetime() {
		return picDatetime;
	}

	public void setPicDatetime(Date picDatetime) {
		this.picDatetime = picDatetime;
	}

	public byte[] getPicData() {
		return picData;
	}

	public void setPicData(byte[] picData) {
		this.picData = picData;
	}

	@Override
	public String toString() {
		return "Pic [picId=" + picId + ", picName=" + picName + ", picInfo=" + picInfo + ", picSize=" + picSize
				+ ", picAuthor=" + picAuthor + ", picDatetime=" + picDatetime + ", picData=" + Arrays.toString(picData)
				+ "]";
	}

}