package com.ndc888.resp;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * 
 * @title
 * @description
 * @usage
 * @company 上海微汇信息技术有限公司
 * @author TQSUMMER
 * @create 2012-5-8 上午10:55:16
 */
public class ActionResponse {
	protected boolean success;
	protected Object message;
	protected Throwable throwable;
	private String redirect;

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @return the message
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	public Object getMessage() {
		return message;
	}

	/**
	 * @return the throwable
	 */
	@JsonIgnore
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * @return the redirect
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	public String getRedirect() {
		return redirect;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(Object message) {
		this.message = message;
	}

	/**
	 * @param throwable
	 *            the throwable to set
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	/**
	 * @param redirect
	 *            the redirect to set
	 */
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

}
