/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.enums;

import org.springframework.http.HttpStatus;

/**
 *
 * @author obinna.asuzu
 */
public enum ErrorStatus {

    /**
     * Account is already active
     */
    account_active(1, "Account is already active", HttpStatus.BAD_REQUEST),
    /**
     * Token is expired
     */
    token_expired(2, "Token is expired", HttpStatus.UNAUTHORIZED),
    /**
     * Invalid token
     */
    token_invalid(3, "Invalid token", HttpStatus.UNAUTHORIZED),
    /**
     * User already exist
     */
    user_exist(4, "User already exist"),
    /**
     * User does not exist
     */
    user_not_exist(5, "User does not exist", HttpStatus.NOT_FOUND),
    /**
     * You cannot deactivate a system user
     */
    cannot_deactivate_sys_user(6, "You cannot deactivate a system user"),
    /**
     * Role already exists
     */
    role_exist(7, "Role already exists"),
    /**
     * Role does not exist
     */
    role_not_exist(8, "Role does not exist", HttpStatus.NOT_FOUND),
    /**
     * Invalid Authority.Valid format: ROLE.PERMISSION_ACCESS Example:
     * 'ADMIN.USER_READ'
     */
    invalid_grant_format(9, "Invalid Authority.Valid format: ROLE.PERMISSION_ACCESS Example: 'ADMIN.USER_READ'"),
    /**
     * Invalid Authority: Allowed characters; a to z, A to Z, 0 to 9,'_' '_' and
     * '-' Examples: 'ADMIN.USER_READ', 'ADMIN.WEB-SOCKET_READ'
     */
    invalid_grant_characters(10, "Invalid Authority: Allowed characters; a to z, A to Z, 0 to 9,'_' '_' and '-'  Examples: 'ADMIN.USER_READ', 'ADMIN.WEB-SOCKET_READ'"),
    /**
     * Invalid Authority: Must end with '_READ' or '_WRITE' Examples:
     * 'ADMIN.USER_READ', 'ADMIN.WEB-SOCKET_WRITE'
     */
    invalid_grant_access(11, "Invalid Authority: Must end with '_READ' or '_WRITE'  Examples: 'ADMIN.USER_READ', 'ADMIN.WEB-SOCKET_WRITE'"),
    /**
     * Privilege already exist
     */
    privilege_exist(12, "Privilege already exist", HttpStatus.NOT_FOUND),
    /**
     * Privilege does not exist
     */
    privilege_not_exist(13, "Privilege does not exist", HttpStatus.NOT_FOUND),
    /**
     * Cannot delete roles in use
     */
    cannot_del_roles_inuse(14, "Cannot delete roles in use", HttpStatus.FORBIDDEN),
    /**
     * Cannot delete privileges in use
     */
    cannot_del_privileges_inuse(15, "Cannot delete privileges in use", HttpStatus.FORBIDDEN),
    /**
     * Cannot delete system roles
     */
    cannot_delete_sys_role(16, "Cannot delete system roles", HttpStatus.FORBIDDEN),
    /**
     * Cannot delete system privileges
     */
    cannot_delete_sys_privileges(17, "Cannot delete system privileges", HttpStatus.FORBIDDEN),
    /**
     * You cannot delete your account
     */
    cannot_delete_self(18, "You cannot delete your account", HttpStatus.FORBIDDEN),
    /**
     * Account is disabled
     */
    account_disabled(19, "Account is disabled", HttpStatus.FORBIDDEN),
    /**
     * Rating not found
     */
    rating_not_found(20, "Rating not found", HttpStatus.NOT_FOUND),
    /**
     * Account registeration failed
     */
    cannot_register_account(21, "Account registeration failed", HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * Message was not found
     */
    message_not_found(22, "Message was not found", HttpStatus.NOT_FOUND),
    /**
     * User account required
     */
    valid_account_required(23, "Valid account is required", HttpStatus.UNAUTHORIZED),
    /**
     * Account is already active
     */
    account_verified(24, "Account is already verified", HttpStatus.BAD_REQUEST),
    /**
     * Account is already active
     */
    account_not_verified(25, "Account is not verified", HttpStatus.BAD_REQUEST),
    /**
     * You cannot feature your account
     */
    cannot_feature_self(26, "You cannot feature your account", HttpStatus.FORBIDDEN),
    /**
     * You cannot unfeature your account
     */
    cannot_unfeature_self(26, "You cannot unfeature your account", HttpStatus.FORBIDDEN),;

    int code;

    String message;

    HttpStatus status = HttpStatus.BAD_REQUEST;

    private ErrorStatus(int code, String message) {
        this.message = message;
        this.code = code;
    }

    private ErrorStatus(int code, String message, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
    @Override
	public String toString() {
		return this.code + " " + name();
	}

}
