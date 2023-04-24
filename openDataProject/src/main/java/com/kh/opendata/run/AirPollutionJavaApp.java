package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

public class AirPollutionJavaApp {
	
	// 발급받은 인증키 정보 변수에 담아두기
	public static final String servicekey = "9fVCTFes9PHIJUbJs5tpoq0ASuiR09ZaQ%2BbLG3Kiw2c7k4ltx6YfS0W7sGmkd50Bb34eWV4pTF%2ByEOsrnAoOVQ%3D%3D"; 															// 절대 변하지 않으므로 상수로 선언함

	
	public static void main(String[] args) throws IOException {

		// OpenAPI 서버로 요청하고자 하는 URL 만들기
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
	    // url += "?servicekey=서비스키"; SERVICE_KEY_IS_NOT_REGISTERED_ERROR : 서비스 키가 제대로 부여되지 않았을 경우
	    url += "?servicekey=" + servicekey;
		
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8"); // 요청시 전달값 중 한글이 있을 경우 => encoding 해야됨
	    //url += "&returnType=xml";
	    url += "&returnType=json";
	    
	    //System.out.println(url);
	    
	    // ** HttpURLConnection 객체를 활용해서 OpenAPI 요청절차 **
	    
	    // 1. 요청하고자 하는 url 전달하면서 java.net.URL 객체 생성
	    URL requestUrl = new URL(url);
	    
	    // 2. 1번 과정으로 생성된 URL 객체 가지고 HttpURLConnection 객체 생성
	    HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
	    
	    // 3. 요청에 필요한 Header 설정하기
	    urlConnection.setRequestMethod("GET");
	    
	    // 4. 해당 OpenAPI 서버로 요청 보낸 후 입력 스트림을 통해 응답데이터 읽어들이기
	    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); 		// BufferedReader"문자기반"보조스트림(urlConnection.getInputStream()"바이트기반"기반스트림) => "문자기반" 보조스트림으로 형변환 필요하므로, new InputStreamReader(urlConnection.getInputStream())
	    // 한줄단위로 읽을 수 있는 보조스트림 br => 기반스트림 필요
	    // 인풋스트림 => 바이트 스트림(1바이트씩 읽음) // 리더스트림 => 문자스트림(2바이트씩 읽음..) 달라서 바꿔줘야함
	    
	    String line;
	    String responseText = "";
	    
	    while((line = br.readLine())!= null) { 																// 한줄단위로 읽는 메소드 readLine() => 한줄단위로 읽었을때 null이 아닌 경우
	    	//System.out.println(line);
	    	responseText += line;
	    }
	    
	    //System.out.println(responseText);
	    
	    /*
         * 개행이 안 들어가서 그렇지 이런 느낌입니다!
         * {
         *      "response":
         *          {
         *              "body":
         *                  {
         *                      "totalCount":40,
         *                      "items":
         *                          [
         *                              {
         *                                  "so2Grade":"1",
         *                                  "coFlag":null,
         *                                  "khaiValue":"92",
         *                                  "so2Value":"0.003",
         *                                  "coValue":"0.4",
         *                                  "pm10Flag":null,
         *                                  "o3Grade":"2",
         *                                  "pm10Value":"25",
         *                                  "khaiGrade":"2",
         *                                  "sidoName":"서울",
         *                                  "no2Flag":null,
         *                                  "no2Grade":"1",
         *                                  "o3Flag":null,
         *                                  "so2Flag":null,
         *                                  "dataTime":"2023-04-09 14:00",
         *                                  "coGrade":"1",
         *                                  "no2Value":"0.011",
         *                                  "stationName":"중구",
         *                                  "pm10Grade":"1",
         *                                  "o3Value":"0.080"
         *                              },
         *                              {
         *                                  "so2Grade":"1",
         *                                  "coFlag":null,
         *                                  "khaiValue":"67",
         *                                  "so2Value":"0.003",
         *                                  "coValue":"0.4",
         *                                  "pm10Flag":null,
         *                              ...
         *                              }
         *                          ],
         *                      "pageNo":1,
         *                      "numOfRows":10
         *                  },
         *              "header":
         *                  {
         *                      "resultMsg":"NORMAL_CODE",
         *                      "resultCode":"00"
         *                  }
         *          }
         *  }
         */
	    
	    // JsonObject, JsonArray, JsonElement 이용해서 파싱 할 수 있음 (gson lib에서 제공) 							// JSONObject, JSONArray (json lib 제공)
	    // 각각의 item 정보를 => AirVO 객체에 담고 => ArrayList에 차곡차곡 쌓기
	    // 우리가 일반적으로 썼던거는 json 라이브러리 .. 이건 gson
	    
	    // 우선은 파싱하려면 jsonObject 형으로 만들어야함
	    JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
	    JsonObject responseObj = totalObj.getAsJsonObject("response"); // response 속성 접근 => {} jsonObject 리턴함
	    
	    //System.out.println(responseObj);
	    
	    JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body 속성 접근 => {}
	    //System.out.println(bodyObj);
	    
	    int totalCount = bodyObj.get("totalCount").getAsInt(); // totalCount 속성 접근 => 40 int 				// get()은 json 형태로 반환하므로 반환형을 int로 맞춰주기 위한 getAsInt() 메소드 필요
	    
	    //System.out.println(totalCount);
	    
	    JsonArray itemArr = bodyObj.getAsJsonArray("items");
	    
	    //System.out.println(itemArr);
	    
	    ArrayList<AirVO> list = new ArrayList<>();
	    for(int i=0; i<itemArr.size(); i++) {
	    	JsonObject item = itemArr.get(i).getAsJsonObject();												// getAsJsonObject() json 객체 형태로 반환하는 메소드
	    	//System.out.println(item);
	    	
	    	AirVO air = new AirVO();
	    	air.setStationName(item.get("stationName").getAsString());
	    	air.setDataTime(item.get("dataTime").getAsString());
	    	air.setKhaiValue(item.get("khaiValue").getAsString());
	    	air.setPm10Value(item.get("pm10Value").getAsString());
	    	air.setSo2Value(item.get("so2Value").getAsString());
	    	air.setCoValue(item.get("coValue").getAsString());
	    	air.setNo2Value(item.get("no2Value").getAsString());
	    	air.setO3Value(item.get("o3Value").getAsString());
	    	
	    	list.add(air);
	    }
	    
	    //System.out.println(list);
	    for(AirVO a: list) {
	    	System.out.println(a);
	    }
	    
	    // 5. 다 사용한 스트림 반납
	    br.close();
	    urlConnection.disconnect();
	    
	}

}

