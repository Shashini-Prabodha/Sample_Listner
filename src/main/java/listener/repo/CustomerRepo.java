package listener.repo;



import listener.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    @Query(value = "SELECT * FROM Customer WHERE Flag='1' AND EmailStatus ='NOST' ",nativeQuery = true)
    List<Customer> getEmailStatus();

    @Modifying
    @Query("update Customer c set c.EmailStatus = :status where c.CustomerID = :id")
    void updateEmailStatus(@Param(value = "id") String id, @Param(value = "status") String status);

}

