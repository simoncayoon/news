package com.eteng.govnews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailCotentActivity extends Activity {

	private String category = "";
	private String title = "";
	private String content = "";
	private TextView contentView, titleView;
	private ImageView naviView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
		
		contentView = (TextView) findViewById(R.id.contentView);
		titleView = (TextView) findViewById(R.id.titleView);
		naviView = (ImageView) findViewById(R.id.navi_image_view);
		
		title = getIntent().getStringExtra("title");
		content = getIntent().getStringExtra("content");
		category = getIntent().getStringExtra("category");
		if(category.equals("FORM_IMPO")){
			
		}else if(category.equals("FORM_GOV")){
			
		}else if(category.equals("FROM_GZ")){
			
		}
		titleView.setText(title);
		contentView.setText(content);
	}
}
