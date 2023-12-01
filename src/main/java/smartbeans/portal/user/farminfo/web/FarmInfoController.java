package smartbeans.portal.user.farminfo.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.portal.user.farminfo.service.WeatherVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user/farminfo")
public class FarmInfoController {
    private static final int[] HOUR = {2, 5, 8, 11, 14, 17, 20, 23}; // 단기예보 기준시간 (1일 8회)
    private static final String[] HOUR_TMRW = {"22", "19", "16", "13", "10", "7", "4", "1"}; // 내일
    private static final String[] HOUR_AFTER_TMRW = {"46", "43", "40", "37", "34", "31", "28", "25"}; // 모레
    private static final String[] SKYDESC = {"", "맑음", "", "구름많음", "흐림"};
    private static final String[] PTYDESC = {"", "비", "비/눈", "눈", "소나기"};
    private static final String[] DAYSKYICON = {"", "clear-day", "", "cloudy", "partly-cloudy-day"};
    private static final String[] NIGHTSKYICON = {"", "clear-night", "", "cloudy", "partly-cloudy-night"};
    private static final String[] PTYICON = {"", "rain", "sleet", "snow", "hail"};

    @Value("${WEATHER_API_KEY}")
    private String WEATHER_API_KEY;

    private String pageNoFormat() {
        LocalTime now = LocalTime.now();
        int[] filteredHour = IntStream.of(HOUR).filter(h -> h <= now.getHour()).toArray();
        int max = Arrays.stream(filteredHour).max().getAsInt();

        int index = IntStream.range(0, HOUR.length).filter(i -> HOUR[i] == max).sum(); // 인덱스 반환
        return HOUR_TMRW[index];
    }

    private String pageNoFormatforAfter() {
        LocalTime now = LocalTime.now();
        int[] filteredHour = IntStream.of(HOUR).filter(h -> h <= now.getHour()).toArray();
        int max = Arrays.stream(filteredHour).max().getAsInt();

        int index = IntStream.range(0, HOUR.length).filter(i -> HOUR[i] == max).sum(); // 인덱스 반환
        return HOUR_AFTER_TMRW[index];
    }

    private String hourFormat() {
        LocalTime now = LocalTime.now();
        int[] filteredHour = IntStream.of(HOUR).filter(h -> h <= now.getHour()).toArray();
        int max = Arrays.stream(filteredHour).max().getAsInt();

        return String.format("%02d00", max);
    }
    public String hourFormatforVilage() {
        LocalTime now = LocalTime.now();
        int[] filteredHour = IntStream.of(HOUR).filter(h -> h <= now.getHour()).toArray();
        int max = Arrays.stream(filteredHour).max().getAsInt();

        return String.format("%02d00", max);
    }
    private String dateFormat() {
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        return simpleDateFormat.format(nowDate);
    }

