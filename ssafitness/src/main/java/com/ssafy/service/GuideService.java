package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Guide;

public interface GuideService {
	public List<Guide> guideList();
	public List<Guide> partList(String part);
}
