package fy.socket.socketPCClient.interf;

import java.io.IOException;

import fy.socket.socketPCClient.exception.AuthException;


/**
 * 发送文本消息，关闭socket连接
 * @author wurunzhou
 *
 */
public interface IWebsocketClientObject {

	/**
	 * 发送文本消息
	 * @param message
	 * @throws AuthException 
	 */
	public void sendMsgText(String message) throws  AuthException;
	
	//public String reciveMsgText();
	
	/*public void onError();*/
	/**
	 * 关闭socket 连接
	 */
	public void close()throws IOException;
}
