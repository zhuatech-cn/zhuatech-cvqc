# CVQC API 摘要

版权所有 © 2026 上海如静知华信息科技有限公司。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录并获取 JWT |
| GET | `/api/admin/dashboard` | 视觉质量运营数据 |
| GET | `/api/admin/work-orders` | 在线检测批次清单 |
| GET | `/api/shopfloor/dashboard` | 视觉质检员工作台 |
| POST | `/api/shopfloor/work-orders/{id}/reports` | 提交缺陷人工复核 |
| POST | `/api/shopfloor/ai-preview` | 调用可替换视觉 AI Provider |
| POST | `/api/shopfloor/visual-disposition` | 根据缺陷置信度与安全等级生成放行处置结论 |
