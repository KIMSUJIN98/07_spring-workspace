<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h2>실시간 대기 오염 정보</h2>
	
	지역 :
	<select id="location">
		<option>서울</option>
		<option>부산</option>
		<option>대전</option>
	</select>
	<button id="btn1">해당 지역 대기오염정보 확인하기</button>
	
	<table id="result1" border="1" align="center">
		<thead>
			<tr>
				<th>측정소명</th>
				<th>측정일시</th>
				<th>통합대기환경수치</th>
				<th>미세먼지농도</th>
				<th>아황산가스농도</th>
				<th>일산화탄소농도</th>
				<th>이산화질소농도</th>
				<th>오존농도</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	<script>
		$(function(){
			$("#btn1").click(function(){
				
				/* json 형식으로 응답받을때의 예시
				$.ajax({
					url:"air.do",
					data:{location:$("#location").val()},
					success:function(data){
						//console.log(data);
						//console.log(data.response.body.items);
						
						const itemArr = data.response.body.items;	// 고정값이므로 const로 선언함
						
						let value = "";
						for(let i in itemArr){						// for문이 돌아가면서 값이 바뀔 여지가 있으므로 let으로 선언함
							//console.log(itemArr[i]); // {}
							let item = itemArr[i];					// for문이 돌아가면서 값이 바뀔 여지가 있으므로 let으로 선언함
							
							value += "<tr>"
								+ "<td>" + item.stationName + "</td>"
								+ "<td>" + item.dataTime + "</td>"
								+ "<td>" + item.khaiValue + "</td>"
								+ "<td>" + item.pm10Value + "</td>"
								+ "<td>" + item.so2Value + "</td>"
								+ "<td>" + item.coValue + "</td>"
								+ "<td>" + item.no2Value + "</td>"
								+ "<td>" + item.o3Value + "</td>"
								+ "</tr>";
						
						}
						
						$("#result1 tbody").html(value);
						
					}, error:function(){
						console.log("ajax 통신 실패!");
					}
				});
				*/
				
				
				// xml 형식으로 응답데이터를 받을 때
				$.ajax({
					url:"air.do",
					data:{location:$("#location").val()},
					success:function(data){
						console.log(data);
						// jQuery 탐색 메소드
						// find 메소드 : 기준이 되는 요소의 하위 요소들 중 특정 요소를 찾을 때 사용 (html, xml 다 사용 가능)
						// console.log(data.find("item")); // find는 제이쿼리 메소드! 제이쿼리화 시켜야함
						//console.log($(data).find("item"));
						
						// xml 형식의 응답데이터를 받았을 때
						// 1. 응답데이터 안에 실제 데이터가 담겨있는 요소 선택
						
						let itemArr = $(data).find("item");
						
						// 2. 반복문을 통해 실제 데이터가 담긴 요소들에 접근해서 동적으로 요소 만들기
						let value = "";
						itemArr.each(function(i, item){ // i에는 인덱스값, item은 순차적으로 접근한 요소
							//console.log(item);
							//console.log($(item).find("stationName")); // <station>중구</station> 			// 요소 자체를 선택함
							//console.log($(item).find("stationName").text()); 								// 중구
							
							value += "<tr>"
									+ "<td>" + $(item).find("stationName").text() + "</td>"
									+ "<td>" + $(item).find("dataTime").text() + "</td>"
									+ "<td>" + $(item).find("khaiValue").text() + "</td>"
									+ "<td>" + $(item).find("pm10Value").text() + "</td>"
									+ "<td>" + $(item).find("coValue").text() + "</td>"
									+ "<td>" + $(item).find("no2Value").text() + "</td>"
									+ "<td>" + $(item).find("so2Value").text() + "</td>"
									+ "<td>" + $(item).find("o3Value").text() + "</td>"
									+ "</tr>";
									
						})
						
						// 3. 동적으로 만들어낸 요소를 화면에 출력
						$("#result1 tbody").html(value);
						
						
					}, error:function(){
						console.log("ajax 통신 실패!");
					}
				});
			})
		})
	</script>
	
	
	<h2>부산광역시 가족사랑카드참여업체 현황 조회</h2>
	
	지역 :
	<select id="cpHgu">
		<option>부산진구</option>
		<option>서구</option>
		<option>금정구</option>
		<option>사하구</option>
	</select>
	<button id="btn2">현황조회</button>
	
	<table id="result2" border="1" align="center">
		<thead>
			<tr>
				<th>참여업체명</th>
				<th>대표자명</th>
				<th>사업자번호</th>
				<th>시행일</th>
				<th>주소</th>
				<th>연락처</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	
	<script>
		$(function(){
			$("#btn2").click(function(){
				$.ajax({
					url:"family.do",
					data:{cpHgu:$("#cpHgu").val()},
					success:function(data){
						//console.log(data);
						//console.log(data.response.body.items);
						
						const itemArr = data.response.body.items;
						
						let value = "";
						for(let i in itemArr){
							//console.log(itemArr[i]);
							let item = itemArr[i];
							
							value += "<tr>"
								+ "<td>" + item.cpCompname + "</td>"
								+ "<td>" + item.cpCeoname + "</td>"
								+ "<td>" + item.cpSanum + "</td>"
								+ "<td>" + item.cpSidate + "</td>"
								+ "<td>" + item.cpAddr + "</td>"
								+ "<td>" + item.cpTel + "</td>"
								+ "</tr>";
						
						}
						
						$("#result2 tbody").html(value);
						
					}, error:function(){
						console.log("ajax 통신 실패!");
					}
				});
			})
		})
	</script>
	
	
	
	
	
	
	
	
	
	
	<hr> <!-- 201 라인 -->
	
	<h2>지진해일대피소 정보</h2>
	<input type="button" value="실행" id="btn3">
	
	<div id="result3"></div>
	
	<script>
		$(function(){
			
			
			/*
			$("#btn3").click(function(){
				
				$.ajax({
					url:"disaster.do",	// 넘길 data가 없으므로 생략함
					success:function(data){
						//console.log(data);
						//console.log($(data).find("row"));
						
						// jquery 방식으로 동적으로 테이블 요소 만들기!
						
						let $table = $("<table border='1'></table>");
						let $thead = $("<thead></thead>");
						let headTr = "<tr>"
									+ "<th>일련번호</th>"
									+ "<th>시도명</th>"
									+ "<th>시군구명</th>"
									+ "<th>대피장소명</th>"
									+ "<th>주소</th>"
									+ "<th>수용가능인원(명)</th>"
									+ "<th>해변으로부터거리</th>"
									+ "<th>대피소분류명</th>"
									+ "</tr>";
						
						// 결합작업
						$thead.html(headTr);
						
						let $tbody = $("<tbody></tbody>");
						let bodyTr = "";
						
						$(data).find("row").each(function(i, row){ // i는 인덱스값, row는 순차적으로 접근한 요소
							//console.log(row);
							//console.log($(row).find("shel_nm").text()); // <shel_nm>어쩌구</shel_nm>
							
							bodyTr += "<tr>"
									+ "<td>" + $(row).find("id").text() + "</td>"
									+ "<td>" + $(row).find("sido_name").text() + "</td>"
									+ "<td>" + $(row).find("sigungu_name").text() + "</td>"
									+ "<td>" + $(row).find("shel_nm").text() + "</td>"
									+ "<td>" + $(row).find("address").text() + "</td>"
									+ "<td>" + $(row).find("shel_av").text() + "</td>"
									+ "<td>" + $(row).find("lenth").text() + "</td>"
									+ "<td>" + $(row).find("shel_div_type").text() + "</td>"
									+ "</tr>";
						})
						
						// 결합작업
						$tbody.html(bodyTr);
						
						// jquery 방식으로 만들어진 요소를 자식으로 추가할때는 이렇게 해야함
						// html() 메소드는 안에 스트링을 넣어야함
						
						//$table.append($thead, $tbody); 	// a.append(b) => a안에 b를 추가하겠다.
						//$table.appendTo("#result3"); 	// a.appendTo(b) => b안에 a를 추가하겠다.
						
						$table.append($thead, $tbody)
							  .appendTo("#result3");
						
						
						
						
						
					}, error:function(){
						console.log("ajax 통신 실패!");
					}
				});
			})
			*/
			
			$("#btn3").click(() => {
				
				$.ajax({
					url:"disaster.do",	// 넘길 data가 없으므로 생략함
					success:data => {
						//console.log(data);
						//console.log($(data).find("row"));
						
						// jquery 방식으로 동적으로 테이블 요소 만들기!
						
						let $table = $("<table border='1'></table>");
						let $thead = $("<thead></thead>");
						let headTr = "<tr>"
									+ "<th>일련번호</th>"
									+ "<th>시도명</th>"
									+ "<th>시군구명</th>"
									+ "<th>대피장소명</th>"
									+ "<th>주소</th>"
									+ "<th>수용가능인원(명)</th>"
									+ "<th>해변으로부터거리</th>"
									+ "<th>대피소분류명</th>"
									+ "</tr>";
						
						// 결합작업
						$thead.html(headTr);
						
						let $tbody = $("<tbody></tbody>");
						let bodyTr = "";
						
						$(data).find("row").each((i, row) => { // i는 인덱스값, row는 순차적으로 접근한 요소
							//console.log(row);
							//console.log($(row).find("shel_nm").text()); // <shel_nm>어쩌구</shel_nm>
							
							bodyTr += "<tr>"
									+ "<td>" + $(row).find("id").text() + "</td>"
									+ "<td>" + $(row).find("sido_name").text() + "</td>"
									+ "<td>" + $(row).find("sigungu_name").text() + "</td>"
									+ "<td>" + $(row).find("shel_nm").text() + "</td>"
									+ "<td>" + $(row).find("address").text() + "</td>"
									+ "<td>" + $(row).find("shel_av").text() + "</td>"
									+ "<td>" + $(row).find("lenth").text() + "</td>"
									+ "<td>" + $(row).find("shel_div_type").text() + "</td>"
									+ "</tr>";
						})
						
						// 결합작업
						$tbody.html(bodyTr);
						
						// jquery 방식으로 만들어진 요소를 자식으로 추가할때는 이렇게 해야함
						// html() 메소드는 안에 스트링을 넣어야함
						
						//$table.append($thead, $tbody); 	// a.append(b) => a안에 b를 추가하겠다.
						//$table.appendTo("#result3"); 	// a.appendTo(b) => b안에 a를 추가하겠다.
						
						$table.append($thead, $tbody)
							  .appendTo("#result3");
						
						
						
						
						
					}, error:() => {
						console.log("ajax 통신 실패!");
					}
				});
			})
		})
		
		/*
			** 화살표 함수 **
			익명함수들을 화살표 함수로 작성 할 수 있음
			
			"function() {}" 를 				"() => {}" 이런식으로 작성가능
			
			"function(data) {}" 를 			"data => {}" 이런식으로 작성가능 (매개변수 한개)
			
			"function(a, b) {}" 를 			"(a,b) => {}" 이런식으로 작성가능 (매개변수 여러개)
			
			"function() {return 10;}" 를 	"() => 10" 이런식으로 작성가능
			
			
			
		
		*/
		
		
	</script>
	
	
	
	
	
</body>
</html>