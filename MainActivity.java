package com.example.musicplay;

import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvTitle,tvAuthor;
	private Button btnPlay,btnStop;
	public static final String CONTROL="com.example.musicplay.control";
	public static final String UPDATE="com.example.musicplay.update";
	//定义广播接收器，控制播放，暂停
	ActivityReceiver activityReceiver;
	int status=0x11;
	//定义播放状态，0x11,未播放；0x12，正在播放；0x13，暂停
	String []titleStrs=new String []{
			"老男孩","春天里","在路上"};
	String [] authorStrs=new String[]{
			"筷子兄弟","汪峰","刘欢"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnPlay=(Button) this.findViewById(R.id.btnPlay);
		btnStop=(Button) this.findViewById(R.id.btnStop);
		tvTitle=(TextView) this.findViewById(R.id.tvTitle);
		tvAuthor=(TextView) this.findViewById(R.id.tvAuthor);
		
		
		btnPlay.setOnClickListener((OnClickListener) this);
		btnStop.setOnClickListener((OnClickListener) this);
		//为两个Button添加监听器
		
		 activityReceiver = new ActivityReceiver();
			// 创建IntentFilter
			IntentFilter filter = new IntentFilter(UPDATE);
			// 注册BroadcastReceiver
			registerReceiver(activityReceiver, filter);
			Intent intent = new Intent(this, MusicService.class);
			startService(intent);
			// 启动后台Service
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        
        

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
