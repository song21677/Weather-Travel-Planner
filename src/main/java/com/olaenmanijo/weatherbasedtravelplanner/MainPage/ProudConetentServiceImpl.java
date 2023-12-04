package com.olaenmanijo.weatherbasedtravelplanner.MainPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProudConetentServiceImpl implements ProudConetentService {

	@Autowired
	ProudConetentDAO dao;
	
	
	public List<GetRecommendDTO> community(){

		List<GetRecommendDTO> list = dao.community();
		for (GetRecommendDTO dto : list) {
			if (dto.getPLANNER_REVIEW_TITLE().length()>=8) {
			String shortenedTitle = dto.getPLANNER_REVIEW_TITLE().substring(0, 8) + "...";
			dto.setPLANNER_REVIEW_TITLE(shortenedTitle);
			}
		}
		return list;
	}

}
