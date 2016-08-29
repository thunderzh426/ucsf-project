package ucsf.cron;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ucsf.services.GeneralService;

@Component
public class ScheduledTasks {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private GeneralService generalService;
	
	@Scheduled(cron = "0 0 0 * * ?")
    public void processTodoFiles() {
		Map result = (Map) generalService.processTodoFiles();
		log.info("Success:" + result.get("success"));
		log.info("Message:" + result.get("message"));
    }
}
