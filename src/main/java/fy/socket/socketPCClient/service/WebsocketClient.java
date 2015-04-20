package fy.socket.socketPCClient.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import fy.socket.socketPCClient.exception.AuthException;
import fy.socket.socketPCClient.interf.IClientCallinface;
import fy.socket.socketPCClient.interf.IWebsocketClientObject;
import fy.socket.socketPCClient.util.ExceptionConstants;
import fy.socket.socketPCClient.util.SocketConstant;
import fy.socket.socketPCClient.util.logger.LoggerUtil;


public class WebsocketClient {

	private Logger logger = LoggerUtil.getLogger(this.getClass().getName());
	
	private IClientCallinface clientIm;
	
//	private final static int PORT = 8866;
//	private final static String HOST = "222.201.139.162";// "localhost";
	public WebsocketClient(){
		
	}
	public WebsocketClient(IClientCallinface clientF){
		this.clientIm = clientF;
	}
	

	
	public IWebsocketClientObject createConnection(int port,String host){
		return new ClientImpl( port, host);
	}
	
	final class ClientImpl implements IWebsocketClientObject{

		private Socket clientS ;
		private final static String USERNAME = "webband";
		private final static String VERCODE = "20141227";
		
		private DataOutputStream dout;
		private DataInputStream din;
		
		private ClientImpl(int port,String host){
			
			try {
				init(port,host);
			} catch (IOException e) {
				logger.log(Level.INFO,"初始化 socket 连接失败 （client）");
			}  
		}
		private final void init(int port,String host) throws  IOException{
			// 初始化连接
			// 发送连接请求  验证用户
			if(clientS == null)
				clientS  = new Socket(host,port);
			if(verifyUser()){
				// 验证用户
				logger.log(Level.INFO,"吾托帮web用户身份被通过！！");
				new Thread(new ReciveThread4()).start();
			}
		}
		private boolean pass = false;
		
		/**
		 * java API 接收验证用户
		 * @return
		 */
		private boolean verifyUser(){
			
			try {
				dout = new DataOutputStream(clientS.getOutputStream());
				din  = new DataInputStream(clientS.getInputStream());
				String verfUser = USERNAME+ SocketConstant.splitNormalUV.getRssURL()+VERCODE;
				dout.writeInt(verfUser.length());
				dout.flush();
				dout.write(verfUser.getBytes());
				dout.flush();
				
				int readLen = din.readInt();
				String ack = din.readUTF();
				if("ackPass".equals(ack)){
					pass = true;
				}
			} catch (IOException e) {
				logger.log(Level.INFO,"验证java API 调用者身份过程中 发生异常");
				clientIm.onError(e.toString());
			}
			return pass;
			
		}
		


		@Override
		public void sendMsgText(String message) throws  AuthException {
			if(pass)
				try {
					sendMessageText(message);
				} catch (IOException e) {
					abortError("写（主）线程 突发异常");
				}
			else 
				throw new  AuthException(ExceptionConstants.SendError.getRss(),"标志（pass）未没有开启，不能发送文本消息");//clientIm.onError("标志（pass）未没有开启，不能发送文本消息");;
		}
		
		@Override
		public void close() throws IOException  {
			
				pass = false;
				clientIm.onMessage("server 关闭");
				clientS.close();
				clientIm.onClosed("清理连接资源");
		}
		
		public void closed() {
			try {
				pass = false;
				clientIm.onMessage("server 关闭");
				clientIm.onError("IO 异常，socket 已经关闭");
				clientS.close();
			} catch (IOException e) {
				logger.log(Level.INFO,"socket client  关闭发送异常");
			}
		}
		/**
		 * 通过socket连接，发送文本消息
		 * @param message
		 * @throws IOException
		 */
		private void sendMessageText(String message) throws IOException{

				dout.writeInt(message.length());
				dout.flush();
				dout.writeUTF(message);
				dout.flush();

		}
		
		/**
		 * 接收消息
		 * <br> 回调onMessage方法
		 * @param message
		 * @throws IOException
		 */
		private void onMessageText(String message) throws IOException{
			 clientIm.onMessage(message);
		}
		
		private void abortError(String msg){
			clientIm.onError(msg);
			closed();
		}
		
		/**
		 * java socket api 接收socket server 回复
		 * @author Administrator
		 *
		 */
		final class ReciveThread4 implements Runnable{

			
			public ReciveThread4(){
				
			}
			
			public void run() {
				try{
					int readLen = 0;
					//ByteBuffer buf = ByteBuffer.allocate(1024);
					while(true){

						if((readLen = din.readInt()) != -1){
							String msg = din.readUTF();
							onMessageText(msg);
						}

					}
				}catch(  IOException e){
					logger.log(Level.INFO,"接收消息过程中，socket 发生异常");
					abortError("读取子线程，突然发生异常");
					//throw new IOSocketException(ExceptionConstants.OutputError.getRss(), "读取子线程，突然发送异常");
				}
				logger.log(Level.INFO,"结束读子线程，然后关闭写（主）线程");
				
			}
			
		}


	}
	
}


