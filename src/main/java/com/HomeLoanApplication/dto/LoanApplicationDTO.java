package com.HomeLoanApplication.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LoanApplicationDTO {


	@NotNull(message = "Loan Applied Amount is required Cannot be Empty")
	@Min(value = 100000)
	@Max(value = 9999999)
	private double loanAppliedAmount;

	@NotNull(message = "Tenure is required Cannot be Empty")
	@Min(value = 1)
	@Max(value = 35)
	private int tenure;

	public double getLoanAppliedAmount() {
		return loanAppliedAmount;
	}

	public void setLoanAppliedAmount(double loanAppliedAmount) {
		this.loanAppliedAmount = loanAppliedAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

}
