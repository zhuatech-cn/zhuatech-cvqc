/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.cvqc.controller;
import cn.zhuatech.cvqc.common.ApiResponse;import cn.zhuatech.cvqc.service.InspectionSamplingService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/cvqc/insights/inspection-sampling") public class InspectionSamplingController {private final InspectionSamplingService service;/**
                                                                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                      */
public InspectionSamplingController(InspectionSamplingService service){this.service=service;}/**
                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                   */
@PostMapping ApiResponse<InspectionSamplingService.Result> plan(@Valid @RequestBody InspectionSamplingService.Request request){return ApiResponse.ok(service.plan(request));}}
