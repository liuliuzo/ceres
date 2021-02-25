# ceres gateway
因为公司的特殊需求比如需要支持MQ,HTTP,DUBBO等不同协议然后结合zuul生命周期管理PRE-> Routing-> POST | ERROR的网关，所以准备参考spring cloud gateway、zuul gateway、soul gateway 手撕一个gateway。

鉴权部分是参考的OpenAPI的发布订阅的设计。
