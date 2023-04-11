package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	
	// 발급받은 인증키 정보 변수에 담아두기
	public static final String servicekey = "9fVCTFes9PHIJUbJs5tpoq0ASuiR09ZaQ%2BbLG3Kiw2c7k4ltx6YfS0W7sGmkd50Bb34eWV4pTF%2ByEOsrnAoOVQ%3D%3D";
	
	/* json 형식으로 응답할때의 메소드
	@ResponseBody
	@RequestMapping(value="air.do", produces="application/json; charset=utf-8")
	public String airPollution(String location) throws IOException {
		
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + servicekey;
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
		url += "&returnType=json";
		url += "&numOfRows=50";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line;
		String responseText = "";
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		//System.out.println(responseText);
		return responseText;
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="air.do", produces="text/xml; charset=utf-8")
	public String airPollution(String location) throws IOException {
		
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + servicekey;
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
		url += "&returnType=xml";
		url += "&numOfRows=50";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line;
		String responseText = "";
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		//System.out.println(responseText);
		return responseText;
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="family.do", produces="application/json; charset=utf-8")
	public String familyCard(String cpHgu) throws IOException {
		String url = "http://apis.data.go.kr/6260000/BusanFmlyLvcrInfoService/getFmlyLvcrInfo";
		url += "?serviceKey=" + servicekey;
		url += "&pageNo=1";
		url += "&numOfRows=10";
		url += "&resultType=json";
		url += "&cpHgu=" + URLEncoder.encode(cpHgu, "UTF-8");
		url += "&cpClass=" + URLEncoder.encode("어린이집", "UTF-8");
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line;
		String responseText = "";
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="disaster.do", produces="text/xml; charset=utf-8")
	public String disasterShelter() throws IOException { 	// 부모 타입인 IOException으로 예외처리
		
		String url = "https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
		url += "?serviceKey=" + servicekey;
		url += "&type=xml";
		url += "&pageNo=1";
		url += "&numOfRows=20";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line;
		String requestText = "";
		
		while((line = br.readLine()) != null) {
			requestText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return requestText;
	}

	
}
