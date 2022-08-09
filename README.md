

#  💡 알고리즘 스터디 💡

SSAFY 5기 서울 13반 알고리즘 스터디 기록

 - [ ] 서울 15반 안승태
 - [ ] 서울 15반 지강훈
 - [ ] 서울 15반 이종민
 - [ ] 서울 15반 신도연
 - [ ] 서울 15반 엄희정
 - [ ] 서울 15반 김지영
<br><br>

## 📌 Rule
월요일 밤12시까지 문제를 풀어주세요.
화요일 오프라인 스터디에서 새로운 문제를 출제합니다.
* ❗❗ 문제풀이 마감 : 매주 월요일 24:00 까지
* ❕❕ 문제 출제 : 매주 화요일 오프라인 스터디

<br><br>

## 📌 Convention
###  1️⃣ Code Convention
각 코드 별 목적을 주석으로 작성합니다.
변수와 함수 이름 또한 역할을 알 수 있도록 간단한 주석을 덧붙입니다.

<br>

### 2️⃣ Project Convention

각 멤버별 프로젝트 구조는 다음과 같습니다
**프로젝트이름/week번호/플랫폼_문제번호_레벨_문제이름/...**

    baekjoon/username/week15/BOJ_1051_S3_숫자정사각형/...

<br>

### 3️⃣ Commit Convention
한번에 모든 파일을 add하지 않고 type별로 분리하여 commit 합니다.

    docs : README.md 등 문서 작성 및 수정
    code : 코드 작성
    fix : 코드 수정
    add : 기존에 푼 문제에 대한 추가
    remove : 코드 및 문서 삭제
    merge : pr(pull request)을 통해 자신의 repo에서 원본 repo로 merge하기
  <br>

적용 예시 ::
1. BOJ의 1051번 숫자 정사각형 (silver3) 문제를 풀었다면
해당 코드를 하나의 commit으로 분리합니다.  
이 때의 commit message는 다음과 같이 통일합니다
		
		 git commit -m "code : BOJ 1051 S3 숫자정사각형"

	해당 코드를 수정할 때의 commit message는 다음과 같이 통일합니다.
		
		 git commit -m "fix : BOJ 1051 S3 숫자정사각형"

2.  코드에 대한 설명을 작성하고
해당 문서를 하나의 commit으로 분리합니다.  
이 때의 commit message는 다음과 같습니다.
		
		 git commit -m "docs : BOJ 1051 S3 숫자정사각형"

3. main README.md 파일을 수정할 때의 commit message는 다음과 같습니다.
		
		 git commit -m "docs : main README update"

5. 파일을 삭제할 경우 commit message는 다음과 같습니다
		
		 git commit -m "remove : 삭제파일"
		
<br>

### 4️⃣ Review Convention
1. Pull Request의 제목은 다음과 같이 통일합니다.
**이름 : 문제플랫폼 문제번호 문제등급 문제제목** 
		
		 DAUN JO : BOJ 1051 S3 숫자정사각형
		
2. Pull Request의 comment에는 본인이 작성한 README.md의 내용을 추가합니다. 

3. 문제에 해당하는 유형을 선택하여 PR에 label을 attach하고,   
 자신의 PR의 assignee에 자신을 추가 후 문제풀이 week에 해당하는 Milestones을 선택합니다.

4. 기존에 PR을 작성 후 새로운 문제를 풀었을 경우, 새로운 문제에 대한 commit을 하기 전 다음 과정을 수행합니다.

	- ❓ 코드리뷰가 완료 되었을 경우  
		자신의 PR에서 merge 버튼을 눌러 merge 합니다. 
		
	- ❓ 리뷰 완료 전 새로운 문제를 풀 경우
		1. 자신의 local에서 새로 푼 문제에 대한 branch를 생성합니다.  
		이 때 branch의 이름을 **문제플랫폼-문제번호**과 같이 생성하는 것을 권장합니다.
		
			    boj-1051
		
		2. 새로운 문제에 대한 code와 README.md에 대한 commit을 추가 후 push합니다.   
		이 때의 commit message는 2️⃣ Commit Convention에서 언급한 규칙에 맞게 설정합니다.
		3. 이 때 반드시 (a)에서 생성한 branch로 push 되는지 확인합니다.
		4. 본인 계정의 fork된 repo에서 Pull Request을 작성할 때,   
		코드가 push된 브랜치(a에서 생성한 jodawoooon/boj-1051)에서   
		organization repo의 main 브랜치로 Pull Request를 보냅니다.


<br><br>

## 📌 Solved Problems
### 🚩 week 1
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ |  5555 | [반지](https://www.acmicpc.net/problem/5555) | 문자열 | silver5 |
| BOJ |  2504 | [괄호의 값](https://www.acmicpc.net/problem/2504) | 스택 | silver1 |
| BOJ |  1260 | [DFS와 BFS](https://www.acmicpc.net/problem/1260) | 탐색 | silver2 |
| BOJ |  1051 | [숫자 정사각형](https://www.acmicpc.net/problem/1051) | 브루트포스| silver4 |
| BOJ | 18352 | [특정 거리의 도시 찾기](https://www.acmicpc.net/problem/18352) | 다익스트라 벨만포드| silver2 |


### 🚩 week 2
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ |  5397 | [키로거](https://www.acmicpc.net/problem/5397) | 문자열 | silver2 |
| BOJ |  1182 | [부분수열의합](https://www.acmicpc.net/problem/1182) | 스택 | silver2 |
| BOJ |  2644 | [촌수계산](https://www.acmicpc.net/problem/2644) | 탐색 | silver2 |
| BOJ | 17052 | [클레어와 팰린드롬 ](https://www.acmicpc.net/problem/17052) | 브루트포스| bronze4 |
| BOJ | 11724 | [연결 요소의 개수](https://www.acmicpc.net/problem/11724) | 다익스트라 벨만포드| silver2 |

