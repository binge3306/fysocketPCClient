package fy.socket.socketPCClient.util;

/**
 * 2015 01 31 
 * for 将可能使用的常量集中到该目录下面
 * @author wurunzhou
 *
 */
public  enum SocketConstant { 
	
	// 异常前缀
	ERROROUT("---------------------"),
	
	
	// 输出前缀
	SYSTEMOUT("*******************"),
	
	// 吾托帮web 登录socketFy的登录名称
	wtbwebKey("webband"),
	
	
	/**
	 * 关键字截取长度
	 */
	splitLen("3"),
	
	/**
	 *  分隔符（用户名：验证码：url（如果是app用户登录还有：app））
	 */
	splitUV(":"),
	
	/**
	 *  普通socket 用户和验证码分隔符 ：
	 */
	splitNormalUV(":"),
	
	/**
	 *  用户：用户对应的互动室
	 */
	splitUC(":"),
	
	/**
	 * 互动室与文本消息分隔符  ##
	 */
	splitCT("##"),
	
	/**
	 * 客户端发送图片到socketFy  格式 ：chatroom1##0###图片内容  (二进制格式)
	 */
	splitCI("##"),
	
	/**
	 * 服务器转发图片，格式 互动室视图ID##图片内容
	 */
	splitCIR("##"),
	
	/**
	 * 待登录用户发送标志#U#
	 */
    holeLoginUser("#U#"), 
    
    /**
     * 登录用户标志
     */
    olUser(""),
    
    /**
     * pc用户退出标志#O#
     */
    quitUser("#O#"),
    
    /**
     * app用户退出标志#A#
     */
    quitUserApp("#A#"),
    
    /**
     * 待登录用户  用户对应的帮区私聊互动室列表#C#
     */
    hlUserChats("#C#"),
    
    
    /**
     * 创建帮区私聊互动室 #C#
     */
    createBPChat("#C#"),
    
    /**
     * 删除帮区私聊互动室
     */
    deleteBPChat(""),
    
    // 发送帮区私聊 消息
    sendBPMsg(""),
    
    /**
     * 发送公共 消息
     */
    sendPubMsg("");
    
    /**
     * 枚举对象的 RSS 地址的属性
     */
    private String rss_url; 
        
    /**
     * 枚举对象构造函数
     * @param rss
     */
    private SocketConstant(String rss) { 
        this.rss_url = rss; 
    } 
        
    /**
     * 枚举对象获取 表达式的方法
     * @return
     */
    public String getRssURL() { 
        return this.rss_url; 
    } 
 }