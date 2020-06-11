# 자산관리 프로젝트 소개
![main](https://user-images.githubusercontent.com/66577309/83989759-698e5680-a982-11ea-92f7-6ef4b41f5666.PNG)

## 목차
[1. 플로우 차트](#1-플로우-차트)<br>
[2. 개발 ](#2-개발-환경)<br>
[3. 개체 관계 다이어그램 ERD: Entity-Relationship Diagram](#3-개체-관계-다이어그램-ERD:-Entity-Relationship Diagram)<br>
[4-1. 로그인 및 회원 가입](#4-1-로그인-및-회원-가입)<br>
[4-2. 마이페이지](#4-2-마이페이지)<br>
[4-3. 수입 및 지출 입력 페이지](#4-3-수입-및-지출-입력-페이지)<br>
[4-4. 수입 및 지출 조회 ](#4-4-수입-및-지출-조회-페이지)<br>
[4-5. 게시판](#4-5-게시판)<br>
[4-6. 관리자](#4-6-관리자)

### 1. 플로우 차트
![자산관리 페이지](https://user-images.githubusercontent.com/66577309/84332987-676a0900-abc9-11ea-826c-4841c13080a4.JPG)

### 2. 개발 환경
![개발 환경](https://user-images.githubusercontent.com/66577309/84333399-9e8cea00-abca-11ea-85f9-a0ff73b01e36.JPG)

### 3. 개체 관계 다이어그램 ERD: Entity-Relationship Diagram
![자산관리 페이지](https://user-images.githubusercontent.com/66577309/84332987-676a0900-abc9-11ea-826c-4841c13080a4.JPG)

### 4-1. 로그인 및 회원 가입
![login](https://user-images.githubusercontent.com/66577309/83991951-7a8e9600-a989-11ea-84e4-ae8bfc304167.JPG)
* 회원 아이디가 존재하면 아이디 및 비밀번호를 입력 후 로그인한다 -> 메인 페이지로 이동한다.
* 회원 가입 버튼을 클릭시 회원 가입 페이지로 이동한다.

![insertmember](https://user-images.githubusercontent.com/66577309/83991954-7d898680-a989-11ea-9dee-f847ab0c577f.JPG)
* 필수 입력 항목 : 아이디, 중복체크, 비밀번호, 비밀번호 확인, 이름, 이메일, 주소, 상세 주소, 전화 번호
* 항목 작성 후 '가입하기' 버튼 클릭 시 회원 등록이 처리되고 메인 페이지로 이동한다.

### 4-2. 마이페이지
![mypage](https://user-images.githubusercontent.com/66577309/83991779-dd336200-a988-11ea-9e0f-4b3bb45e5e76.JPG)
* 이 페이지에서는 회원 정보를 수정 할 수 있다.
* 정보 수정 시 비밀번호를 필수 입력 사항이다.
* 새 비밀번호 설정 시 현재 비밀번호를 입력하면 수정이 가능하고 로그 아웃이 되고 메인 페이지로 이동한다.

### 4-3. 수입 및 지출 입력 페이지
![register](https://user-images.githubusercontent.com/66577309/83991783-df95bc00-a988-11ea-958f-a2485d1e9d4e.JPG)
* 현재 해당 되는 월이 표시된다.
* 수입 및 지출 등록 버튼을 클릭해서 각각의 정보를 등록할 수 있다.
* 삭제시 해당 체크 박스를 체크하고 삭제 버튼을 누른다.(1개씩 삭제 가능)
* 지출 및 수입 정보를 각각 검색할 수 있다.
* 현재 월의 좌측 버튼을 클릭하면 이전달의 정보가 표시되고 우측 버튼을 클릭하면 다음달의 정보가 표시된다.

### 4-4. 수입 및 지출 조회 페이지
![reference1](https://user-images.githubusercontent.com/66577309/83991786-e15f7f80-a988-11ea-9b32-33c29d40ed60.JPG)
* 해당 월의 지출과 수입의 정보를 카테고리 별로 파이차트로 표현된다.
* 해당 카테고리 클릭 시 해당 카테고리의 월별 정보가 나타난다.
* 현재 월의 좌측 버튼을 클릭하면 이전달의 정보가 표시되고 우측 버튼을 클릭하면 다음달의 정보가 표시된다.

![reference2](https://user-images.githubusercontent.com/66577309/83991788-e3294300-a988-11ea-8323-61b5aa344d63.JPG)
* 현재 월로부터 과거로 1년간 월별 수입 및 지출의 총 금액이 나타난다.
* 현재 월의 좌측 버튼을 클릭하면 이전달의 정보가 표시되고 우측 버튼을 클릭하면 다음달의 정보가 표시된다.

### 4-5. 게시판
![board](https://user-images.githubusercontent.com/66577309/83991790-e45a7000-a988-11ea-9ee8-4eb215f52b34.JPG)
* 작성된 게시글이 표시되어 나타난다.
* 로그인한 사용자는 글쓰기 버튼을 눌러서 글쓰기를 할 수 있다.

### 4-6. 관리자
![admin](https://user-images.githubusercontent.com/66577309/83991795-e58b9d00-a988-11ea-9602-5c19c1e5cad1.JPG)
* 관리자 페이지 중 회원 목록 페이지가 나타난다.
* 회원 가입한 모든 사용자들의 정보가 표시된다.
* 해당 아이디를 클릭 시 그 회원의 상세정보가 표시되고 회원정보 수정 및 삭제가 가능하다.

![admin-board](https://user-images.githubusercontent.com/66577309/83991798-e6bcca00-a988-11ea-9a2b-c3de31b4bcb5.JPG)
* 공지사항 게시판 페이지이다.
* 관리자로 로그인할때만 공지사항 게시판 글쓰기를 할 수 있다.
