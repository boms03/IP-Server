# IP-Server

<br>

## Git Convention

### Commit Convention

- `[FEAT]` : 새로운 기능 구현
- `[DOCS]` : README나 WIKI 등의 문서 수정
- `[ADD]` : Feat 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성
- `[REMOVE]` : 폴더 또는 파일 삭제
- `[FIX]` : 버그, 오류 해결
- `[RENAME]` : 파일 이름 변경시
- `[REFACTOR]` : 기능 추가나 버그 수정이 없는 코드 변경 ( 코드 구조 변경 등의 리팩토링 )
- `[TEST]` : 테스트 추가 또는 이전 테스트 수정
- `[STYLE]` : 코드의 의미에 영향을 미치지 않는 변경 사항 ( 코드 형식, 변수명 변경, 오타 수정, 세미콜론 추가: 비즈니스 로직에 변경 없음 )
- `[CHORE]` : src 또는 test 파일을 수정하지 않는 기타 변경 사항 ( 빌드/패키지 매니저 설정 변경 등, 파일 이동 )
- `[MERGE]` : Merge 하는 경우

### 커밋 예시

- git commit -m "#이슈 번호 [커밋 태그] 커밋 내용"
  - `ex ) git commit -m "#1 [FEAT] 회원가입 기능 완료"`

<br>

### Branch Convention

- [main] : 최종 배포
- [develop] : 주요 개발, main merge 이전에 거치는 branch
- [feature] : 각자 개발, 기능 추가
- [fix] : 에러 수정, 버그 수정
- [docs] : README, 문서
- [refactor] : 코드 리펙토링 (기능 변경 없이 코드만 수정할 때)
- [modify] : 코드 수정 (기능의 변화가 있을 때)
- [chore] : gradle 세팅, 위의 것 이외에 거의 모든 것

### 브랜치 명 예시

- feature/#이슈 번호-기능 이름
  - `ex) feature/#1-login`

<br>
