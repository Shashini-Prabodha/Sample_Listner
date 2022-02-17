package listener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    @Id
    String CustomerID;
    String Name;
    String Address;
    String Status;
    String CreateTime;
    String LastUpdatedTime;
    String DOB;
    String Email;
    String CIF;
    String NIC;
    String BranchCode;
    String Flag;
    String EmailStatus;

}
