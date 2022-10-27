package com.codegym.dating.controller;

import com.codegym.dating.dto.ReportDetailDto;
import com.codegym.dating.service.IReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/admin")
public class RestFulReportDetailController {

    @Autowired
    private IReportDetailsService iReportDetailsService;

    @GetMapping(value = "/list/warning/{id}")
    public ResponseEntity<?> getUserReportDetail(@PathVariable int id){
        List<ReportDetailDto> reportDetails = iReportDetailsService.findByIdReportDetailUser(id);
        if(reportDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(reportDetails, HttpStatus.OK);
        }
    }

}
