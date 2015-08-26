package com.jzsoft.fm;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.jzsoft.fm.conf.Config;

public class AudioRecordActivity extends Activity {
	private String audioName = "";
	private RecordButton recordButton;
	private MediaRecorder mRecorder = null;
	private RelativeLayout rl;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rl = new RelativeLayout(this);
		recordButton = new RecordButton(this);
		recordButton.setImageResource(android.R.drawable.ic_media_play);
		RelativeLayout.LayoutParams recordButtonLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		recordButtonLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL,
				RelativeLayout.TRUE);
		recordButtonLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,
				RelativeLayout.TRUE);
		rl.addView(recordButton, recordButtonLayoutParams);
		setContentView(rl);
	}

	class RecordButton extends ImageButton {
		boolean mStartRecording = true;

		OnClickListener clicker = new OnClickListener() {
			public void onClick(View v) {
				onRecord(mStartRecording);
				if (mStartRecording) {
					setImageResource(android.R.drawable.ic_media_pause);
				} else {
					setImageResource(android.R.drawable.ic_media_play);
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

	private void onRecord(boolean start) {
		if (start) {
			startRecording();
		} else {
			stopRecording();
		}
	}

	private void startRecording() {
		File dir = new File(Config.AUDIOPATH);
		if (!dir.exists()) {
			dir.mkdir();
		}
		audioName = UUID.randomUUID().toString() + ".3gp";
		mRecorder = new MediaRecorder();
		// 设置音源为Micphone
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 设置封装格式
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(Config.AUDIOPATH + audioName);
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

		Intent intent = getIntent();
		intent.putExtra("audioName", audioName);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}