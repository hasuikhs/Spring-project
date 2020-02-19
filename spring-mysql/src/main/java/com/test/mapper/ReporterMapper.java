package com.test.mapper;

import java.util.List;

import com.test.domain.ReporterVO;

public interface ReporterMapper {
	
	public List<ReporterVO> getList();
	
	public ReporterVO read(int userno);
	
	public void insert(ReporterVO reporter);
}
