package fy.socket.socketPCClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fy.socket.socketPCClient.exception.AuthException;
import fy.socket.socketPCClient.exception.ConnectException;
import fy.socket.socketPCClient.interf.IClientCallinface;
import fy.socket.socketPCClient.interf.IWebsocketClientObject;
import fy.socket.socketPCClient.service.WebsocketClient;
import fy.socket.socketPCClient.util.ExceptionConstants;
import fy.socket.socketPCClient.util.SocketConstant;


public class ClientMain  implements IClientCallinface{

	private IWebsocketClientObject clientObject;
	private int initUserNum = 0;
	
	private boolean overRUN = false;
	
	private final static int StaticNum = 20;
	/**
	 * 初始化pc(wtbweb)
	 * @param initUserNum 待连接用户数  默认为100
	 */
	public ClientMain(int initUserNum){
		this.initUserNum = initUserNum;
		init();
		
	}
	
	public void init( ){
		if(clientObject == null){
			clientObject = new WebsocketClient(this).createConnection();
		}
	}
	
	private void start() {
		
		try {
			
			if(clientObject == null){
				throw new ConnectException(ExceptionConstants.SendError.getRss(), "获取不到连接对象");
			}
			for(int i= 0;i<initUserNum;i++){
				// 发送待登录用户名和验证码
				//clientObject.sendMsgText("#U#"+"user" + i+":"+"verify"+i);
				clientObject.sendMsgText(SocketConstant.holeLoginUser.getRssURL()+"user" + i+SocketConstant.splitUV.getRssURL()+"verify"+i);
				
				// 发送待登录用户 互动室列表
				List<String> chatrooms = new ArrayList<String>();
				for(int j = 0;j<5;j++){
					if(j%2==0 && i%2 == 0)
						// 偶数用户没有偶数互动室
						continue;
					chatrooms.add("chatroom" + j);
				}
//				chatrooms.add("20840");
//				chatrooms.add("20896");
//				chatrooms.add("20237");
				
				//clientObject.sendMsgText("#C#"+"user"+i+":"+chatrooms.toString());
				clientObject.sendMsgText(SocketConstant.hlUserChats.getRssURL()+"user"+i+SocketConstant.splitUC.getRssURL()+chatrooms.toString());
				TimeUnit.SECONDS.sleep(1);
//				if(i == 5)
//					System.out.println("111");
//				if(i>4)
//					TimeUnit.SECONDS.sleep(60);
			}
			Thread.yield();
			TimeUnit.SECONDS.sleep(120);
			while(!overRUN){
				
			}
			clientObject.close();
		} catch (InterruptedException e) {
			System.out.println("java 客户端发送数据失败");
		} catch (ConnectException e) {
			System.out.println(e.excepMessage());
		} catch (AuthException e) {
			System.out.println(e.excepMessage());
		} catch (IOException e) {
			System.out.println("java 客户端发送数据失败");
		} 
		
		/**
		 * catch (ConnectException e) {
			System.out.println(e.excepMessage());
			
		} catch (IOException e) {
			System.out.println("java 客户端发送数据失败");
		} catch (SendException e) {
			System.out.println(e.excepMessage());
		}
		 */
	}
	
	/**
	 * 发送一个用户和用户对应的互动室列表
	 * @throws IOException 
	 * @throws AuthException 
	 */
	private void start1() throws IOException, AuthException {
		// 发送待登录用户名和验证码
		// clientObject.sendMsgText("#U#"+"user" + i+":"+"verify"+i);
		clientObject.sendMsgText(SocketConstant.holeLoginUser.getRssURL()
				+ "user1" + SocketConstant.splitUV.getRssURL() + "verify1");

		// 发送待登录用户 互动室列表
		List<String> chatrooms = new ArrayList<String>();
		for (int j = 0; j < 5; j++) {
			if (j % 2 == 0)
				// 偶数用户没有偶数互动室
				continue;
			chatrooms.add("chatroom" + j);
		}

		// clientObject.sendMsgText("#C#"+"user"+i+":"+chatrooms.toString());
		clientObject.sendMsgText(SocketConstant.hlUserChats.getRssURL()
				+ "user1" + SocketConstant.splitUC.getRssURL()
				+ chatrooms.toString());

		while (true) {

		}
		// clientObject.onClose();

	}

	private void loginOutOptions(){
		
	}
	
	private void loginout1(String userKey){
		System.out.println("" + userKey);
		
		
	}
	
	private void printMsg(String reMsg){
		System.out.println("收到服务器的消息= "+reMsg);
	}
	
	// 提供一个异步返回结果的接口
	@Override
	public void onMessage(String message) {
		printMsg(message);
	}

	// 捕获异常
	@Override
	public void onError(String msg) {
		printMsg(msg);
		
		// 不能这么直接等空
		//clientObject = null;
		// 实际中并不需要改语句
		overRUN = true;
	}
	
	@Override
	public void onClosed(String msg) {
		clientObject = null;
		
	}
	

//	/**
//	 * 接口内部发生严重异常，关闭需要清理接口资源
//	 */
//	@Override
//	public void onClosed(String msg) {
//		clientObject = null;
//	}

	
	public static void main(String[] args) {
		int connectNum = 0;
		
		if(args.length <1){
			connectNum = StaticNum;
			System.out.println("args is null,so set connectNum is " + connectNum);
		}else{
			try{
				connectNum = Integer.parseInt(args[0]);
				System.out.println("connectNum is setting " + connectNum);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		new ClientMain(connectNum).start();
	}







}
