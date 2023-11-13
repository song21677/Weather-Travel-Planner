package com.olaenmanijo.weatherbasedtravelplanner.global.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.request.TravelReviewFileRequest;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.response.TravelReviewFileResponse;


@Mapper
public interface FileMapper {

    /**
     * 여행기 파일 정보 저장
     * @param files - 파일 정보 리스트
     */
    void travelReviewImgSaveAll(TravelReviewFileRequest file);
    
    /**
     * 여행기 파일 리스트 조회
     * @param travelReviewNo - 여행기 번호
     * @return 파일 리스트
     */
    List<TravelReviewFileResponse> travelFileFindByTravelReviewNo(Long travelReviewNo);
    
    /**
     * 파일 리스트 조회
     * @param fileNoes - 파일 번호 리스트
     * @return 파일정보 리스트
     */
    List<TravelReviewFileResponse> travelFileFindByFileNoes(List<Long> fileNoes);
    
    /**
     * 파일 삭제
     * @param fileNo - 파일 번호
     * @return 파일정보 리스트
     */
    void deleteByFileNoes(List<Long> fileNoes);
    
    

}
