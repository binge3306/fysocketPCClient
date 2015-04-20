package fy.socket.socketPCClient.exception;

public class CommonSocketException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3344793317742212529L;

	private String errorMsg;
	private Integer errorCode;
	
	public CommonSocketException(){
		
	}
	
	
	public CommonSocketException(Integer errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	
	public CommonSocketException(String message ,Throwable cause){
		super(message, cause);
	}
	
	
}
