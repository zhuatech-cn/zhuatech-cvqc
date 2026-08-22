/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.cvqc;
import cn.zhuatech.cvqc.service.InspectionSamplingService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class InspectionSamplingServiceTests {private final InspectionSamplingService service=new InspectionSamplingService();@Test void fullyInspectsRiskyCriticalLot(){var r=service.plan(new InspectionSamplingService.Request("L1",1000,3,95,true,true));assertEquals("FULL_INSPECTION",r.status());assertEquals(1000,r.sampleSize());}@Test void usesNormalSamplingForStableLot(){var r=service.plan(new InspectionSamplingService.Request("L2",1000,0.5,98,false,false));assertEquals("NORMAL",r.status());assertTrue(r.sampleSize()<1000);}}
