package banking;

import banking.Account;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CUST_ID")
	private long id;

	@Column(name = "NAME")
	private String name;
        
        @Column(name = "AGE")
	private int age;
        
        @Column(name = "ADDRESS")
	private String addr;
        
        @Column(name = "JOB")
	private String job;
        
	
	@OneToMany(mappedBy="ACCOUNT_CUST")
        @Column (name = "CUT_ACCOUNTS")
	private Account custAccount;
	
	public Customer(){
        this.custAccount = new Account();
	}
	
	public Customer(String name,int age,String addr,String job, Account a){
            this.custAccount = a;
            this.name = name;
            this.addr=addr;
            this.age=age;
            this.job=job;
            this.custAccount=a;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Subject))
			return false;
		Subject other = (Subject) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}*/

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + "]";
	}

	public Account getAccount() {
		return custAccount;
	}

	public void setAccount(Account custAccount) {
		this.custAccount = custAccount;
	}

}