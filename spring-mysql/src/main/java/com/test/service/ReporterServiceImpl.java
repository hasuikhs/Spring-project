package com.test.service;

import org.springframework.stereotype.Service;

import com.test.domain.ReporterVO;
import com.test.mapper.ReporterMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReporterServiceImpl implements ReporterService {

	private ReporterMapper mapper;

	@Override
	public void insert(ReporterVO reporter) {
		
	}
}
