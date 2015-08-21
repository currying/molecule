package com.toparchy.push;

import android.content.Context;
import android.content.Intent;
import com.toparchy.push.core.MessageDispacher;
import com.toparchy.push.core.MessageService;
import com.toparchy.push.utils.ServiceUtil;

/**
 * 推送启动器
 * 
 * @author flaty
 * 
 */
public class PushBootStrap {
	
	private Context applicationContext = null;

	public static final String host = "192.168.111.106";

	public static final int port = 11111;

	private static String TAG = "PushBootStrap";

	private static final String serviceName = "com.toparchy.push.core.MessageService";

	private static volatile PushBootStrap push;

	private PushBootStrap() {
	}

	public static PushBootStrap getInstance() {
		// cas 比较
		if (push == null) {
			synchronized (PushBootStrap.class) {
				return new PushBootStrap();
			}
		}
		return push;
	}

	public void start(Context context) {
		// connect
		applicationContext = context.getApplicationContext();
		this.connServer();
	}

	/**
	 * 连接服务器
	 */
	private void connServer() {

//		// 检测是否启动
		if (this.checkServiceIsRunning()) {
			MessageDispacher.getInstance(applicationContext).connect(PushBootStrap.host,
					PushBootStrap.port);
		} else {
			Intent intent = new Intent(applicationContext, MessageService.class);
			applicationContext.startService(intent);
		}
	}

	private boolean checkServiceIsRunning() {
		return ServiceUtil.isServiceRunning(applicationContext,
				serviceName);
	}

}
