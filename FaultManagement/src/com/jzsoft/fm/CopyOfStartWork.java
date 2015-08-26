package com.jzsoft.fm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzsoft.fm.conf.Config;
import com.jzsoft.fm.db.DBManager;
import com.jzsoft.fm.model.Job;

public class CopyOfStartWork extends ActionBarActivity {
	private List<ImageView> imageViews = new ArrayList<ImageView>();
	private List<String> images = new ArrayList<String>();
	private TextView positionTextView;
	private EditText positionEditText;
	private TextView jobNameTextView;
	private EditText jobNameEditText;
	private TextView photoTextView;
	private ImageButton photoButton;
	private TextView audioTextView;
	private RecordButton audioButton;
	private TextView jobRecordTextView;
	private EditText jobRecordEditText;
	private RelativeLayout rl;
	private int i = 100;
	private Job job;
	private Button saveButton;
	// private String jobName;
	private String position;
	private String workTicketNumber;

	private String audioName = "";

	private MediaRecorder mRecorder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		position = intent.getStringExtra("position");
		workTicketNumber = intent.getStringExtra("workTicketNumber");
		createNewJob();
		rl = new RelativeLayout(this);

		positionTextView = new TextView(this);
		positionTextView.setId(1);
		positionTextView.setText("当前位置：");
		RelativeLayout.LayoutParams positionTextViewLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		positionTextViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		positionTextViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		positionTextViewLayoutParams.topMargin = 30;
		positionTextViewLayoutParams.leftMargin = 30;
		rl.addView(positionTextView, positionTextViewLayoutParams);

