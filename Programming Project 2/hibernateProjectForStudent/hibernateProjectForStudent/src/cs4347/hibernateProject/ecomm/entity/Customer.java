package cs4347.hibernateProject.ecomm.entity;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;


public class Customer 
{
	private Long id;
	private String firstName;
	private String lastName;
	private Character gender;
	private Date dob;
	private String email;
	private Address address;
	private CreditCard creditCard;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Character getGender()
	{
		return gender;
	}

	public void setGender(Character gender)
	{
		this.gender = gender;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public CreditCard getCreditCard()
	{
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard)
	{
		this.creditCard = creditCard;
	}
}
