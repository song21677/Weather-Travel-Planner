package com.olaenmanijo.weatherbasedtravelplanner.global.file.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.olaenmanijo.weatherbasedtravelplanner.global.file.controller.CloudFileUpload;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.dto.request.TravelReviewFileRequest;
import com.olaenmanijo.weatherbasedtravelplanner.global.file.mapper.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    @Transactional
    public void saveTravelReviewFiles(final Long travelReviewNo, final List<TravelReviewFileRequest> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (TravelReviewFileRequest file : files) {
        	file.setTravelReviewNo(travelReviewNo);
        }
        fileMapper.travelReviewImgSaveAll(files);
    }

}
