package com.icss.hr.emp.pojo;

import java.util.List;

/**
 * È«ÎÄ¼ìË÷DTO
 * @author DLETC
 *
 */
public class EmpDto {
	
	private int recordCount;
	
	private List<Emp> list;

	public EmpDto() {
		super();
	}

	public EmpDto(int recordCount, List<Emp> list) {
		super();
		this.recordCount = recordCount;
		this.list = list;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<Emp> getList() {
		return list;
	}

	public void setList(List<Emp> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "EmpDto [recordCount=" + recordCount + ", list=" + list + "]";
	}
	
}