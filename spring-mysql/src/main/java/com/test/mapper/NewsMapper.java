package com.test.mapper;

import java.util.List;
import java.util.Map;

import com.test.domain.NewsVO;

public interface NewsMapper {

	public List<Map<String, Object>> getList();

	public Map<String, Object> get(int newsno);

	public void insert(NewsVO news);
}
