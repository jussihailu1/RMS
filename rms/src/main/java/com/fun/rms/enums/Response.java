package com.fun.rms.enums;

public enum Response {
//	Succes responses:
	SUCCES,
	
//	Error responses:
	WRONG_CREDENTIALS,
	LOGIN_CODE_IN_USE,
	ALREADY_IN_ROLE,
	WRONG_ROLE,
	NOT_ASSISTANT_MANAGER,	// This might not be needed if causing this violation is prevented in the front-end.
	CAN_NOT_DELETE_MANAGER,
	SERVER_ERROR
}