    private String dateFormatforMid() {
        String today = dateFormat();
        LocalTime now = LocalTime.now();
        int hour;
        if (now.getHour() >= 18) {
            hour = 18;
        } else {
            hour = 6;
        }

        return today + String.format("%02d00", hour);
    }
    public JSONArray getOrgVilageFcst(String API_kEY, String date, String time) throws IOException, ParseException {
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
    private JSONArray getVilageFcst(String API_kEY, String pageNo, String date, String time) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + API_kEY); /*Service Key(Encoding)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
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
    // 중기육상예보조회
    private JSONArray getMidLandFcst(String API_kEY, String date) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + API_kEY); /*Service Key(Encoding)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode("11C10000", "UTF-8")); /*충청북도*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/

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
    // 중기기온조회
    private JSONArray getMidTa(String API_kEY, String date) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + API_kEY); /*Service Key(Encoding)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode("11C10303", "UTF-8")); /*괴산*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/

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

    public void addWeatherAttribute(ModelMap model) throws IOException, ParseException {
        JSONArray item = getOrgVilageFcst(WEATHER_API_KEY, dateFormat(), hourFormatforVilage());

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
        String today = month + "." + date + "(" + week + ")";

        WeatherVO weatherVO = new WeatherVO(weather, tmp, pop, reh, wsd, today, img);
        model.addAttribute("weatherData", weatherVO);
    }
    @RequestMapping("/WeatherInformation.do")
    public String WeatherInformation(ModelMap model) throws IOException, ParseException {
        JSONArray tmrwArr = getVilageFcst(WEATHER_API_KEY, pageNoFormat(), dateFormat(), hourFormat());
        JSONArray aftertmrwArr = getVilageFcst(WEATHER_API_KEY, pageNoFormatforAfter(), dateFormat(), hourFormat());

        String tmp="";
        String pty="";
        String sky="";
        String pop = "";
        String img = "";

        for (Object i : tmrwArr) {
            JSONObject jsonItem = (JSONObject) i;
            String category = (String) jsonItem.get("category");

            if ("TMP".equals(category)) {
                tmp = (String) jsonItem.get("fcstValue");
            } else if ("PTY".equals(category)) {
                pty = (String) jsonItem.get("fcstValue");
            } else if ("SKY".equals(category)) {
                sky = (String) jsonItem.get("fcstValue");
            } else if ("POP".equals(category)) {
                pop = (String) jsonItem.get("fcstValue");
            }
        }
        if (Objects.equals(pty, "0")) {
            LocalTime now = LocalTime.now();
            int hour = now.getHour();
            if (hour >= 18)
                img = NIGHTSKYICON[Integer.parseInt(sky)];
            else
                img = DAYSKYICON[Integer.parseInt(sky)];
        }
        else {
            img = PTYICON[Integer.parseInt(pty)];
        }

        WeatherVO tmrwVO = new WeatherVO(tmp, pop, img);
        model.addAttribute("tmrw", tmrwVO);

        for (Object i : aftertmrwArr) {
            JSONObject jsonItem = (JSONObject) i;
            String category = (String) jsonItem.get("category");

            if ("TMP".equals(category)) {
                tmp = (String) jsonItem.get("fcstValue");
            } else if ("PTY".equals(category)) {
                pty = (String) jsonItem.get("fcstValue");
            } else if ("SKY".equals(category)) {
                sky = (String) jsonItem.get("fcstValue");
            } else if ("POP".equals(category)) {
                pop = (String) jsonItem.get("fcstValue");
            }
        }
        if (Objects.equals(pty, "0")) {
            LocalTime now = LocalTime.now();
            int hour = now.getHour();
            if (hour >= 18)
                img = NIGHTSKYICON[Integer.parseInt(sky)];
            else
                img = DAYSKYICON[Integer.parseInt(sky)];
        }
        else {
            img = PTYICON[Integer.parseInt(pty)];
        }

        WeatherVO aftertmrwVO = new WeatherVO(tmp, pop, img);
        model.addAttribute("aftertmrw", aftertmrwVO);

        JSONArray MidLandArr = getMidLandFcst(WEATHER_API_KEY, dateFormatforMid());
        JSONObject firstItem = (JSONObject) MidLandArr.get(0);

        // 날씨
        Map<String, String> wfMap = new LinkedHashMap<>();
        wfMap.put("wf3Am", String.valueOf(firstItem.get("wf3Am")));
        wfMap.put("wf3Pm", String.valueOf(firstItem.get("wf3Pm")));
        wfMap.put("wf4Am", String.valueOf(firstItem.get("wf4Am")));
        wfMap.put("wf4Pm", String.valueOf(firstItem.get("wf4Pm")));
        wfMap.put("wf5Am", String.valueOf(firstItem.get("wf5Am")));
        wfMap.put("wf5Pm", String.valueOf(firstItem.get("wf5Pm")));
        wfMap.put("wf6Am", String.valueOf(firstItem.get("wf6Am")));
        wfMap.put("wf6Pm", String.valueOf(firstItem.get("wf6Pm")));
        wfMap.put("wf7Am", String.valueOf(firstItem.get("wf7Am")));
        wfMap.put("wf7Pm", String.valueOf(firstItem.get("wf7Pm")));
        model.addAttribute("wfImg", wfMap);

        // 강수확률
        Map<String, String> rnMap = new LinkedHashMap<>();
        rnMap.put("rnSt3Am", String.valueOf(firstItem.get("rnSt3Am")));
        rnMap.put("rnSt3Pm",String.valueOf(firstItem.get("rnSt3Pm")));
        rnMap.put("rnSt4Am",String.valueOf(firstItem.get("rnSt4Am")));
        rnMap.put("rnSt4Pm",String.valueOf(firstItem.get("rnSt4Pm")));
        rnMap.put("rnSt5Am",String.valueOf(firstItem.get("rnSt5Am")));
        rnMap.put("rnSt5Pm",String.valueOf(firstItem.get("rnSt5Pm")));
        rnMap.put("rnSt6Am",String.valueOf(firstItem.get("rnSt6Am")));
        rnMap.put("rnSt6Pm",String.valueOf(firstItem.get("rnSt6Pm")));
        rnMap.put("rnSt7Am",String.valueOf(firstItem.get("rnSt7Am")));
        rnMap.put("rnSt7Pm",String.valueOf(firstItem.get("rnSt7Pm")));
        model.addAttribute("rnMap", rnMap);

        JSONArray MidTaArr = getMidTa(WEATHER_API_KEY, dateFormatforMid());
        JSONObject MidTaObj = (JSONObject) MidTaArr.get(0);

        // 기온
        Map<String, String> taMap = new LinkedHashMap<>();
        taMap.put("taMin3", String.valueOf(MidTaObj.get("taMin3")));
        taMap.put("taMax3", String.valueOf(MidTaObj.get("taMax3")));
        taMap.put("taMin4", String.valueOf(MidTaObj.get("taMin4")));
        taMap.put("taMax4", String.valueOf(MidTaObj.get("taMax4")));
        taMap.put("taMin5", String.valueOf(MidTaObj.get("taMin5")));
        taMap.put("taMax5", String.valueOf(MidTaObj.get("taMax5")));
        taMap.put("taMin6", String.valueOf(MidTaObj.get("taMin6")));
        taMap.put("taMax6", String.valueOf(MidTaObj.get("taMax6")));
        taMap.put("taMin7", String.valueOf(MidTaObj.get("taMin7")));
        taMap.put("taMax7", String.valueOf(MidTaObj.get("taMax7")));
        model.addAttribute("taMap", taMap);

        addWeatherAttribute(model);

        return "/user/farminfo/WeatherInformation.lnb";
    }
}
