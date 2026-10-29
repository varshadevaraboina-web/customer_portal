package com.vcube.customerportal.Entity;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;

	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;

	@Entity
	@Table(name = "user_accounts")
	@Getter
	@Setter
	@NoArgsConstructor
	public class UserAccount {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String email;

	private String password;
	

	}


