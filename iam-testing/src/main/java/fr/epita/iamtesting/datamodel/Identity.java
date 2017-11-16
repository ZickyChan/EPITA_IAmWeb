package fr.epita.iamtesting.datamodel;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="IDENTITIES")
public class Identity {
	@Id
	private int uid;
	@Column(name="NAME", length = 50)
	private String displayName;
	@Column(name="EMAIL", length = 50)
	private String email;
	@Column(name="BIRTHDATE")
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	private static int counter = 0;
	
	
	//Map<String,String> attributes;
	
	private Identity(){}
	
	/**
	 * This is a constructor for Identity class.
	 * This constructor set the displayName and the email by the relevant arguments.
	 * It set the uid automatically by using counter
	 * @param displayName
	 * @param email
	 */
	public Identity(String displayName, String email, Date d) {
		this.displayName = displayName;
		this.uid = ++counter;
		this.email = email;
		this.birthdate = d;
	}
	
	/**
	 * This is a constructor for Identity class.
	 * This constructor set the uid, the displayName and the email by the relevant arguments.
	 * @param id
	 * @param displayName
	 * @param email
	 */
	public Identity(int id, String displayName, String email, Date d) {
		this.displayName = displayName;
		this.uid = id;
		this.email = email;
		this.birthdate = d;
	}
	
	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int i){
		counter = i;
	}
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [displayName=" + displayName + ", uid=" + uid + ", email=" + email + ", birthdate="+birthdate+"]";
	}
	
	/**
	 * This method is used to print the identity in the format:
	 * <pre>
	 * --- Identity ---
	 * Identity id: uid
	 * Identity display name: displayName
	 * Identity email: email
	 * --------------
	 * </pre>
	 * @return
	 */
	public String display(){
		String result = "";
		result += "---Identity---\n";
		result += "Identity id: " + this.uid + "\n";
		result += "Identity display name: " + this.displayName + "\n";
		result += "Identity email: " + this.email + "\n";
		result += "--------------";
		return result;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	
	
}
