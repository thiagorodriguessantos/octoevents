package com.jaya.octoevents.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EndPointError {

	@Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore	
	private Integer id;
	
	@Size(max=3000)
	private String message;
	@Size(max=3000)
	private String clientInfo;
	@NotBlank
	@Size(max=3000)
	private String stackTrace;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;
	
	public EndPointError(String message, String clientInfo, String stackTrace, Date dataHora) {
		super();
		this.message = message;
		this.clientInfo = clientInfo;
		this.stackTrace = stackTrace;
		this.dataHora = dataHora;
	}
	
}
