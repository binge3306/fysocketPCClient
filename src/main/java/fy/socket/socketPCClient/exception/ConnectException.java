package fy.socket.socketPCClient.exception;

public class ConnectException extends CommonSocketException{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8743286668366495601L;
	
	//默认错误代码 
   public static final Integer GENERIC = 1000000; 
   //错误代码
   private Integer errorCode; 
   private String errorMsg;
   
    public ConnectException(Integer errorCode, Throwable cause) {
           this(errorCode, null, cause);
    }
    public ConnectException(String message, Throwable cause) {
           //利用通用错误代码
           this(GENERIC, message, cause);
    }
    public ConnectException(Integer errorCode, String message, Throwable cause) {
           super(message, cause);
           this.errorCode = errorCode;
    }
    
    public ConnectException(int code,String message){
    	this.errorCode = code;
    	this.errorMsg = message;
    }
    
    public Integer getErrorCode() {
           return errorCode;
    } 
    
    public String excepMessage(){
     	  String  str= "连接异常代码：" + errorCode + ",异常消息：" + errorMsg;
     	  return str; 	
     }
	
}