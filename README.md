#asset-platform

# Dockerfile Maven
[![License](https://img.shields.io/github/license/spotify/dockerfile-maven.svg)](LICENSE)

资产云业务中台，基于[flowable]二次开发

[flowable]: https://github.com/flowable/flowable-engine


## 环境要求

`java 8`
`mysql` 
`maven 3`

## 启动

新建数据库，运行abpm-process-center/src/main/resources/sql下[abpm.sql]文件

[abpm.sql]:https://github.com/assetcloud/assetBPM/blob/master/abpm-process-center/src/main/resources/sql/abpm.sql

修改以下配置文件

abpm-process-center/src/main/resources/flowable.cfg.xml `数据库连接`
abpm-process-center/src/main/resources/application.properties `spring.profiles.active`
abpm-process-center/src/main/resources/config/database/jdbc-dev.properties `数据库连接`
abpm-process-center/src/main/resources/config/database/jdbc-test.properties `数据库连接`

运行com.asset.FlowableApplication ，访问 [http//:localhost:9000] 

# asset-platform-idm

asset-platform-idm模块为组织架构管理模块，包含员工、用户、角色、应用等管理模块

## 环境要求

`java 8`
`mysql` 
`maven 3`

## 启动

新建数据库，运行asset-platform-idm/src/main/resources/sql下[asset-platform-idm.sql]文件

修改以下配置文件

abpm-process-center/src/main/resources/application.properties

运行com.asset.AssetPlatformIdmApplication ，访问 [http//:localhost:8083] 
