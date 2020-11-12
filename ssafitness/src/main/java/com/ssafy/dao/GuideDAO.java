package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Guide;

public interface GuideDAO {
	public List<Guide> guideList();
	public List<Guide> partList(String part);
}
