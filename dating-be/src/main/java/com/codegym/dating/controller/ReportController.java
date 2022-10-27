package com.codegym.dating.controller;
import com.codegym.dating.dto.IReportDetailsDto;
import com.codegym.dating.dto.ReportDetailsDto;
import com.codegym.dating.dto.ReportDto;
import com.codegym.dating.model.ReportDetails;
import com.codegym.dating.service.IReportService;
import com.codegym.dating.service.IReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api")
public class ReportController {
    @Autowired
    private IReportService iReportService;
    @Autowired
    private IReportDetailsService reportDetailsService;

    @GetMapping("/users/report-list")
    public ResponseEntity<List<ReportDto>> getAllReport() {
        List<ReportDto> reportList = iReportService.findAllReport();
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }


    @GetMapping("/admin/report-detail")
    public ResponseEntity<Page<IReportDetailsDto>> getAllReportUser(@PageableDefault(8) Pageable pageable,
                                                                    @RequestParam Optional<String> keyWord) {
        String key = keyWord.orElse("");
        Page<IReportDetailsDto> reportDetailsPage = reportDetailsService.findAll(key, pageable);
        if (reportDetailsPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(reportDetailsPage, HttpStatus.OK);
        }
    }

    @PatchMapping("/admin/confirm/{id}")
    public ResponseEntity<Void> confirm(@PathVariable("id") Integer id) {
        this.reportDetailsService.confirm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users/report")
    public ResponseEntity<Void> add(@RequestBody ReportDetailsDto reportDetailsDto) {
        this.reportDetailsService.save(reportDetailsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/admin/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        this.reportDetailsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/admin/detail/{id}")
    public ResponseEntity<ReportDetails> getInfoReport(@PathVariable Integer id) {

        ReportDetails reportDetailsObj = this.reportDetailsService.findById(id);

        if (reportDetailsObj == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reportDetailsObj, HttpStatus.OK);
    } }