		positionEditText = new EditText(this);
		positionEditText.setId(2);
		positionEditText.setEms(15);
		positionEditText.setText(position);
		positionEditText.setTextSize(16);
		positionEditText.setInputType(InputType.TYPE_CLASS_TEXT);
		RelativeLayout.LayoutParams positionEditTextLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		positionEditTextLayoutParams.addRule(RelativeLayout.RIGHT_OF,
				positionTextView.getId());
		positionEditTextLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,
				positionTextView.getId());
		positionEditTextLayoutParams.rightMargin = 30;
		rl.addView(positionEditText, positionEditTextLayoutParams);

		jobNameTextView = new TextView(this);
		jobNameTextView.setId(3);
		jobNameTextView.setText("工作名：");
		RelativeLayout.LayoutParams jobNameTextViewLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		jobNameTextViewLayoutParams.addRule(RelativeLayout.BELOW,
				positionTextView.getId());
		jobNameTextViewLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT,
				positionTextView.getId());
		jobNameTextViewLayoutParams.topMargin = 30;
		rl.addView(jobNameTextView, jobNameTextViewLayoutParams);

		jobNameEditText = new EditText(this);
		jobNameEditText.setId(4);
		jobNameEditText.setEms(15);
		jobNameEditText.setTextSize(16);
		jobNameEditText.setInputType(InputType.TYPE_CLASS_TEXT);
		RelativeLayout.LayoutParams jobNameEditTextLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		jobNameEditTextLayoutParams.addRule(RelativeLayout.RIGHT_OF,
				jobNameTextView.getId());
		jobNameEditTextLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,
				jobNameTextView.getId());
		jobNameEditTextLayoutParams.rightMargin = 30;
		rl.addView(jobNameEditText, jobNameEditTextLayoutParams);

		photoTextView = new TextView(this);
		photoTextView.setId(5);
		photoTextView.setText("现场取景：");
		RelativeLayout.LayoutParams photoTextViewLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		photoTextViewLayoutParams.addRule(RelativeLayout.BELOW,
				jobNameTextView.getId());
		photoTextViewLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT,
				jobNameTextView.getId());
		photoTextViewLayoutParams.topMargin = 30;
		rl.addView(photoTextView, photoTextViewLayoutParams);

		photoButton = new ImageButton(this);
		photoButton.setId(6);
		photoButton.setImageResource(android.R.drawable.ic_menu_camera);
		RelativeLayout.LayoutParams photoButtonLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		photoButtonLayoutParams.addRule(RelativeLayout.BELOW,
				photoTextView.getId());
		photoButtonLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT,
				photoTextView.getId());
		rl.addView(photoButton, photoButtonLayoutParams);

		photoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CopyOfStartWork.this, CameraActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		audioTextView = new TextView(this);
		audioTextView.setId(7);
		audioTextView.setText("现场录音：");
		RelativeLayout.LayoutParams audioTextViewLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		audioTextViewLayoutParams.addRule(RelativeLayout.BELOW,
				photoButton.getId());
		audioTextViewLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT,
				photoTextView.getId());
		audioTextViewLayoutParams.topMargin = 30;
		rl.addView(audioTextView, audioTextViewLayoutParams);

		audioButton = new RecordButton(this);
		audioButton.setId(8);
		audioButton.setImageResource(android.R.drawable.ic_media_play);
		RelativeLayout.LayoutParams audioButtonLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		audioButtonLayoutParams.addRule(RelativeLayout.BELOW,
				audioTextView.getId());
		audioButtonLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT,
				audioTextView.getId());
		rl.addView(audioButton, audioButtonLayoutParams);

		jobRecordTextView = new TextView(this);
		jobRecordTextView.setId(9);
		jobRecordTextView.setText("工作描述：");
		RelativeLayout.LayoutParams jobRecordTextViewLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		jobRecordTextViewLayoutParams.addRule(RelativeLayout.BELOW,
				audioButton.getId());
		jobRecordTextViewLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT,
				audioTextView.getId());
		jobRecordTextViewLayoutParams.topMargin = 30;
		rl.addView(jobRecordTextView, jobRecordTextViewLayoutParams);

		jobRecordEditText = new EditText(this);
		jobRecordEditText.setId(10);
		jobRecordEditText.setEms(15);
		jobRecordEditText.setTextSize(16);
		jobRecordEditText.setGravity(Gravity.TOP);
		jobRecordEditText.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
				| InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		RelativeLayout.LayoutParams jobRecordEditTextLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		jobRecordEditTextLayoutParams.addRule(RelativeLayout.RIGHT_OF,
				jobRecordTextView.getId());
		jobRecordEditTextLayoutParams.addRule(RelativeLayout.ALIGN_TOP,
				jobRecordTextView.getId());
		jobRecordEditTextLayoutParams.rightMargin = 30;
		rl.addView(jobRecordEditText, jobRecordEditTextLayoutParams);

		saveButton = new Button(this);
		saveButton.setId(11);
		saveButton.setText("提交");
		RelativeLayout.LayoutParams saveButtonLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		saveButtonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		saveButtonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rl.addView(saveButton, saveButtonLayoutParams);
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveJobInfo();
				finish();
			}
		});
		setContentView(rl);
	}

	private void onRecord(boolean start) {
		if (start) {
			startRecording();
		} else {
			stopRecording();
		}
	}

	private void startRecording() {
		audioName = UUID.randomUUID().toString() + ".3gp";
		mRecorder = new MediaRecorder();
		// 设置音源为Micphone
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 设置封装格式
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(Config.PHOTOPATH + audioName);
		// 设置编码格式
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(Config.LOG_TAG, "prepare() failed");
		}
		mRecorder.start();
	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

	class RecordButton extends ImageButton {
		boolean mStartRecording = true;

		OnClickListener clicker = new OnClickListener() {
			public void onClick(View v) {
				onRecord(mStartRecording);
				if (mStartRecording) {
					setImageResource(android.R.drawable.ic_media_pause);
					saveButton.setEnabled(false);
				} else {
					setImageResource(android.R.drawable.ic_media_play);
					saveButton.setEnabled(true);
				}
				mStartRecording = !mStartRecording;
			}
		};

		public RecordButton(Context ctx) {
			super(ctx);
			setOnClickListener(clicker);
			setImageResource(android.R.drawable.ic_media_play);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (requestCode == 0) {
				Bundle bundle = data.getExtras();
				// 获得照片uri
				Uri uri = Uri.parse(bundle.getString("uriStr"));
				// 获得拍照时间
				long dateTaken = bundle.getLong("dateTaken");
				Bitmap cameraBitmap;
				try {
					cameraBitmap = MediaStore.Images.Media.getBitmap(
							getContentResolver(), uri);
					ImageView imageView = new ImageView(this);
					imageView.setId(i);
					RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
							ViewGroup.LayoutParams.WRAP_CONTENT,
							ViewGroup.LayoutParams.WRAP_CONTENT);

					if (imageViews.size() == 0) {
						lp.addRule(RelativeLayout.BELOW, photoTextView.getId());
						lp.addRule(RelativeLayout.ALIGN_LEFT,
								photoTextView.getId());
					} else {
						lp.addRule(RelativeLayout.RIGHT_OF,
								imageViews.get(imageViews.size() - 1).getId());
						lp.addRule(RelativeLayout.ALIGN_BOTTOM,
								imageViews.get(imageViews.size() - 1).getId());
						lp.leftMargin = 10;
					}
					imageView.setImageBitmap(cameraBitmap);
					imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
					imageView.setMaxWidth(200);
					imageView.setAdjustViewBounds(true);
					rl.addView(imageView, lp);
					RelativeLayout.LayoutParams photoButtonLayoutParams = new RelativeLayout.LayoutParams(
							ViewGroup.LayoutParams.WRAP_CONTENT,
							ViewGroup.LayoutParams.WRAP_CONTENT);
					photoButtonLayoutParams.addRule(RelativeLayout.RIGHT_OF,
							imageView.getId());
					photoButtonLayoutParams.addRule(
							RelativeLayout.ALIGN_BOTTOM, imageView.getId());
					photoButton.setLayoutParams(photoButtonLayoutParams);
					imageViews.add(imageView);
					images.add(bundle.getString("imageName"));
					i++;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private DBManager dbManager;

	private void createNewJob() {
		dbManager = new DBManager(this);
		job = new Job();
		job.setId(UUID.randomUUID().toString().replace("-", ""));
		job.setWorkTicketNumber(workTicketNumber);
		job.setJobName("new_work");
		job.setStartTime(new Date().getTime());
		job.setEndMark(0);
		job.setSyncMark(0);
		dbManager.add(job);
		dbManager.closeDB();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbManager.closeDB();// 释放数据库资源
	}

	private void saveJobInfo() {
		if (!jobNameEditText.getText().toString().equals(""))
			job.setJobName(jobNameEditText.getText().toString());
		job.setPhoto(images.toString());
		job.setJobRecord(jobRecordEditText.getText().toString());
		new AddJob().execute();
	}

	class AddJob extends AsyncTask<Object, Object, Object> {
		@Override
		protected Object doInBackground(Object... params) {
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", job.getId());
				jsonObject.put("jobName", jobNameEditText.getText().toString());
				jsonObject.put("workTicketNumber", workTicketNumber);
				jsonObject.put("position", position);
				jsonObject.put("startTime", job.getStartTime());
				jsonObject.put("jobRecord", jobRecordEditText.getText()
						.toString());
				JSONArray jsonArray = new JSONArray();
				for (String imageName : images) {
					File file = new File(Config.PHOTOPATH + imageName);
					FileInputStream inputFile = new FileInputStream(file);
					byte[] buffer = new byte[inputFile.available()];
					inputFile.read(buffer);
					inputFile.close();
					jsonArray.put(Base64.encodeToString(buffer, 0,
							buffer.length, Base64.DEFAULT));
				}
				jsonObject.put("image", jsonArray.toString());
				if (!audioName.equals("")) {
					File file = new File(Config.PHOTOPATH + audioName);
					FileInputStream inputFile = new FileInputStream(file);
					byte[] buffer = new byte[inputFile.available()];
					inputFile.read(buffer);
					inputFile.close();
					jsonObject.put("audio", Base64.encodeToString(buffer, 0,
							buffer.length, Base64.DEFAULT));
				} else {
					jsonObject.put("audio", "");
				}
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(Config.SERVERURL + "/job/post");
				httpPost.setEntity(new StringEntity(jsonObject.toString(),
						"UTF-8"));
				httpPost.addHeader("Content-Type", "application/json");
				httpPost.addHeader("Accept-Encoding", "gzip,deflate");
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String strResult = EntityUtils.toString(
							httpResponse.getEntity(), "UTF-8");
					return strResult;
				} else {
					return "请求出错";
				}
			} catch (JSONException e) {
				return Config.JSONEXCEPTION;
			} catch (ClientProtocolException e) {
				return Config.CLIENTPROTOCOLEXCEPTION;
			} catch (IOException e) {
				e.printStackTrace();
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
			} else {
				dbManager = new DBManager(CopyOfStartWork.this);
				dbManager.update(job);
				dbManager.closeDB();
			}
		}
	}

	@Override
	public void onBackPressed() {
		dbManager = new DBManager(CopyOfStartWork.this);
		dbManager.deleteOldJob(job.getId());
		dbManager.closeDB();
		super.onBackPressed();
	}
}
