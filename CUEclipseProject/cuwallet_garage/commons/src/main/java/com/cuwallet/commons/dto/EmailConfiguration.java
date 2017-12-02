package com.cuwallet.commons.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
@Component
public class EmailConfiguration extends EmailTemplate {

	private static final long serialVersionUID = -7619767101996231028L;

	@ApiModelProperty
	private Boolean renderGmailButton;
	
	@ApiModelProperty
	@NotNull
	private Integer noOfMailPerSecond;
	
	@ApiModelProperty
	@NotNull
	private Integer noOfMailPerDay;
	
	@ApiModelProperty
	@NotNull
	private String seasonalTemplateName;
	
	public Boolean getRenderGmailButton() {
		return renderGmailButton;
	}

	public void setRenderGmailButton(Boolean renderGmailButton) {
		this.renderGmailButton = renderGmailButton;
	}
	
	public Integer getNoOfMailPerSecond() {
		return noOfMailPerSecond;
	}

	public void setNoOfMailPerSecond(Integer noOfMailPerSecond) {
		this.noOfMailPerSecond = noOfMailPerSecond;
	}

	public Integer getNoOfMailPerDay() {
		return noOfMailPerDay;
	}

	public void setNoOfMailPerDay(Integer noOfMailPerDay) {
		this.noOfMailPerDay = noOfMailPerDay;
	}

	public String getSeasonalTemplateName() {
		return seasonalTemplateName;
	}

	public void setSeasonalTemplateName(String seasonalTemplateName) {
		this.seasonalTemplateName = seasonalTemplateName;
	}

	public void mergeAdditionalProperties(EmailConfiguration newConfig) {
		this.renderGmailButton = (newConfig.getRenderGmailButton() == null) ? this.getRenderGmailButton()
				: newConfig.getRenderGmailButton();
		this.noOfMailPerSecond = (newConfig.getNoOfMailPerSecond() == null) ? this.getNoOfMailPerSecond()
				: newConfig.getNoOfMailPerSecond();
		this.noOfMailPerDay = (newConfig.getNoOfMailPerDay() == null) ? this.getNoOfMailPerDay()
				: newConfig.getNoOfMailPerDay();
		this.seasonalTemplateName = (newConfig.getSeasonalTemplateName() == null) ? this.getSeasonalTemplateName()
				: newConfig.getSeasonalTemplateName();
	}

}
