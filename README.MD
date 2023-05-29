# 인증 구현 1 - 스프링 시큐리티 구현
## 사용 기술: JPA, TEST CODE, thymeleaf
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