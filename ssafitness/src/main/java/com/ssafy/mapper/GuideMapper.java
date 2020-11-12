package com.ssafy.mapper;


import java.util.List;

import com.ssafy.vo.Guide;

public interface GuideMapper {
	public List<Guide> guideList();
	public List<Guide> partList(String part);
}
