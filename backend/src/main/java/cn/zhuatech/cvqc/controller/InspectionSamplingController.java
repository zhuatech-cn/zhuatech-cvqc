/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.cvqc.controller;
import cn.zhuatech.cvqc.common.ApiResponse;import cn.zhuatech.cvqc.service.InspectionSamplingService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/cvqc/insights/inspection-sampling") public class InspectionSamplingController {private final InspectionSamplingService service;public InspectionSamplingController(InspectionSamplingService service){this.service=service;}@PostMapping ApiResponse<InspectionSamplingService.Result> plan(@Valid @RequestBody InspectionSamplingService.Request request){return ApiResponse.ok(service.plan(request));}}
