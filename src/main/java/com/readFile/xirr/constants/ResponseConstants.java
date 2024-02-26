package com.readFile.xirr.constants;

public class ResponseConstants {

	public static final Integer RESPONSE_CODE_SUCCESS = 20;
	public static final Integer RESPONSE_CODE_ERROR = 40;
	public static final String RESPONSE_CODE_SUCCESS_GENERIC_MESSAGE = "Success";

	public static final String RESPONSE_MESSAGE_SUCCESS_GENERIC = "Request Processed Successfully";
	public static final String RESPONSE_MESSAGE_FAILURE_GENERIC = "Failed to process request. Please try again later.";

	public static final String FETCH_SUCCESS = "Fetch Success.";

	public static final String ACCOUNT_VERIFICATION_SUCCESS = "Account verified successfully.";
	public static final String OTP_VERIFICATION_SUCCESS = "OTP Validated Succesfully";
	public static final String PASSWORD_CHANGE_SUCCESS = "Password Changed Successfully.";
	public static final String PASSWORD_RESET_LINK_SENT_MESSAGE = "A password reset link has been sent to your registered email id. Kindly check.";
	public static final String PASSWORD_RESET_MESSAGE = "Password Reset Successfully.";
	public static final String OTP_SENT_MESSAGE = "OTP has been sent to your registered email address.";
	public static final String SMS_OTP_SENT_MESSAGE = "OTP has been sent to your phone number.";
	public static final String SMS_OTP_SENT_MESSAGE_FAILURE = "Failed to send OTP to phone number.";
	public static final String NOTIFICATION_TRIGGER_EMAIL_NOT_FOUND = "Email not found for otpNotification Trigger";
}
