package com.example.demo.controller;

import com.example.demo.service.SubsidyCarListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carList")
@Slf4j
public class SubsidyCarListController {
    
    private final SubsidyCarListService subSidyCarListService;
    
    @GetMapping
    public ResponseEntity<?> carListRender(
            @RequestParam(defaultValue = "1", name = "pageNo") int pageNo,
            @RequestParam(name = "search") String search
    ) {
        log.info("/carList GET! pageNo: {}", pageNo);
        return ResponseEntity.ok().body(subSidyCarListService.findCarList(pageNo, search));
    }
    
}
