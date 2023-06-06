<div align="center"> <h1>🍽️ 포장뚝딱 🍽️</h1> 

  
   <h3>길거리 포장마차도 일반 가게들처럼 미리 주문해두고 시간 맞춰 픽업할수 있도록 <br>
   포장마차를 위한 포장주문 앱을 제작해보았습니다.</h3>
<h4>서비스 링크: http://ec2-15-165-21-12.ap-northeast-2.compute.amazonaws.com/</h4>

<h4>프로젝트 소개 및 시연영상 링크: https://codinglcy.notion.site/4cf398e12d624469a290453e4fb5d32e</h4>

</div>

<br>

***
<br>

## 프로젝트 정보
> @codinglcy 개인 프로젝트<br>
> 개발 기간: 2023.02 ~ 2023.05

<br><br>

## 스택
### 환경
<img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=flat-square&logo=Visual Studio Code&logoColor=white"/> <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/>
<br>
### 배포
<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat-square&logo=GitHub Actions&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"/>
<br>
### 백엔드
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white"/> <img src="https://img.shields.io/badge/MongoDB-47A248?style=flat-square&logo=MongoDB&logoColor=white"/> <img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/>
<br>
### 프론트엔드
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white"/> <img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=React&logoColor=white"/> <img src="https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=Figma&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/>

<br>
<br>

## API 문서
[Order 관련 API](https://documenter.getpostman.com/view/20119504/2s93RNyFCp)&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;[Seller 관련 API](https://documenter.getpostman.com/view/20119504/2s93RNyFCr)&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;[Shop 관련 API](https://documenter.getpostman.com/view/20119504/2s93RNyFCs)

<br>
<br>

## 디렉토리 구조

```
📦backend
 ┣ 📂.git
 ┣ 📂.github
 ┃ ┗ 📂workflows
 ┃ ┃ ┗ 📜gradleCD.yml
 ┣ 📂.gradle
 ┃ ┣ 📂7.6
 ┃ ┣ 📂buildOutputCleanup
 ┃ ┗ 📂vcs-1
 ┣ 📂.idea
 ┣ 📂gradle
 ┃ ┗ 📂wrapper
 ┃ ┃ ┣ 📜gradle-wrapper.jar
 ┃ ┃ ┗ 📜gradle-wrapper.properties
 ┣ 📂out
 ┣ 📂scripts
 ┃ ┣ 📜start.sh
 ┃ ┗ 📜stop.sh
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂generated
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂lcy
 ┃ ┃ ┃ ┃ ┗ 📂takeoutddookddack
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AppConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SellerController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShopController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BankAccount.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CheckResult.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Location.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginResult.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Menu.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderMenu.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Orders.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Seller.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Shop.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂jwt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtProvider.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityUtil.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mail
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MailConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MailProperties.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AbstractRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrdersRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SellerRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShopRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SellerService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShopService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TakeoutddookddackApplication.java
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┃ ┗ 📂build
 ┃ ┃ ┃ ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜asset-manifest.json
 ┃ ┃ ┃ ┃ ┃ ┗ 📜index.html
 ┃ ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┗ 📜application.yml
 ┃ ┗ 📂test
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂lcy
 ┃ ┃ ┃ ┃ ┗ 📂takeoutddookddack
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TakeoutddookddackApplicationTests.java
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┗ 📜application.yml
 ┣ 📜.gitignore
 ┣ 📜appspec.yml
 ┣ 📜build.gradle
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┣ 📜HELP.md
 ┣ 📜README.md
 ┗ 📜settings.gradle
 ```
