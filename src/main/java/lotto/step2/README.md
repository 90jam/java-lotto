**step2. 로또 당첨(자동)**
-
**- 요구사항**
1. 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급
2. 로또 1장의 가격은 1000원
3. 로또 구매 갯수 계산
4. 구매한 로또번호 출력
5. 당첨 번호 입력
6. 당첨 번호와 비교하여 일치한 개수 출력
7. 총 수익률 계산

**- TODO List**
1. ~~도메인 설계를 해보자~~
2. ~~도메인 객체 생성(Lotto, Price, Number)~~ 
   - ~~랜덤으로 6개 숫자 반환(1~46)~~
3. ~~출력~~ 
   - ~~가격 입력받고 주문개수 출력~~ 
   - ~~로또번호 출력~~
   - ~~당첨번호 입력~~
4. ~~출력 통계~~
5. ~~총 수익률~~
6. ~~검증~~

**- 기능 목록**
1. domain
   - Lotto : 로또
   - Lotteries : 로또 컬렉션
   - Store : 로또판매점, 로또 생성  
   - Statics : 통계 계산
   - Profit : 총 수익률 계산
2. controller
   - LottoController : 컨트롤러
3. view
   - InputView : 클라이언트 입력 뷰
   - ResultView : 출력 뷰

**- 피드백 반영**
1. 주석 없이도 읽기 좋은 깔끔한 코드를 만드는 것이니, 앞으로는 정말 복잡한 비즈니스 로직이 외에는 최소화 해보는건 어떨까요~?
   -> 주석 삭제하고 메서드명을 변경
2. 금액은 숫자로 구성되어 있느니 int나 long형으로 받는건 어떨까요~?
   -> InputView에서 next()로 받으면 에러가..nextLine()으로 받아서 
      view에서 string -> int 로 바꾸고 값 검증이 나은가요 / 도메인에서 값 검증이 나을까요?
3. 상수와 필드 사이에 줄 바꿈 / 바뀌지 않는 필드는 final / 할당도 생성자 
   -> 변경
4. indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다
   -> 메소드로 분리
5. 명확한 동사를 붙여주면 메서드명만 보고 의미를 파악할 수 있을 가능성이 높아질거에요!
   -> 메서드명 변경
6. 테스트 패키지는 프로덕션 코드의 패키지와 동일하게 구성해주세요
   -> 패키지 추가
7. 꼭 배열이 필요한 상황이 아니라면 collection 사용을 권장드립니다
   -> 리스트로 변경
8. 주생성자를 정해놓고, 부생성자에선 주생성자를 사용하면 중복을 최소화할 수 있습니다
   -> ?
9. 로또가... 아마 45개일거에요...
10. 1~45 사이의 숫자가 들어온다는 보장이 안되는 것 같네요 
   -> 1-45 값으로 변경, 검증추가     
11. 개수를 비교하는데 정렬이 필요한가요~?
12. 반대로 로또가 출력될 땐 정렬이 되지 않는거같습니다
   -> 비교시 정렬 삭제, 출력시 정렬할 수 있도록 추가
13. 역할 분리 및 정리가 시급합니다!
14. LottoCompany 역할 분리
   -> LottoStatics 클래스와 LottoProfit 클래스로 분리 
15. Enum 클래스 추가
   -> 맵삭제후 enum 클래스 추가  
16. winningNumbers -> Numbers 객체로 
   -> 불필요한 Numbers 객체 삭제후 Lotto 객체로 대체 