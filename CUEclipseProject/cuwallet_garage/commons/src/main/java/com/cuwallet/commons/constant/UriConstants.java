package com.cuwallet.commons.constant;

public class UriConstants {

	public class User {

		public static final String NEW_USER = "/api/service/user/create";

		public static final String GET_USER_INFO = "/api/service/user/get";

		public static final String UPDATE_USER_INFO = "/api/service/user/update";
	}

	public class Configuration {

		public static final String SET_MAIL_CONFIGURATION = "/api/service/reviews/configurations/mail";

		public static final String GET_MAIL_CONFIGURATION = "/api/service/reviews/configurations/mail";

		public static final String GET_DEFAULT_MAIL_CONFIGURATION = "/api/service/reviews/configurations/mail/defaults";

		public static final String UPLOAD_EXCEL_FILE = "/api/service/reviews/upload/excel/file";

		public static final String UPLOAD_TEMPLATE_FILE = "/api/service/reviews/upload/template/file";
	}

}
