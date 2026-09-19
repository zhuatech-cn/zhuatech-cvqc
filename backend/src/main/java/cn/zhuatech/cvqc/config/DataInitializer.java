/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.cvqc.config;
import cn.zhuatech.cvqc.model.*; import cn.zhuatech.cvqc.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.LocalDate; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {/**
                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                              */
@Bean CommandLineRunner seed(OperatingUnitRepository units,WorkRecordRepository records,ResourceRegisterRepository resources,ReviewRecordRepository reviews,UserRepository users,PasswordEncoder encoder){return args->{if(units.count()>0)return;
 OperatingUnit a3=units.save(new OperatingUnit("CV-A3","A3 视觉工位","精密装配线",5000)),b1=units.save(new OperatingUnit("CV-B1","B1 视觉工位","电子装配线",7000)),c4=units.save(new OperatingUnit("CV-C4","C4 视觉工位","总成包装线",5500));
 WorkRecord a=records.save(new WorkRecord("CV-A3-260801-018","PRD-EDU-COVER","电驱控制器上盖外观检测",a3,4800,4216,38,LocalDate.now().plusDays(1),WorkRecord.Status.RUNNING,"MODEL-V4.2")); WorkRecord b=records.save(new WorkRecord("CV-B1-260801-011","PRD-CONNECTOR-08","连接器针脚完整性检测",b1,6200,6200,16,LocalDate.now(),WorkRecord.Status.COMPLETED,"MODEL-V3.8")); WorkRecord c=records.save(new WorkRecord("CV-C4-260801-032","PRD-NAMEPLATE","铭牌字符与二维码检测",c4,5200,3120,27,LocalDate.now().plusDays(2),WorkRecord.Status.RELEASED,"MODEL-V4.0"));
 resources.saveAll(List.of(new ResourceRegister("CAM-A3-01","A3 三相机检测站",a3,ResourceRegister.Status.RUNNING,97),new ResourceRegister("CAM-B1-02","B1 顶视针脚检测站",b1,ResourceRegister.Status.RUNNING,94),new ResourceRegister("CAM-C4-04","C4 字符检测站",c4,ResourceRegister.Status.ALARM,73)));
 reviews.saveAll(List.of(new ReviewRecord("REV-260801-142",a,"缺陷分类",68,4,ReviewRecord.Result.PENDING,"顾宁"),new ReviewRecord("REV-260801-119",b,"误检分析",120,1,ReviewRecord.Result.PASSED,"韩序"),new ReviewRecord("REV-260731-088",c,"漏检分析",80,5,ReviewRecord.Result.FAILED,"顾宁")));
 String demo=encoder.encode("Demo@2026"); users.saveAll(List.of(new UserAccount("operator",demo,"韩序",UserAccount.Role.DOMAIN_USER,"CV-A3"),new UserAccount("planner",demo,"顾宁",UserAccount.Role.DOMAIN_OPERATOR,null),new UserAccount("quality",demo,"林砚",UserAccount.Role.QUALITY,null),new UserAccount("admin",encoder.encode("ZhuaTech@2026"),"系统管理员",UserAccount.Role.ADMIN,null)));};}}
