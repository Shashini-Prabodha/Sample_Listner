package listener.service;

import listener.entity.Customer;
import listener.mailsender.EmailService;
import listener.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepo repo;

    @Autowired
    EmailService emailService;

    List<Customer> lastList = new ArrayList<>();
    List<Customer> list = new ArrayList<>();
    List<Customer> all = new ArrayList<>();

//    @Scheduled(cron = "* * * * * *")
//    public void checkList() {
//        list.clear();
//
//        all= customer.getFlagStatus();
//        System.out.println("(all.size()"+all.size()+" lastList.size() "+lastList.size());
//        if(lastList.isEmpty()){
//            getCustomer(all);
//        }else if(all.size() != lastList.size()) {
////            for (Customer customer : all) {
////                System.out.println("Customer all-> " + customer.getCustomerID());
////            }
////            for (Customer customer : lastList) {
////                System.out.println("Customer lastList-> " + customer.getCustomerID());
////            }
////            for (int i = all.size(),j=0; i >lastList.size() ;i--,j++) {
////                System.out.println("i => "+i);
////                list.add(all.get(i-1));
////                System.out.println(all.get(i-1));
////            }
//
//            getCustomer(list);
//        }
//    }

//    public void getCustomer(List<Customer> list) {
//        lastList = customer.getFlagStatus("1");
//        System.out.println("line 26" + list.isEmpty());
//        for (Customer customer : list) {
//            System.out.println("Customer -> " + customer.getCustomerID());
//            try {
//                emailService.sendMail(customer.getEmail(),
//                        "Hi " + customer.getName() + " , " + "\n Thank you for registering with SP Bank. We warmly Welcome you to the SP Bank.\n" +
//                                "You will be added to our Mobile Banking System.\n" +
//                                "\n" +
//                                "This is an automatically generated email. Please don't reply.\n" +
//                                "\n" +
//                                "\n" +
//                                "Sincerely,\n" +
//                                "SP Bank.",
//                        "WELCOME TO SP BANK",
//                        "C:\\Users\\SHASHI\\Desktop\\pinterest\\welcome animated.gif");
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }
//


    @Scheduled(cron = "* * * * * *")
    public void checkList() {
        list = repo.getEmailStatus();
        if(!list.isEmpty()){
            System.out.println("list length "+list.size());
            emailSender();
            list.clear();
        }
    }


    public void emailSender() {

        System.out.println("line 26" + list.isEmpty());
        for (Customer customer : list) {
            System.out.println("Customer -> " + customer.getCustomerID());
            try {
//                repo.updateEmailStatus(customer.getCustomerID(),"STPR");
                setCustomer(customer, "STPR");
                emailService.sendMail(customer.getEmail(),
                        "Hi " + customer.getName() + " , " + "\n Thank you for registering with SP Bank. We warmly Welcome you to the SP Bank.\n" +
                                "You will be added to our Mobile Banking System.\n" +
                                "\n" +
                                "This is an automatically generated email. Please don't reply.\n" +
                                "\n" +
                                "\n" +
                                "Sincerely,\n" +
                                "SP Bank.",
                        "WELCOME TO SP BANK",
                        "C:\\Users\\SHASHI\\Desktop\\pinterest\\welcome animated.gif");

//                repo.updateEmailStatus(customer.getCustomerID(),"COMP");
                setCustomer(customer, "COMP");

            } catch (MessagingException e) {
//                repo.updateEmailStatus(customer.getCustomerID(),"ERRO");
                setCustomer(customer, "ERRO");

                e.printStackTrace();
            }
        }


    }

    public void setCustomer(Customer customer, String status) {
        System.out.println("in set => " + customer + " status " + status);
        customer.setEmailStatus(status);
        Customer customer1 = new Customer(customer.getCustomerID(), customer.getName(), customer.getAddress(), customer.getStatus(),
                customer.getLastUpdatedTime(), customer.getCreateTime(), customer.getDOB(),
                customer.getEmail(), customer.getCIF(), customer.getNIC(), customer.getBranchCode(),
                customer.getFlag(), customer.getEmailStatus());

        repo.save(customer1);
    }

}
