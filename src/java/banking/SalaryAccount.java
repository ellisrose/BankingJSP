/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ellis
 */
@Entity
@DiscriminatorValue(value="SALARY")
public class SalaryAccount extends Account {
    
    void setInitial(double initial){
            this.initial=0;
            this.balance=initial;
    }
}
