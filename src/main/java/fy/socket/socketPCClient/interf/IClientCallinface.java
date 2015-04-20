package fy.socket.socketPCClient.interf;

import java.io.IOException;

/**
 * 接收和异常捕捉接口
 * @author wurunzhou
 *
 */
public interface IClientCallinface {

	/**
	 * 接收socket服务器回复
	 * @param message
	 */
	public void onMessage(String message) throws IOException;

	/**
	 * 捕捉socket 连接异常
	 * @param msg 异常信息
	 */
	public void onError(String msg);
	
	/**
	 * 关闭连接
	 * <br> 关闭连接提示
	 * @param msg 关闭连接提示
	 */
	public void onClosed(String msg);
	
	
}
