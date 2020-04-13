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
@DiscriminatorValue(value="CURRENT")
public class SavingsAccount extends Account {
    double initial;
    
    void setInitial(double initial){
        if(initial>10000){
            this.initial=10000;
            this.balance=initial;
        }
    }
}
