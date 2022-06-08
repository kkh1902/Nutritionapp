


# 먹보 - Frontend
### 





# 먹보 - Backend

### &#10024; 먹보 주요 기능 &#10024;
#### 1. 사용자의 음식 데이터를 통해 필요한 영양분에 맞는 음식 추천 (Algorithm) 구축
![image](https://user-images.githubusercontent.com/91061890/172547686-f17daff9-fd98-4e73-a158-e09fc579df59.png)
<br>한국 영양 학회에 따른 BMR공식(사용자의 스펙으로부터) 계산 하여 일일 탄수화물, 단백질, 지방 추천 및 부족한 영양소 확인

#### 2.약 2만여개의 음식 데이터(식약처 제공 데이터)를 2번의 데이터 가공을 통해 Data Base 구축
![image](https://user-images.githubusercontent.com/91061890/172547193-27c289df-8927-4798-a699-8af654cc5cbf.png)

#### 3.사용자 스펙 조회,기록,수정,삭제 연산 가능
mysql 이미지(user)

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



### ⚙Node & npm Version
node: v14.17.1  
npm: 6.14.13  

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

  </br>첫번째로 Database를 정규화해서 받는다.
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


### &#128526; About Us

### FrontEnd
김현수

### BackEnd
김기훈

### UI/UX
전탁준





  

    
