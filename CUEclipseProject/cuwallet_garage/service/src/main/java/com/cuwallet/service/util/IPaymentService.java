package com.cuwallet.service.util;

import com.cuwallet.commons.dto.BankAmountInformation;
import com.cuwallet.commons.dto.UserBankInformation;

public interface IPaymentService {

	boolean addBankDetails(UserBankInformation userBankInformation);

	boolean addDummyBankDetails(BankAmountInformation request);

	
}
