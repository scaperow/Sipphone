package com.linphone.common;


@SuppressWarnings("serial")
public class LinphoneConfigException extends LinphoneException {

	public LinphoneConfigException() {
		super();
	}

	public LinphoneConfigException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public LinphoneConfigException(String detailMessage) {
		super(detailMessage);
	}

	public LinphoneConfigException(Throwable throwable) {
		super(throwable);
	}
}
