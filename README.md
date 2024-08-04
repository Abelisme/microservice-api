# Microservices Practice Project

## 項目簡述

本項目是一個微服務實踐項目，旨在展示多個服務如何獨立運行，同時利用最佳的微服務模式來實現可擴展性、性能和彈性。該項目集成了多種功能和技術，包括郵件發送、API 管理、數據庫操作、緩存實現、消息隊列等。

## 主要功能模塊

1. **郵件發送模塊**：
    - [x] 實現郵件發送功能
    - [x] 發送完成后將數據傳輸到 ActiveMQ

2. **ChatGPT API 微服務**：
    - [x] 管理和發送所有 API 請求
    - [x] 使用 OkHttp 進行 API 訪問

3. **數據庫服務模塊 (service-two)**：
    - [x] 連接 MySQL 數據庫
    - [ ] 計劃實現緩存功能及定期更新機制

4. **通用模塊 (common)**：
    - [x] 包含 RestTemplate 和各種轉換方法
    - [x] 管理常用功能供其他模塊使用

5. **網關模塊**：
    - [x] 使用 Spring Cloud Gateway
    - [x] 統一管理所有功能模塊的訪問

6. **服務註冊與發現**：
    - [x] 使用 Eureka Server

## 技術線

- SpringBoot：應用框架
- Spring Cloud Gateway：API 網關
- Eureka Server：服務註冊與發現
- Docker：容器化平台
- Java：編程語言
- Maven：構建工具
- Git：版本控制
- MySQL：數據庫
- MyBatis：ORM 框架
- ActiveMQ：消息隊列
- OkHttp/RestTemplate：HTTP 客戶端

## 開發計劃

- 集成 Logback 日誌機制
- 實現日誌寫入 MQ 和數據庫的功能
- 為郵件發送模塊添加 MQ 監聽器，將數據存入數據庫
- 實現各模塊的單元測試
- Docker 化各個模塊
- 優化緩存實現和更新策略

## 開發環境建立

1. 安裝必要工具：Git、Java、Maven 和 Docker
2. clone項目： git clone https://github.com/Abelisme/microservice-api.git
3. 進入腳本目錄： cd /microservices-api/build/docker/scripts/
4. 部署 Docker： sh ./deploy.sh
5. 訪問應用：http://localhost:8761/

## 目標

本項目目的在整合和實踐各種微服務相關技術，包括：

- HTTP/HTTPS GET/POST 請求處理
- ActiveMQ 的使用
- MySQL 和 MyBatis 的應用
- 郵件發送功能
- Docker 容器化
- 網關和服務註冊的實現
- 單元測試的編寫

通過這個項目，希望能夠學習和實踐最新的微服務架構和相關技術。