### DB 설정
globals.properties : postgresql 설정 수정
!!수정 부분
Globals.postgres.Url=jdbc:postgresql://121.133.55.190:25432/portal 
내부 아이피 192.168.0.253:5432/portal
외부 아이피 121.133.55.190:25432/portal




### Main  
```
WEB-INF/jsp/smartbeans/portal/main/main_index.jsp 메인 인덱스 경로
css/com/egovframework/main/ .css 헤더,푸터,컨텐츠 CSS 파일 경로
js/egovframework/com/main/main.js 메인 JS 경로

Java/smartbeans/portal/main/ .jsp 자바 경로
Java/smartbeans/portal/AdminnoticeBoard/  관리자 게시물 관리 JAVA
WEB-INF/jsp/smartbeans/portal/main/AdminnoticeBoard/ 관리자 게시물 관리 JSP 

```