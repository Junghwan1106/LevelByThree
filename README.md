# Lv3 과제

## 요구사항
( 23.07.06 ~ 23.07.12 )</br>
## 1. 회원 가입 API
    - 회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능

## 2. 로그인 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능
    - 선택한 게시글의 DB 저장 유무를 확인하기
    - 선택한 게시글이 있다면 댓글을 등록하고 등록된 댓글 반환하기

## 1. 댓글 작성 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능
    - 선택한 게시글의 DB 저장 유무를 확인하기
    - 선택한 게시글이 있다면 댓글을 등록하고 등록된 댓글 반환하기
## 2. 댓글 수정 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
## 3. 댓글 삭제 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
## 4. 예외 처리
    - 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    - 로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기
 
 ## 5. 전체 게시글 목록 조회 API
     - 각각의 게시글에 등록된 모든 댓글을 게시글과 같이 Client에 반환하기
     - 댓글은 작성 날짜 기준 내림차순으로 정렬하기
 ## 6. 선택한 게시글 조회 API
    - 선택한 게시글에 등록된 모든 댓글을 선택한 게시글과 같이 Client에 반환하기
    - 댓글은 작성 날짜 기준 내림차순으로 정렬하기

## 팀원 소개 

<table align=center>
    <thead>
        <tr >
            <th style="text-align:center;" >박정환</th>
            <th style="text-align:center;" >이명현</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><img width="200" src="https://velog.velcdn.com/images/titaniumdiana/post/e372b592-15e0-4aab-a4b8-23e82c7c8f72/%E1%84%89%E1%85%B3%E1%86%AF%E1%84%91%E1%85%B3%E1%86%AB%E1%84%85%E1%85%AE%E1%84%91%E1%85%B5.png" /> </td>
            <td><img width="200" src="https://i.namu.wiki/i/1JmqYp_0WB1AwrMRlzys7r0zJCSIczMAljiXHACTU1vCREGovI97RKMu4SW_NTjGYEbV_2Nm3X3LX9x5OzPfGzf-nyByAt9nI4dQsdC_28hbDl5STImgQCjUXtwU1YtFVpxn4ewTmsImhpkJ3mIQGQ.webp" /></td>
        </tr>
        <tr>
            <td><a href="https://github.com/Junghwan1106">@ToToRo_1106</a></td>
            <td><a href="https://github.com/HGive">@KorMH</a></td>
        </tr>
        <tr>
            <td width="200">카페인 못 마셔서 타우린 마시기</td>
            <td width="200">오늘도 내일도 코딩코딩 베이베</td>
        </tr>
    </tbody>
</table>

## ERD
<td><img width="1000" src="https://raw.githubusercontent.com/Junghwan1106/LevelByThree/main/Lv3ERD.png" /></td>

__[erdcloud 링크입니다.](https://www.erdcloud.com/d/dwzDi974sCp4cWA9Q)__

## API 명세서
__[API 명세서 링크입니다.](https://scandalous-uncle-516.notion.site/8541bce262c94b84ae8f67a510a210e7?v=694f7bd8842a46c498ca994ce738f53f&pvs=4)__

<td><img width="1000" src="https://raw.githubusercontent.com/Junghwan1106/LevelByThree/main/LV3API%EB%AA%85%EC%84%B8%EC%84%9C.png" /></td>


