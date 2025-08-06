package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ContactBook")
public class ContactBookEntity {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int contactid;

@Column(name="name")
private String name;

@Column(name="phone")
private long phone;

@Column(name="Email")
private String Email;

public ContactBookEntity() {
	super();
}

public int getContactid() {
	return contactid;
}

public void setContactid(int contactid) {
	this.contactid = contactid;
}

public String getname() {
	return name;
}

public void setname(String name) {
	this.name = name;
}

public long getphone() {
	return phone;
}

public void setphone(long phone) {
	this.phone = phone;
}

public String getEmail() {
	return Email;
}

public void setEmail(String Email) {
	this.Email = Email;
}
}
