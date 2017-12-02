package com.cuwallet.repository.dao;

import com.cuwallet.commons.dto.BankAmountInformation;
import com.cuwallet.commons.dto.UserBankInformation;

public interface IPaymentDao {

	boolean addBankDetails(UserBankInformation userBankInformation);

	boolean addDummyBankDetails(BankAmountInformation request);

}
