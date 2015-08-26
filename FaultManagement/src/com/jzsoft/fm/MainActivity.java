package com.jzsoft.fm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
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
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.jzsoft.fm.conf.Config;
import com.jzsoft.fm.db.DBManager;
import com.jzsoft.fm.model.Job;

public class MainActivity extends ActionBarActivity {
	private ListView jobListView;
	private SimpleAdapter simpleAdapter;
	private List<Map<String, Object>> dataSimpleAdapter = new ArrayList<Map<String, Object>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
			Locale.CHINA);
	private String position;
	private ConnectivityManager cm;

	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;

	private String workTicketNumber;

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			// StringBuffer sb = new StringBuffer(256);
			// sb.append("time : ");
			// sb.append(location.getTime());
			// sb.append("\nerror code : ");
			// sb.append(location.getLocType());
			// sb.append("\nlatitude : ");
			// sb.append(location.getLatitude());
			// sb.append("\nlontitude : ");
			// sb.append(location.getLongitude());
			// sb.append("\nradius : ");
			// sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				// sb.append("\nspeed : ");
				// sb.append(location.getSpeed());
				// sb.append("\nsatellite : ");
				// sb.append(location.getSatelliteNumber());
				// sb.append("\ndirection : ");
				// sb.append("\naddr : ");
				// sb.append(location.getAddrStr());
				// sb.append(location.getDirection());
				new ChangePosition().execute(Config.CHANGEPOSITION
						+ location.getLatitude() + ","
						+ location.getLongitude() + Config.FROM + "2"
						+ Config.TO + "5" + Config.AK
						+ Config.BAIDU_WEB_API_KEY);
				Toast.makeText(
						getApplicationContext(),
						"地址：" + position + "(经度：" + location.getLongitude()
								+ ",维度：" + location.getLatitude() + ",精度："
								+ location.getRadius() + ")", Toast.LENGTH_LONG)
						.show();
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				// sb.append("\naddr : ");
				// sb.append(location.getAddrStr());
				// // 运营商信息
				// sb.append("\noperationers : ");
				// sb.append(location.getOperators());
				position = location.getAddrStr();
				Toast.makeText(
						getApplicationContext(),
						"地址：" + location.getAddrStr() + "(经度："
								+ location.getLongitude() + ",维度："
								+ location.getLatitude() + ",精度："
								+ location.getRadius() + ")", Toast.LENGTH_LONG)
						.show();
			}
			// logMsg(sb.toString());
			// Log.i("BaiduLocationApiDem", sb.toString());
		}
	}

	/**
	 * 显示请求字符串
	 * 
	 * @param str
	 */
	public void logMsg(String str) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		workTicketNumber = intent.getStringExtra("workTicketNumber");
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		setContentView(R.layout.activity_main);
		createJobList();
		getLocation();
		mLocationClient.start();
	}

	private DBManager dbManager;

	private List<Map<String, Object>> getData() {
		dbManager = new DBManager(this);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Job job : dbManager.query(workTicketNumber)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("_id", job.getId());
			map.put("jobName", job.getJobName());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm",
					Locale.CHINA);
			map.put("startTime", format.format(job.getStartTime()));
			list.add(map);
		}
		dbManager.closeDB();
		return list;
	}

	private void createJobList() {
		jobListView = (ListView) findViewById(R.id.jobListView);
		jobListView
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.setHeaderTitle("执行操作");
						menu.add(0, 3, 0, "编辑");
						menu.add(0, 4, 0, "删除");
					}
				});

		dataSimpleAdapter = getData();
		simpleAdapter = new SimpleAdapter(this, dataSimpleAdapter,
				R.layout.joblist, new String[] { "jobName", "startTime" },
				new int[] { R.id.jobNameView, R.id.startTimeView });
		jobListView.setAdapter(simpleAdapter);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int position = contextMenuInfo.position;
		switch (item.getItemId()) {
		case 3:
			break;
		case 4:
			dbManager = new DBManager(MainActivity.this);
			dbManager.deleteOldJob(dataSimpleAdapter.get(position).get("_id")
					.toString());
			dbManager.closeDB();
			dataSimpleAdapter.clear();
			dataSimpleAdapter.addAll(getData());
			simpleAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class UpdateWitcket extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			HttpGet httpRequest = new HttpGet(Config.SERVERURL
					+ "/workticket/get/workTicketNumber/" + workTicketNumber);
			httpRequest.addHeader("Content-Type", "application/json");
			httpRequest.addHeader("charset", HTTP.UTF_8);
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					JSONObject jsonObject = new JSONObject(
							EntityUtils.toString(httpResponse.getEntity()));
					jsonObject.remove("state");
					jsonObject.put("state", params[0]);
					HttpClient client = new DefaultHttpClient();
					HttpPut httpPut = new HttpPut(Config.SERVERURL
							+ "/workticket/put/workTicketNumber/"
							+ workTicketNumber);
					httpPut.setEntity(new StringEntity(jsonObject.toString()));
					httpPut.addHeader("Content-Type", "application/json");
					httpPut.addHeader("charset", HTTP.UTF_8);
					client.execute(httpPut);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onCancelled(Object result) {
			super.onCancelled(result);
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.newJob) {
			Intent intent = new Intent();
			intent.putExtra("position", position);
			intent.putExtra("workTicketNumber", workTicketNumber);
			intent.setClassName("com.jzsoft.fm", "com.jzsoft.fm.StartWork");
			new UpdateWitcket().execute("1");
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		dataSimpleAdapter.clear();
		dataSimpleAdapter.addAll(getData());
		simpleAdapter.notifyDataSetChanged();
	}

	private void getLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setIsNeedAddress(true);
		option.setNeedDeviceDirect(true);
		option.setCoorType("gcj02");// 返回的定位结果是百度经纬度，默认值gcj02
		int span = 8000;
		option.setScanSpan(span);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}

	class Geocoding extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			HttpGet httpRequest = new HttpGet(params[0].toString());
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String strResult = EntityUtils.toString(httpResponse
							.getEntity());
					return strResult;
				} else {
					return "请求出错";
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onCancelled(Object result) {
			super.onCancelled(result);
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			try {
				JSONObject jsonObject1 = new JSONObject(result.toString());
				JSONObject jsonObject2 = jsonObject1.getJSONObject("result");
				JSONObject location = jsonObject2.getJSONObject("location");
				// Toast.makeText(
				// getApplicationContext(),
				// "地址：" + jsonObject2.getString("formatted_address")
				// + "(经度:" + location.getString("lng") + ",维度:"
				// + location.getString("lat") + ")",
				// Toast.LENGTH_LONG).show();
				position = jsonObject2.getString("formatted_address");
			}

			catch (JSONException e) {
				e.printStackTrace();
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

	class ChangePosition extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			HttpGet httpRequest = new HttpGet(params[0].toString());
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String strResult = EntityUtils.toString(httpResponse
							.getEntity());
					return strResult;
				} else {
					return "请求出错";
				}
			}

			catch (ClientProtocolException e) {

			}

			catch (IOException e) {
				e.printStackTrace();

			}
			return null;
		}

		@Override
		protected void onCancelled(Object result) {
			super.onCancelled(result);
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			try {
				JSONArray jsonArray = new JSONObject(result.toString())
						.getJSONArray("result");
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
					builder.append(jsonObject2.getString("y")); // 获取数据
					builder.append(",");
					builder.append(jsonObject2.getString("x"));
				}
				new Geocoding().execute(Config.GEOCODER
						+ Config.BAIDU_WEB_API_KEY + "&location="
						+ builder.toString() + "&output=json&pois=0");
			}

			catch (JSONException e) {
				e.printStackTrace();
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

	public boolean isNetworkAvailable() {
		cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
			return false;
		} else {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					// Log.d(Config.LOG_TAG, info[i].getState().name());
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean is3g() {
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	public boolean isWifi() {
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	@Override
	protected void onStop() {
		mLocationClient.stop();
		super.onStop();
	}
}
