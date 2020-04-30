package com.fun.rms.enums;

public enum Response {
	SUCCES,
	WRONG_CREDENTIALS,
	LOGIN_CODE_IN_USE,
	ALREADY_IN_ROLE,
	WRONG_ROLE,
	NOT_ASSISTANT_MANAGER,	// This might not be needed if causing this violation is prevented in the front-end.
	SERVER_ERROR
}
