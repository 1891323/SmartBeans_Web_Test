package smartbeans.job;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.EnableScheduling;// 추가

@EnableScheduling // 추가
@Component // 추가
//@EnableAsync // 추가
public class ScheduleTest1 
{
	/**
     * Cron 표현식을 사용한 작업 예약
     * 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
     * 매분 33초마다
     */
    //@Scheduled(cron = "33 * * * * *")
    public void scheduleTaskUsingCronExpression()
    {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("KIMZOON ScheduleTest1 !!! schedule tasks using cron jobs - " + now);
    }
}
