# Lv2 과제

## 요구사항
( 23.06.29 ~ 23.07.05 )</br>
## 1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기

## 2. 로그인 API
    - username, password를 Client에서 전달받기
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
    발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

## 1. 전체 게시글 목록 조회 API
    - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
## 2. 게시글 작성 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
    - 제목, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기(username은 로그인 된 사용자)
## 3. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기 
    (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
## 4. 선택한 게시글 수정 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
    - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
## 5. 선택한 게시글 삭제 API  
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기

## API 명세서
<td><img width="1000" src="https://raw.githubusercontent.com/yunji-nam/springblog/eb6179667c0f9943f5574adc0fe9ee302f797fbc/API%EB%AA%85%EC%84%B8%EC%84%9C.png" /></td>

__[API 명세서 링크입니다.](https://www.notion.so/Lv-2-API-592480fe75a94f4a80a4a483bfa7f992)__

## ERD
<td><img width="1000" src="https://raw.githubusercontent.com/yunji-nam/springblog/main/ERD.png" /></td>

__[erdcloud 링크입니다.](https://www.erdcloud.com/d/E439JmEud2KoRnbTt)__

## 팀원 소개 

<table align=center>
    <thead>
        <tr >
            <th style="text-align:center;" >박정환</th>
            <th style="text-align:center;" >남윤지</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><img width="200" src="https://velog.velcdn.com/images/titaniumdiana/post/e372b592-15e0-4aab-a4b8-23e82c7c8f72/%E1%84%89%E1%85%B3%E1%86%AF%E1%84%91%E1%85%B3%E1%86%AB%E1%84%85%E1%85%AE%E1%84%91%E1%85%B5.png" /> </td>
            <td><img width="200" src="https://i.namu.wiki/i/1JmqYp_0WB1AwrMRlzys7r0zJCSIczMAljiXHACTU1vCREGovI97RKMu4SW_NTjGYEbV_2Nm3X3LX9x5OzPfGzf-nyByAt9nI4dQsdC_28hbDl5STImgQCjUXtwU1YtFVpxn4ewTmsImhpkJ3mIQGQ.webp" /></td>
        </tr>
        <tr>
            <td><a href="https://github.com/Junghwan1106">@ToToRo_1106</a></td>
            <td><a href="https://github.com/HGive">@yunji-nam</a></td>
        </tr>
        <tr>
            <td width="200">카페인이 필요해요...</td>
            <td width="200">잠온다...</td>
        </tr>
    </tbody>
</table>
