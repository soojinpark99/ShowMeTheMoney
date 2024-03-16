## 💰 프로젝트 소개

간단 가계부 프로젝트 Show me the Money입니다.

## 👥 팀원 소개

- 박수진[FE] <https://github.com/soojinpark99>
- 박영채[BE] <https://github.com/cree-per-com>
- 이채화[BE] <https://github.com/chaehwa82>

## 🛠️ 개발 환경

- Front-end: HTML5, CSS3, Javascript
- Back-end : Spring Boot, Spring Security, MySQL

## 💡 주요 기능

### 회원가입, 로그인 기능

![KakaoTalk_Video_2024-03-16-11-23-55-ezgif com-resize](https://github.com/soojinpark99/ShowMeTheMoney/assets/154590790/49f401e3-33c2-4b50-9d6b-0a1dd39c8256)

- 회원가입시 아이디, 비밀번호 조건에 맞도록 제어

### 달력 페이지

![KakaoTalk_Video_2024-03-16-10-34-15-ezgif com-resize1](https://github.com/soojinpark99/ShowMeTheMoney/assets/154590790/df1df7e8-deee-4263-9fa4-caffce61bd35)
![KakaoTalk_Video_2024-03-16-10-09-28-ezgif com-resize2](https://github.com/soojinpark99/ShowMeTheMoney/assets/154590790/1dcf8b45-1c7d-450d-86d7-93e7b705d667)

- 지출/수입 내역 작성 기능
- 작성한 내역은 달력에 일별 합계 금액으로 표시/달력 상단에 월별 합계 금액으로 표시
- 달력의 날짜를 클릭하면 해당 날짜의 거래 내역 조회 가능
- 거래 내역 수정, 삭제 기능

### 통계 페이지

![KakaoTalk_Video_2024-03-16-10-09-34-ezgif com-resize](https://github.com/soojinpark99/ShowMeTheMoney/assets/154590790/85e7b6ac-e93b-440c-bc4a-49ae4e1e687f)

- 거래 내역의 카테고리별 합계 금액을 도넛 차트로 보여주는 기능
- 합계 금액이 높은 순서대로 정렬

## ✅ 협업 규칙

### 헙업 툴

Notion: <https://www.notion.so/Show-me-the-Money-5f93b4b98620448f8f295b7774fc00ce?pvs=4>

### 커밋 메시지 규칙

- [FE] / [BE] 말머리로 적기
- 이모티콘으로 커밋 유형 나타내기
  - ✨: 기능 구현
  - ✏️: 코드 수정
  - 🐞: 버그 수정
  - ❌: 코드 삭제
  - 📁: 파일 위치 이동

## 📝 후기

### 박수진

제대로 해보는 첫 팀프로젝트라 다른 팀원들과 협업을 하는 데 어려움이 있었습니다. 노션을 적극적으로 활용하여 원활하게 소통할 수 있게끔 노력했습니다. 이벤트를 등록하고 데이터를 렌더링하는 과정에서 기대했던 결과와 달랐던 경우가 많았는데, 원인을 분석하고 해결 방법을 찾으면서 많은 것을 배웠습니다. 제일 애먹었던 부분은 ajax로 프론트와 백이 데이터를 주고받는 부분이었습니다. 사소한 것까지 사전에 협의하고 코드를 꼼꼼하게 리뷰해야 한다는 것을 느꼈습니다. 아쉬웠던 부분은 HTML, CSS, 바닐라 자바스크립트만으로 하는 프로젝트라 개발 생산성이 낮았다는 점입니다. 다음 프로젝트에서는 리액트 등 다른 기술을 적용하여 생산성을 높이는 것에 집중해보고 싶습니다.

### 박영채

어떻게 하면 더 효율적인 코드가 될까에 대한 고민이 많았습니다. DB 접근기술로 JDBCTemplate을 사용하니 "findby~"뒤로 메소드 이름이 너무 길어져서, 프로젝트 중간에 JPQL로 변경하여 간결한 코드를 완성했습니다. 다음에는 다른 DB기술로도 구현해 보고 싶네요. 또 사용자가 로그인 실패 시 처음에는 SecurityFilterChain 쪽에서 커스텀 예외를 던지려고 했는데, 쉽지 않고 권장하지 않는 방식이라고 해서 예상보다 오랜 시간 애를 먹었습니다. 고민 끝에 AuthenticationFailureHandler를 직접 구현한 MyFailureHandler를 만들어서 처리했는데 문제가 명쾌하게 해결되어서 뿌듯했던 기억이 납니다. 이번 프로젝트에서 도커를 처음 다뤄봤는데 컨테이너 기술에 익숙하지 않아 docker compose 파일 구동에 많은 어려움을 겪었지만 그만큼 공부할 수 있는 기회가 되었다고 생각합니다. 개인적으로 이전 프로젝트보다 난이도가 확 높아진 것 같은데, 그만큼 아는 것이 많아지고 성장했다고 생각됩니다. 다음 프로젝트에서는 더 발전된 모습을 보여드리고 싶습니다.

### 이채화

가계부 프로젝트를 진행하면서 API 설계부터 서버 응답, 컨트롤러와 서비스와 DTO 간의 연결로 구동되는 데이터 관련 기능들에 대해 학습할 수 있던 좋은 경험이었습니다. 팀원분들의 코드 해석과 직접 코드를 짜보면서 책으로만 학습한 이론들이 실제 코드에서는 어떻게 구현 되는지 알 수 있었고 역시 이론과 실전은 다르다는 걸 다시 한 번 깨달았습니다. 다음 프로젝트에서는 사용성 기능 개선과 업그레이드를 목표로 현재보다 더 많은 부분을 맡아 스스로 발전해야겠다고 다짐하게 되었습니다.
