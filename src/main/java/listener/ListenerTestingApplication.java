package listener;

import listener.mailsender.EmailService;
import listener.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ListenerTestingApplication  {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ListenerTestingApplication.class, args);
//        CustomerService service = context.getBean(CustomerService.class);
//
//        service.checkList();

    }


}
