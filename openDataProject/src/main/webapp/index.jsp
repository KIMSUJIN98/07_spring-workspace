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
			$("#btn1").click(function(){
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
			})
		})
	</script>
	
	
	
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
	
</body>
</html>