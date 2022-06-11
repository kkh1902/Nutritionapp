# **먹보**  - 내가 먹는 모든 음식의 칼로리가 궁금하다면?

  먹보 이미지
  
</br>
🧚‍♀️ 먹보는?
- 칼로리를 고민하는 사람들을 위해, 편리하게 칼로리 검색과 기록, 계산까지 한번에 할 수 있도록 도와주는 서비스
- 부족한 영양소를 바탕으로 음식 추천 서비스
</br>

# 먹보 - Frontend
### &#10024; 먹보 주요 기능 &#10024;
로그인, 회원가입, 소셜로그인
기록 : 바디스펙, daily 칼로리 기록, 캘린더
검색 : 칼로리 검색 (필터링, 정렬, 페이징)

</br>

### &#127919; FrontEnd Trouble Shooting
  
  
<details>
<summary>현수의 front 터짐 문제
  </summary>
<div markdown="1">       

  </br>몸무게를 수정할때마다 수정한 날짜와 몸무게를 같이 저장을 하자.
  
</div>
</details>

# 앱 페이지

## 로딩 페이지
<img width="150" src="https://user-images.githubusercontent.com/91061890/173192506-8be6c84b-57dd-4cf0-9e33-5338efbf8ee6.png"></img>

## 로그인 페이지

<img width="150" src="https://user-images.githubusercontent.com/91061890/173192491-a02d9803-7453-4bce-add6-8d22a1579d58.png"></img>

## 메인 화면
<img width="150" src="https://user-images.githubusercontent.com/91061890/173192522-28d6d842-7474-4843-aab5-068f637c543e.png"></img>

## 음식 추가 화면
<img width="150" src="https://user-images.githubusercontent.com/91061890/173192530-95f92800-4020-42e7-9556-dffeaaa88b0c.png"></img>

## 음식 목록 화면
<img width="150" src="http<s://user-images.githubusercontent.com/91061890/173192548-85249d65-96c5-4165-8895-bdacf2758604.png"></img>


# 먹보 - Backend

### &#10024; 먹보 주요 기능 &#10024;
#### 1. 사용자의 음식 데이터를 통해 필요한 영양분에 맞는 음식 추천 (Algorithm) 구축

</br>

![image](https://user-images.githubusercontent.com/91061890/172547686-f17daff9-fd98-4e73-a158-e09fc579df59.png)
<br>한국 영양 학회에 따른 BMR공식(사용자의 스펙으로부터) 계산 하여 일일 탄수화물, 단백질, 지방 추천 및 부족한 영양소 확인

</br>


#### 2.약 2만여개의 음식 데이터(식약처 제공 데이터)를 2번의 데이터 가공을 통해 Data Base 구축
![image](https://user-images.githubusercontent.com/91061890/172547193-27c289df-8927-4798-a699-8af654cc5cbf.png)


</br>


#### 3.사용자 스펙 조회,기록,수정,삭제 연산 가능
mysql 이미지(user)


</br>

### &#128187;기술스택/라이브러리
#### 기술스택
<table width = "200" style="text-align:center;" >
  <tr>
    <th height = "40"> 종류</th>
    <th height = "40">이름</th>

  </tr>
  <tr>
    <td>서버 프레임워크</td>
    <td>Express</td>
  </tr>
  <tr>
    <td >Database</td>
    <td>Mysql</td>
  </tr>
  <tr>
    <td >Application 구축 software</td>
    <td>Docker</td>
  </tr>
  
  <table width = "200" style="text-align:center;" >
  <tr>
    <th height = "40">라이브러리</th>
    <th height = "40">Appliance</th>
    <tr>
    <td>prettier</td>
    <td> 클린코드 </td>
  </tr>
  </table>
  
  
</br>




### ⚙Node & npm Version
node: v14.17.1  
npm: 6.14.13  
  
  
</br>


### &#127919; Backend Trouble Shooting
  
  
<details>
<summary>칼로리 통계를 보여줄때 몸무게에 따른 기초대사량을 베이스로 보여주는데 예를 들어 오늘 몸무게를 수정하고 어제 먹었던 음식을 기록해야한다면 현재 몸무게가 아닌 어제의 몸무게가 들어가야하는데 어떻게 어제의 몸무게를 알수있을까?</summary>
<div markdown="1">       

  </br>몸무게를 수정할때마다 수정한 날짜와 몸무게를 같이 저장을 하자.
  
</div>
</details>

<details>
<summary>너무 많은 data가 있는데 요청할 때 시간이 많이 걸리지 않을까?</summary>
<div markdown="1">       

  </br>첫번째로 Database를 OpenApi를 통해서 받아서 정규화 한다(사용)
  </br>
  두번째로 data를 openapi해서 전체를 받아서 정규화를 한다.
  -> 첫번째 방법이 효율적이다. 애초에 받을 때 조건을 걸어서 받는다 
  (SQL 지식 부족)
  
  
</div>
</details>

<details>
<summary>컴퓨터마다 다른 mysql을 어떻게 처리할까? 서버 버전을 맞추는 게 힘들다.</summary>
<div markdown="1">       

  </br>Docker를 사용하여 mysql container를 받아서 어느 컴퓨터에서 개발 가상환경을 받아서 실행한다.
  
  
</div>
</details>

<details>
<summary>git 활용 하는 방법</summary>
<div markdown="1">       

  </br>(https://techblog.woowahan.com/2553/)
  
  
</div>
</details>



</br>

### &#128526; About Us

### FrontEnd
김현수

### BackEnd
김기훈

### UI/UX
전탁준





  

    
