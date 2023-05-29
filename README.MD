# 인증 구현 1 - 스프링 시큐리티 구현
## 사용 기술: JPA, TEST CODE, thymeleaf, docker, postgresql
## 구현 내용
1. 도커로 postgres 사용
2. 권한을 enum으로 생성(ROLE_ADMIN, ROLE_MANAGER, ROLE_MEMBER)
2. lombok + jpa로 사용자 생성 - id, pw, role, deptname
   1. 리포지토리도 생성(CrudRepository를 상속받기만 하면 된다.)
3. 테스트 코드 생성 및 사용자 정보 입력
4. 컨트롤러를 생성한다.(BaseController)
5. html에서 thymeleaf 문법을 이용해 위 컨트롤러 인자를 표시한다.
## 나머지 할일
- 레디스로 여러 컨테이너 세션 공유하게 만들기
- jwt구현
## 참고: https://www.youtube.com/watch?v=ry2pRoAnrIc


# 인증 구현 2 - 세션 구현
## 사용기술: 세션, redis
## 구현 내용
1. 프로젝트를 빌드하고 생성된 jar파일로 컨테이너를 두 개 실행한다.
```shell
java -jar handsOnJwtAndSession-0.0.1-SNAPSHOT.jar --server.port=8081
```
2. 도커로 레디스를 실행한다.
   - 레디스 네트워크 생성 후 생성된 네트워크에 레디스 실행 
```shell
# 네트워크 생성
docker network create redis-net
# 레디스 실행
docker run --name my-redis -p 6379:6379 --network redis-net -d redis redis-server --appendonly yes
```
3. 레디스 관련 메이븐 의존성과 config를 추가한다.
4. 다시 빌드한 뒤에 8080, 8081 포트로 서버를 띄운다.
5. 아래와 같은 에러 발생.
```text
org.springframework.data.redis.serializer.SerializationException: Cannot serialize; nested exception is org.springframework.core.serializer.support.SerializationFailedException: Failed to serialize object using DefaultSerializer; nested exception is java.io.NotSerializableException: com.example.handsonjwtandsession.domain.Member
	at org.springframework.data.redis.serializer.JdkSerializationRedisSerializer.serialize(JdkSerializationRedisSerializer.java:96) ~[spring-data-redis-2.7.12.jar!/:2.7.12]
 ...
 ...
```
5. 레디스에 세션을 저장하기 위해선 저장하려는 객체에 직렬화를 구현해야한다.
6. User 도메인에 ```implement Serializable```을 추가한다.
7. 8080과 8081의 세션이 공유되는 것을 확인할 수 있다. (로그인, 로그아웃이 동기화됨)
