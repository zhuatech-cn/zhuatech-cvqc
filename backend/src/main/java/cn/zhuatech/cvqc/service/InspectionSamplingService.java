/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.cvqc.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
@Service public class InspectionSamplingService {
 public Result plan(Request r){double factor=1+(r.historicalDefectRate()/10)+(100-r.modelConfidence())/100+(r.recentProcessShift()?0.8:0)+(r.criticalProduct()?0.5:0);int sample=Math.min(r.lotSize(),Math.max(20,(int)Math.ceil(Math.sqrt(r.lotSize())*factor)));String status=r.criticalProduct()&&r.historicalDefectRate()>2||r.modelConfidence()<60?"FULL_INSPECTION":r.recentProcessShift()||r.historicalDefectRate()>3||r.modelConfidence()<80?"TIGHTENED":"NORMAL";if(status.equals("FULL_INSPECTION"))sample=r.lotSize();List<String> reasons=new ArrayList<>();if(r.recentProcessShift())reasons.add("近期工艺参数发生漂移");if(r.historicalDefectRate()>3)reasons.add("历史缺陷率偏高");if(r.modelConfidence()<80)reasons.add("视觉模型置信度不足");if(reasons.isEmpty())reasons.add("过程与视觉模型表现稳定");return new Result(sample,status,reasons);}
 public record Request(@NotBlank String lotId,@Min(1) int lotSize,@DecimalMin("0") @DecimalMax("100") double historicalDefectRate,@DecimalMin("0") @DecimalMax("100") double modelConfidence,@NotNull Boolean recentProcessShift,@NotNull Boolean criticalProduct){}
 public record Result(int sampleSize,String status,List<String> reasons){}
}
