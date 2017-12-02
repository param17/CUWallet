package com.cuwallet.commons.constant;

public class UriConstants {

	public class User {

		public static final String NEW_USER = "/api/service/user/create";

		public static final String GET_USER_INFO = "/api/service/user/get";

		public static final String UPDATE_USER_INFO = "/api/service/user/update";
	}

	public class Configuration {

		public static final String SET_MAIL_CONFIGURATION = "/api/service/wallet/configurations/mail";

		public static final String GET_MAIL_CONFIGURATION = "/api/service/wallet/configurations/mail";

		public static final String GET_DEFAULT_MAIL_CONFIGURATION = "/api/service/wallet/configurations/mail/defaults";

		public static final String UPLOAD_EXCEL_FILE = "/api/service/wallet/upload/excel/file";

		public static final String UPLOAD_TEMPLATE_FILE = "/api/service/wallet/upload/template/file";
	}
	
	public class Money {
		
		public static final String SEND_MONEY = "/api/service/wallet/send/money";
		
		public static final String ADD_MONEY = "/api/service/wallet/add/money";
		
		public static final String TRANSACTION_DETAILS = "/api/service/wallet/transaction";
		
		public static final String REQUEST_MOENY = "/api/service/wallet/request/money";
		
		public static final String GET_WALLET_MONEY = "/api/service/wallet/money/get";
		
		
	}
	
	public class BankDetails {
		
		public static final String USER_BANK_DETAILS = "/api/service/wallet/user/bank/details";
		
		public static final String BANK_DETAILS = "/api/service/wallet/bank/details";
	}

}
