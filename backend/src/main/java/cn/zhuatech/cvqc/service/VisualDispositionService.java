/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.cvqc.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Service;

/** 根据视觉置信度、缺陷面积和安全属性生成质检处置结论。 */
@Service
public class VisualDispositionService {
    public DispositionResult decide(DispositionRequest request) {
        int riskScore = Math.min(100,
            (int) Math.round(request.confidence() * 45)
                + (int) Math.round(request.defectAreaRatio() * 100 * 0.3)
                + (request.safetyCritical() ? 35 : 0)
                + Math.min(15, request.repeatCount() * 3));
        String disposition = request.safetyCritical() && request.confidence() >= 0.8 ? "STOP_LINE"
            : request.confidence() >= 0.65 || request.repeatCount() >= 3 ? "MANUAL_REVIEW" : "PASS";
        String severity = riskScore >= 80 ? "CRITICAL" : riskScore >= 55 ? "MAJOR" : "MINOR";
        int sampleExpansion = "PASS".equals(disposition) ? 0 : Math.min(50, 5 + request.repeatCount() * 5);
        return new DispositionResult(disposition, severity, riskScore, sampleExpansion,
            "STOP_LINE".equals(disposition) ? "暂停产线并通知质量负责人" : "MANUAL_REVIEW".equals(disposition) ? "扩大抽样并由质检员复判" : "记录结果并继续在线检测");
    }

    public record DispositionRequest(
        @NotBlank(message = "请输入缺陷类型") String defectType,
        @DecimalMin("0.0") @DecimalMax("1.0") double confidence,
        @DecimalMin("0.0") @DecimalMax("1.0") double defectAreaRatio,
        boolean safetyCritical,
        @PositiveOrZero int repeatCount
    ) {}

    public record DispositionResult(String disposition, String severity, int riskScore, int sampleExpansion, String nextAction) {}
}
