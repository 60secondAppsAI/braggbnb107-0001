package com.braggbnb107.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="identity_verifications")
@Getter @Setter @NoArgsConstructor
public class IdentityVerification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="identity_verification_id")
	private Integer identityVerificationId;
    
  	@Column(name="document_type")
	private String documentType;
    
  	@Column(name="document_number")
	private String documentNumber;
    
	




}
