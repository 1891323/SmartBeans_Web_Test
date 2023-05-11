package chungnam.portal.publisher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 임시로 퍼블리셔가 작업할 수있도록 해당 기능을 추가하였음.
 * 퍼블리셔가 jsp/chungnam/portal/publisher/ 하위에 폴더 포함 설정한 경로로 접근되도록 함.
 * index와 lnb 두가지 레이아웃을 선택할 수 있도록 함.
 */
@Controller
@RequestMapping("/pub")
public class PublisherWorkController {

    private String url;

    @RequestMapping("/index/{u1}")
    public String index(@PathVariable(value = "u1") String u1) {
        return getIndexPath(u1, "", "", "", "", "");
    }
    @RequestMapping("/index/{u1}/{u2}")
    public String index2(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2) {
        return getIndexPath(u1, u2, "", "", "", "");
    }
    @RequestMapping("/index/{u1}/{u2}/{u3}")
    public String index3(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3) {
        return getIndexPath(u1, u2, u3, "", "", "");
    }
    @RequestMapping("/index/{u1}/{u2}/{u3}/{u4}")
    public String index4(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3
            , @PathVariable(value = "u4") String u4) {
        return getIndexPath(u1, u2, u3, u4, "", "");
    }
    @RequestMapping("/index/{u1}/{u2}/{u3}/{u4}/{u5}")
    public String index5(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3
            , @PathVariable(value = "u4") String u4
            , @PathVariable(value = "u5") String u5) {
        return getIndexPath(u1, u2, u3, u4, u5, "");
    }
    @RequestMapping("/index/{u1}/{u2}/{u3}/{u4}/{u5}/{u6}")
    public String index6(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3
            , @PathVariable(value = "u4") String u4
            , @PathVariable(value = "u5") String u5
            , @PathVariable(value = "u6") String u6) {
        return getIndexPath(u1, u2, u3, u4, u5, u6);
    }


    public String getIndexPath(
            String u1,
            String u2,
            String u3,
            String u4,
            String u5,
            String u6
    ){
        String url = Stream.of(u1, u2, u3, u4, u5, u6)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("/"));

        return "layouts/publisher/index/" + url + ".index";
    }


    @RequestMapping("/lnb/{u1}")
    public String lnb(@PathVariable(value = "u1") String u1) {
        return getLnbPath(u1, "", "", "", "", "");
    }
    @RequestMapping("/lnb/{u1}/{u2}")
    public String lnb2(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2) {
        return getLnbPath(u1, u2, "", "", "", "");
    }
    @RequestMapping("/lnb/{u1}/{u2}/{u3}")
    public String lnb3(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3) {
        return getLnbPath(u1, u2, u3, "", "", "");
    }
    @RequestMapping("/lnb/{u1}/{u2}/{u3}/{u4}")
    public String lnb4(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3
            , @PathVariable(value = "u4") String u4) {
        return getLnbPath(u1, u2, u3, u4, "", "");
    }
    @RequestMapping("/lnb/{u1}/{u2}/{u3}/{u4}/{u5}")
    public String lnb5(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3
            , @PathVariable(value = "u4") String u4
            , @PathVariable(value = "u5") String u5) {
        return getLnbPath(u1, u2, u3, u4, u5, "");
    }
    @RequestMapping("/lnb/{u1}/{u2}/{u3}/{u4}/{u5}/{u6}")
    public String lnb6(@PathVariable(value = "u1") String u1
            , @PathVariable(value = "u2") String u2
            , @PathVariable(value = "u3") String u3
            , @PathVariable(value = "u4") String u4
            , @PathVariable(value = "u5") String u5
            , @PathVariable(value = "u6") String u6) {
        return getLnbPath(u1, u2, u3, u4, u5, u6);
    }
    public String getLnbPath(
            String u1,
            String u2,
            String u3,
            String u4,
            String u5,
            String u6
    ){
        String url = Stream.of(u1, u2, u3, u4, u5, u6)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("/"));

        return "layouts/publisher/lnb/" + url + ".lnb";
    }


}
