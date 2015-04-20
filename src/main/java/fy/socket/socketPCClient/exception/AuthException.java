package fy.socket.socketPCClient.exception;

public class AuthException extends CommonSocketException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5779251625195940708L;

	//错误代码
	private Integer errorCode; 
	private String errorMsg;
	
	public AuthException(int code,String message){
    	this.errorCode = code;
    	this.errorMsg = message;
	}
	
	
    public String excepMessage(){
   	  String  str= "发送异常代码：" + errorCode + ",异常消息：" + errorMsg;
   	  return str; 	
   }
}
