# FCM 자바 서버 구축 가이드

이 문서는 Spring Boot 기반의 FCM(Firebase Cloud Messaging) 자바 서버 구축 방법을 안내합니다.

## 1. Firebase 프로젝트 생성 및 설정

1. Firebase 콘솔에서 새 프로젝트를 생성합니다. [Firebase 프로젝트 생성 및 관리 문서](https://firebase.google.com/docs/projects/learn-more?hl=ko)
2. 프로젝트 설정 > 서비스 계정 > Firebase Admin SDK > 새 비공개 키 생성을 통해 서비스 계정 키를 다운로드합니다. [Firebase Admin SDK 초기화 문서](https://firebase.google.com/docs/admin/setup?hl=ko#initialize-sdk)

## 2. Spring Boot 프로젝트 생성 및 의존성 추가

1. Spring Initializr를 통해 Spring Boot 프로젝트를 생성합니다. [Spring Initializr 사용 문서](https://start.spring.io/)
2. 프로젝트에 Firebase Admin SDK, Jackson Data Bind 등의 의존성을 추가합니다. [Firebase Admin SDK 설정 문서](https://firebase.google.com/docs/admin/setup?hl=ko#initialize-sdk)
   1. [Gradle 의존성 확인](/Users/jaeyoung/IdeaProjects/wabpush/build.gradle)
   
## 3. Firebase Admin SDK 초기화

1. 다운로드한 서비스 계정 키를 프로젝트에 추가합니다.
2. Firebase Admin SDK를 초기화하여 FCM 메시지 전송을 준비합니다. [Firebase Admin SDK 초기화 문서](https://firebase.google.com/docs/admin/setup?hl=ko#initialize-sdk)
   1. [FCM SDK 추가하는 방법 (FCM Initaializer)](/Users/jaeyoung/IdeaProjects/wabpush/src/main/java/toy/project/wabpush/init/FCMInitializer.java)
## 4. FCM 메시지 전송 구현

1. Spring Boot 컨트롤러에서 FCM 토큰, 메시지 제목, 내용을 전달받습니다.
2. 서비스 계층에서 Firebase Admin SDK를 사용하여 FCM 메시지를 전송합니다. [Firebase Admin SDK 메시지 전송 문서](https://firebase.google.com/docs/cloud-messaging/admin/send-messages?hl=ko)
    1. [FCM Controller](/Users/jaeyoung/IdeaProjects/wabpush/src/main/java/toy/project/wabpush/controller/FcmController.java)
   2. [FCM Service](/Users/jaeyoung/IdeaProjects/wabpush/src/main/java/toy/project/wabpush/service/fcm/impl/FCM_ServiceImpl.java)
   3. [FCM Response](/Users/jaeyoung/IdeaProjects/wabpush/src/main/java/toy/project/wabpush/dto/fcm/FcmResponse.java)
## 5. 추가 기능: 토큰 관리, 메시지 로깅 등
1. FCM 토큰 관리: 클라이언트에서 전송된 토큰을 데이터베이스에 저장하고 관리합니다.
2. 메시지 로깅: 전송된 메시지 내역을 데이터베이스에 기록하여 모니터링할 수 있습니다.

이와 같은 방식으로 Firebase FCM을 활용하여 Spring Boot 기반의 푸시 알림 서버를 구축할 수 있습니다.
