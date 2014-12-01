package com.eteng.govnews;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.eteng.govnews.model.Constants;

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
		if(category.equals(Constants.TYPE_GOV)){
			naviView.setImageResource(R.drawable.gov_navi_detail_img);
		}else if(category.equals(Constants.TYPE_GZ)){
			naviView.setImageResource(R.drawable.gz_navi_detail_img);
		}else if(category.equals(Constants.TYPE_NOTICE)){
			naviView.setImageResource(R.drawable.impo_navi_detail_img);
		}
		titleView.setText(title);
		contentView.setText(Html.fromHtml(content));
		contentView.setMovementMethod(ScrollingMovementMethod.getInstance());
	}
}
