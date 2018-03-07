package com.linphone.common;

public class LinphoneException extends Exception {

	public LinphoneException() {}

	public LinphoneException(String detailMessage) {
		super(detailMessage);
	}

	public LinphoneException(Throwable throwable) {
		super(throwable);
	}

	public LinphoneException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}