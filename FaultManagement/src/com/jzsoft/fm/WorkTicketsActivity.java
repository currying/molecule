package com.jzsoft.fm;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jzsoft.fm.conf.Config;

public class WorkTicketsActivity extends ActionBarActivity {
	private ListView workTicketListView;
	private SimpleAdapter simpleAdapter;
	private List<Map<String, Object>> dataSimpleAdapter = new ArrayList<Map<String, Object>>();
	private ConnectivityManager cm;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
			Locale.CHINA);
	private int pic[] = new int[] { R.drawable.state1, R.drawable.state2 };
	private TelephonyManager telephonyManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_worktickers);
		telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		createWorkTicketList();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		new GetWitckets().execute(Config.SERVERURL + "/workticket/get/member/"
				+ telephonyManager.getLine1Number().replace("+86", ""));
	}

	private final Timer timer = new Timer();
	private TimerTask task;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			new GetWitckets().execute(Config.SERVERURL
					+ "/workticket/get/member/"
					+ telephonyManager.getLine1Number().replace("+86", ""));
			super.handleMessage(msg);
		}
	};

	private void createWorkTicketList() {
		workTicketListView = (ListView) findViewById(R.id.workTicketList);
		simpleAdapter = new SimpleAdapter(this, dataSimpleAdapter,
				R.layout.workticketlist, new String[] { "workTicketState",
						"workTicketNumber", "workTicketStartTime",
						"workTicketDescription" }, new int[] {
						R.id.workTicketState, R.id.workTicketNumberView,
						R.id.startTimeView, R.id.workTicketDescription });
		workTicketListView.setAdapter(simpleAdapter);
		workTicketListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("workTicketNumber",
						((TextView) ((LinearLayout) ((LinearLayout) view)
								.getChildAt(1)).getChildAt(0)).getText());
				intent.setClassName("com.jzsoft.fm",
						"com.jzsoft.fm.MainActivity");
				startActivity(intent);
			}
		});
		task = new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};
		timer.schedule(task, 0, 1000 * 60);
	}

	class GetWitckets extends AsyncTask<Object, Object, Object> {
		ByteArrayBuffer bt = new ByteArrayBuffer(8192);
		byte[] tmp = new byte[8192];

		@Override
		protected Object doInBackground(Object... params) {
			HttpGet httpRequest = new HttpGet(params[0].toString());
			httpRequest.addHeader("Content-Type", "application/json");
			httpRequest.addHeader("charset", HTTP.UTF_8);
			httpRequest.addHeader("Accept-Encoding", "gzip,deflate");
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpParams httpParams = httpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
				HttpConnectionParams.setSoTimeout(httpParams, 5000);
				HttpResponse httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// String strResult = EntityUtils.toString(
					// httpResponse.getEntity(), "UTF-8");
					GZIPInputStream gis = new GZIPInputStream(
							(httpResponse.getEntity().getContent()));
					int l;
					while ((l = gis.read(tmp)) != -1) {
						bt.append(tmp, 0, l);
					}
					return new String(bt.toByteArray(), "UTF-8");
				} else {
					return "请求出错";
				}
			} catch (ClientProtocolException e) {
				return Config.CLIENTPROTOCOLEXCEPTION;
			} catch (ConnectTimeoutException e) {
				return Config.CONNECTTIMEOUTEXCEPTION;
			} catch (IOException e) {
				return Config.IOEXCEPTION;
			}
		}

		@Override
		protected void onCancelled(Object result) {
			super.onCancelled(result);
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			dataSimpleAdapter.clear();
			if (simpleAdapter != null) {
				simpleAdapter.notifyDataSetChanged();
			}
			if (result instanceof Integer) {
				switch (Integer.valueOf(result.toString())) {
				case 1:
					Toast.makeText(getApplicationContext(),
							"连接服务器超时，请联系服务器管理员。", Toast.LENGTH_LONG).show();
					break;
				case 2:
					Toast.makeText(getApplicationContext(),
							"http请求也不符合http协议。", Toast.LENGTH_LONG).show();
					break;
				case 3:
					Toast.makeText(getApplicationContext(),
							"IO异常。有可能没有连接网络，请检查您的手机网络连接。", Toast.LENGTH_LONG)
							.show();
					break;
				}
			} else if (result instanceof String) {
				try {
					JSONArray jsonArray = new JSONArray((String) result);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
						Map<String, Object> map = new HashMap<String, Object>();
						switch (Integer.valueOf(jsonObject.get("state")
								.toString())) {
						case 0:
							map.put("workTicketState", pic[0]);
							break;
						case 1:
							map.put("workTicketState", pic[1]);
							break;
						}
						map.put("workTicketId", jsonObject.get("id"));
						map.put("workTicketNumber",
								jsonObject.get("workTicketNumber"));
						map.put("workTicketStartTime",
								sdf.format(jsonObject.get("startTime")));
						map.put("workTicketDescription",
								jsonObject.get("description"));
						dataSimpleAdapter.add(map);
						simpleAdapter.notifyDataSetChanged();
					}
				}

				catch (JSONException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Object... values) {
			super.onProgressUpdate(values);
		}

	}

	boolean isExit;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			System.exit(0);
		}
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}

	};

	public boolean isNetworkAvailable() {
		cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
			return false;
		} else {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
