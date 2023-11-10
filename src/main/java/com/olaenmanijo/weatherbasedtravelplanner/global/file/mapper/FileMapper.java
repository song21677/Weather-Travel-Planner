package com.olaenmanijo.weatherbasedtravelplanner.global.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.request.TravelReviewFileRequest;


@Mapper
public interface FileMapper {

    /**
     * 여행기 파일 정보 저장
     * @param files - 파일 정보 리스트
     */
    void travelReviewImgSaveAll(List<TravelReviewFileRequest> files);

}
