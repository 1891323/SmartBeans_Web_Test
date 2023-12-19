package smartbeans.cmmn.web;

/**
 * 컴포넌트 설치 후 설치된 컴포넌트들을 IncludedInfo annotation을 통해 찾아낸 후
 * 화면에 표시할 정보를 처리하는 Controller 클래스
 * <Notice>
 * 		개발시 메뉴 구조가 잡히기 전에 배포파일들에 포함된 공통 컴포넌트들의 목록성 화면에
 * 		URL을 제공하여 개발자가 편하게 활용하도록 하기 위해 작성된 것으로,
 * 		실제 운영되는 시스템에서는 적용해서는 안 됨
 *      실 운영 시에는 삭제해서 배포해도 좋음
 * <Disclaimer>
 * 		운영시에 본 컨트롤을 사용하여 메뉴를 구성하는 경우 성능 문제를 일으키거나
 * 		사용자별 메뉴 구성에 오류를 발생할 수 있음
 * @author 공통컴포넌트 정진오
 * @since 2011.08.26
 * @version 2.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일		  수정자		수정내용
 *  ----------   --------   ---------------------------
 *  2011.08.26   정진오            최초 생성
 *  2011.09.16   서준식            컨텐츠 페이지 생성
 *  2011.09.26     이기하		header, footer 페이지 생성
 *  2019.12.04   신용호            KISA 보안코드 점검 : Map<Integer, IncludedCompInfoVO> map를 지역변수로 수정
 *  2020.07.08   신용호           비밀번호를 수정한후 경과한 날짜 조회
 *  2020.08.28   정진오            표준프레임워크 v3.10 개선
 * </pre>
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.cmmn.IncludedCompInfoVO;
import smartbeans.cmmn.annotation.IncludedInfo;
import smartbeans.portal.uat.uia.service.EgovLoginService;
import smartbeans.portal.user.farminfo.service.WeatherVO;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.IntStream;


@Controller
@PropertySource("classpath:config.properties")
public class EgovComIndexController {

	@Autowired
	private ApplicationContext applicationContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovComIndexController.class);

	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	private static final int[] HOUR = {2, 5, 8, 11, 14, 17, 20, 23}; // 단기예보 기준시간 (1일 8회)
	private static final String[] SKYDESC = {"", "맑음", "", "구름많음", "흐림"};
	private static final String[] PTYDESC = {"", "비", "비/눈", "눈", "소나기"};
	private static final String[] DAYSKYICON = {"", "clear-day", "", "cloudy", "partly-cloudy-day"};
	private static final String[] NIGHTSKYICON = {"", "clear-night", "", "cloudy", "partly-cloudy-night"};
	private static final String[] PTYICON = {"", "rain", "sleet", "snow", "hail"};
	private static final String[] DETAILPTYICON = {"", "rain", "sleet", "snow", "", "drizzle", "sleet", "snow"};

	@Value("${WEATHER_API_KEY}")
	private String WEATHER_API_KEY;

	@Value("${AT_API_KEY_VER2}")
	private String AT_API_KEY_VER2;

	public String dateFormat() {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		return simpleDateFormat.format(nowDate);
	}

	public String hourFormatforVilage() {
		LocalTime now = LocalTime.now();
		int[] filteredHour = IntStream.of(HOUR).filter(h -> h <= now.getHour()).toArray();
		int max = Arrays.stream(filteredHour).max().getAsInt();

		return String.format("%02d00", max);
	}

	public String hourFormatforUltraSrt() {
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		int min = now.getMinute();

		if (min < 45) hour -= 1;

		return String.format("%02d30", hour);
	}

	public JSONArray getVilageFcst(String API_kEY, String date, String time) throws IOException, ParseException {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + API_kEY); /*Service Key(Encoding)*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*날짜*/
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*시간*/
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("74", "UTF-8")); /*예보지점(괴산)의 X 좌표값*/
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("111", "UTF-8")); /*예보지점(괴산)의 Y 좌표값*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(sb.toString());
		JSONObject response = (JSONObject) obj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONArray item = (JSONArray) items.get("item");

		return item;
	}

	public JSONArray getUltraSrtFcst(String API_kEY, String pageNo, String date, String time) throws IOException, ParseException {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + API_kEY); /*Service Key(Encoding)*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("6", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*날짜*/
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*시간*/
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("74", "UTF-8")); /*예보지점(괴산)의 X 좌표값*/
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("111", "UTF-8")); /*예보지점(괴산)의 Y 좌표값*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(sb.toString());
		JSONObject response = (JSONObject) obj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONArray item = (JSONArray) items.get("item");

		return item;
	}

	public JSONArray getWhalPumSale(String API_kEY, String date) throws IOException, ParseException {
		StringBuilder urlBuilder = new StringBuilder("https://at.agromarket.kr/openApi/price/dateWhsalPumSale.do");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + API_kEY); /*서비스키*/
		urlBuilder.append("&" + URLEncoder.encode("apiType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*API유형*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		urlBuilder.append("&" + URLEncoder.encode("strDate","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*시작일자*/
		urlBuilder.append("&" + URLEncoder.encode("endDate","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*종료일자*/
		urlBuilder.append("&" + URLEncoder.encode("whsalCd","UTF-8") + "=" + URLEncoder.encode("110001", "UTF-8")); /*도매시장코드*/
		urlBuilder.append("&" + URLEncoder.encode("large","UTF-8") + "=" + URLEncoder.encode("03", "UTF-8")); /*부류코드*/
		urlBuilder.append("&" + URLEncoder.encode("mid","UTF-8") + "=" + URLEncoder.encode("01", "UTF-8")); /*품목코드*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(sb.toString());
		JSONArray item = (JSONArray) obj.get("data");

		return item;
	}

	private String getDate(int add) {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.DATE, add);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(cal.getTime());
	}

	@RequestMapping("/index.do")
	public String index(ModelMap model) throws IOException, ParseException {
		JSONArray item = getVilageFcst(WEATHER_API_KEY, dateFormat(), hourFormatforVilage());

		JSONObject tmp_obj = (JSONObject) item.get(0); // 기온
		String tmp = (String) tmp_obj.get("fcstValue");
		JSONObject pty_obj = (JSONObject) item.get(6); // 강수형태
		String pty = (String) pty_obj.get("fcstValue");
		JSONObject sky_obj = (JSONObject) item.get(5); // 하늘상태
		String sky = (String) sky_obj.get("fcstValue");

		String weather = "";
		String img = "";
		if (Objects.equals(pty, "0")) {
			weather = SKYDESC[Integer.parseInt(sky)];
			LocalTime now = LocalTime.now();
			int hour = now.getHour();
			if (hour >= 18)
				img = NIGHTSKYICON[Integer.parseInt(sky)];
			else
				img = DAYSKYICON[Integer.parseInt(sky)];
		}
		else {
			weather = PTYDESC[Integer.parseInt(pty)];
			img = PTYICON[Integer.parseInt(pty)];
		}

		JSONObject pop_obj = (JSONObject) item.get(7); // 강수확률
		String pop = (String) pop_obj.get("fcstValue");
		JSONObject reh_obj = (JSONObject) item.get(10); // 습도
		String reh = (String) reh_obj.get("fcstValue");
		JSONObject wsd_obj = (JSONObject) item.get(4); // 풍속
		String wsd = (String) wsd_obj.get("fcstValue");

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); // 년
		int month = cal.get(Calendar.MONTH) + 1; // 월
		int date = cal.get(Calendar.DATE); // 일
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 요일
		String week = "";
		switch (dayOfWeek) {
			case 1:
				week = "일";
				break;
			case 2:
				week = "월";
				break;
			case 3:
				week = "화";
				break;
			case 4:
				week = "수";
				break;
			case 5:
				week = "목";
				break;
			case 6:
				week = "금";
				break;
			case 7:
				week = "토";
				break;
		}
		String today = year + "년 " + month + "월 " + date + "일 " + week + "요일";

		WeatherVO weatherVO = new WeatherVO(weather, tmp, pop, reh, wsd, today, img);
		model.addAttribute("weatherData", weatherVO);

		JSONArray tmp_arr = getUltraSrtFcst(WEATHER_API_KEY, "5", dateFormat(), hourFormatforUltraSrt()); // 기온
		JSONArray sky_arr = getUltraSrtFcst(WEATHER_API_KEY, "4", dateFormat(), hourFormatforUltraSrt()); // 하늘상태
		JSONArray pty_arr = getUltraSrtFcst(WEATHER_API_KEY, "2", dateFormat(), hourFormatforUltraSrt()); // 강수형태

		List<Map<String, String>> imgList = new ArrayList<>();
		for (int i=0; i<pty_arr.size(); i++) {
			Map<String, String> imgMap = new HashMap<>();

			JSONObject ptyJson = (JSONObject) pty_arr.get(i);
			String ptyStr = (String) ptyJson.get("fcstValue");

			if (Objects.equals(ptyStr, "0")) {
				JSONObject skyJson = (JSONObject) sky_arr.get(i);
				String skyStr = (String) skyJson.get("fcstValue");
				String timeStr = (String) skyJson.get("fcstTime");
				int hour = Integer.parseInt(timeStr.substring(0, 2));
				if (hour >= 18)
					imgMap.put("img", NIGHTSKYICON[Integer.parseInt(skyStr)]);
				else
					imgMap.put("img", DAYSKYICON[Integer.parseInt(skyStr)]);
			} else {
				imgMap.put("img", DETAILPTYICON[Integer.parseInt(ptyStr)]);
			}
			imgList.add(imgMap);
		}

		JSONArray wsd_arr = getUltraSrtFcst(WEATHER_API_KEY, "10", dateFormat(), hourFormatforUltraSrt()); // 풍속

		model.addAttribute("tmp", tmp_arr);
		model.addAttribute("imgList", imgList);
		model.addAttribute("wsd", wsd_arr);

		int addNum = -1;
		JSONArray priceItem1 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		if (priceItem1.isEmpty()) {
			addNum -= 1;
			priceItem1 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		}
		JSONObject priceObj = (JSONObject) priceItem1.get(0);
		int totqty = Integer.parseInt(priceObj.get("totqty").toString());
		int totamt = Integer.parseInt(priceObj.get("totamt").toString());
		int avebfr1 = totamt / totqty;
		model.addAttribute("avebfr1", avebfr1);
		model.addAttribute("amt1", totqty);

		addNum -= 1;
		JSONArray priceItem2 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		if (priceItem2.isEmpty()) {
			addNum -= 1;
			priceItem2 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		}
		priceObj = (JSONObject) priceItem2.get(0);
		totqty = Integer.parseInt(priceObj.get("totqty").toString());
		totamt = Integer.parseInt(priceObj.get("totamt").toString());
		int avebfr2 = totamt / totqty;
		model.addAttribute("avebfr2", avebfr2);
		model.addAttribute("amt2", totqty);

		addNum -= 1;
		JSONArray priceItem3 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		if (priceItem3.isEmpty()) {
			addNum -= 1;
			priceItem3 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		}
		priceObj = (JSONObject) priceItem3.get(0);
		totqty = Integer.parseInt(priceObj.get("totqty").toString());
		totamt = Integer.parseInt(priceObj.get("totamt").toString());
		int avebfr3 = totamt / totqty;
		model.addAttribute("avebfr3", avebfr3);
		model.addAttribute("amt3", totqty);

		addNum -= 1;
		JSONArray priceItem4 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		if (priceItem4.isEmpty()) {
			addNum -= 1;
			priceItem4 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		}
		priceObj = (JSONObject) priceItem4.get(0);
		totqty = Integer.parseInt(priceObj.get("totqty").toString());
		totamt = Integer.parseInt(priceObj.get("totamt").toString());
		int avebfr4 = totamt / totqty;
		model.addAttribute("avebfr4", avebfr4);
		model.addAttribute("amt4", totqty);

		addNum -= 1;
		JSONArray priceItem5 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		if (priceItem5.isEmpty()) {
			addNum -= 1;
			priceItem5 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		}
		priceObj = (JSONObject) priceItem5.get(0);
		totqty = Integer.parseInt(priceObj.get("totqty").toString());
		totamt = Integer.parseInt(priceObj.get("totamt").toString());
		int avebfr5 = totamt / totqty;
		model.addAttribute("avebfr5", avebfr5);
		model.addAttribute("amt5", totqty);

		addNum -= 1;
		JSONArray priceItem6 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		if (priceItem6.isEmpty()) {
			addNum -= 1;
			priceItem6 = getWhalPumSale(AT_API_KEY_VER2, getDate(addNum));
		}
		priceObj = (JSONObject) priceItem6.get(0);
		totqty = Integer.parseInt(priceObj.get("totqty").toString());
		totamt = Integer.parseInt(priceObj.get("totamt").toString());
		int avebfr6 = totamt / totqty;
		model.addAttribute("avebfr6", avebfr6);
		model.addAttribute("amt6", totqty);

		return "main/main.index";
	}

	@RequestMapping("/header.do")
	public String header() {
		return "main/header";
	}
	@RequestMapping("/footer.do")
	public String bottom() {
		return "main/footer";
	}

	@RequestMapping("/content.do")
	public String setContent(ModelMap model) throws Exception {
		return "main/content";
	}



	@RequestMapping("/EgovLeft.do")
	public String setLeftMenu(ModelMap model) {

		Map<Integer, IncludedCompInfoVO> map = new TreeMap<Integer, IncludedCompInfoVO>();
		RequestMapping rmAnnotation;
		IncludedInfo annotation;
		IncludedCompInfoVO zooVO;

		/*
		 * EgovLoginController가 AOP Proxy되는 바람에 클래스를 reflection으로 가져올 수 없음
		 */
		try {
			Class<?> loginController = Class.forName("egovframework.com.uat.uia.web.EgovLoginController");
			Method[] methods = loginController.getMethods();
			for (int i = 0; i < methods.length; i++) {
				annotation = methods[i].getAnnotation(IncludedInfo.class);

				if (annotation != null) {
					LOGGER.debug("Found @IncludedInfo Method : {}", methods[i]);
					zooVO = new IncludedCompInfoVO();
					zooVO.setName(annotation.name());
					zooVO.setOrder(annotation.order());
					zooVO.setGid(annotation.gid());

					rmAnnotation = methods[i].getAnnotation(RequestMapping.class);
					if ("".equals(annotation.listUrl()) && rmAnnotation != null) {
						zooVO.setListUrl(rmAnnotation.value()[0]);
					} else {
						zooVO.setListUrl(annotation.listUrl());
					}
					map.put(zooVO.getOrder(), zooVO);
				}
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error("No egovframework.com.uat.uia.web.EgovLoginController!!");
		}
		/* 여기까지 AOP Proxy로 인한 코드 */

		/*@Controller Annotation 처리된 클래스를 모두 찾는다.*/
		Map<String, Object> myZoos = applicationContext.getBeansWithAnnotation(Controller.class);
		LOGGER.debug("How many Controllers : ", myZoos.size());
		for (final Object myZoo : myZoos.values()) {
			Class<? extends Object> zooClass = myZoo.getClass();

			Method[] methods = zooClass.getMethods();
			LOGGER.debug("Controller Detected {}", zooClass);
			for (int i = 0; i < methods.length; i++) {
				annotation = methods[i].getAnnotation(IncludedInfo.class);

				if (annotation != null) {
					//LOG.debug("Found @IncludedInfo Method : " + methods[i] );
					zooVO = new IncludedCompInfoVO();
					zooVO.setName(annotation.name());
					zooVO.setOrder(annotation.order());
					zooVO.setGid(annotation.gid());
					/*
					 * 목록형 조회를 위한 url 매핑은 @IncludedInfo나 @RequestMapping에서 가져온다
					 */
					rmAnnotation = methods[i].getAnnotation(RequestMapping.class);
					if ("".equals(annotation.listUrl())) {
						zooVO.setListUrl(rmAnnotation.value()[0]);
					} else {
						zooVO.setListUrl(annotation.listUrl());
					}

					map.put(zooVO.getOrder(), zooVO);
				}
			}
		}

		model.addAttribute("resultList", map.values());

		LOGGER.debug("EgovComIndexController index is called ");

		return "cmm/EgovUnitLeft";
	}

	// context-security.xml 설정
	// csrf="true"인 경우 csrf Token이 없는경우 이동하는 페이지
	// csrfAccessDeniedUrl="/egovCSRFAccessDenied.do"
	@RequestMapping("/egovCSRFAccessDenied.do")
	public String egovCSRFAccessDenied() {
		return "cmm/error/csrfAccessDenied";
	}
}
