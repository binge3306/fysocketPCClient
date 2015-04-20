package fy.socket.socketPCClient.util;

public enum ExceptionConstants {
	
	
	// 连接异常
	ConnError(2000),
	
	
	// 发送突然异常
	SendError(1000),
	
	// socket output 不可用
	OutputError(3002),
	
	// socket input 不可用
	InputError(3001);
	
    // 枚举对象的 RSS 地址的属性
    private Integer rss; 
        
    // 枚举对象构造函数
    private ExceptionConstants(Integer rss) { 
        this.rss = rss; 
    } 
        
    // 枚举对象获取 表达式的方法
    public Integer getRss() { 
        return this.rss; 
    } 
	
}
