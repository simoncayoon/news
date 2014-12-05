package com.eteng.govnews;

import java.net.URLDecoder;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eteng.govnews.model.Constants;
import com.eteng.govnews.utils.DebugFlags;

public class DetailCotentActivity extends Activity {

	private String category = "";
	private String title = "";
	private String content = "";
	private String id = "";
	private TextView titleView;
	private TextView contentView;
	private ImageView naviView;
	private ProgressDialog mProgress;

	/** 工具类 **/
	private JsonObjectRequest jsonObjRequest;
	private RequestQueue mVolleyQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);	
		mVolleyQueue = Volley.newRequestQueue(this);
		contentView = (TextView) findViewById(R.id.contentView);
		titleView = (TextView) findViewById(R.id.titleView);
		naviView = (ImageView) findViewById(R.id.navi_image_view);
		id = getIntent().getStringExtra(Constants.INTENT_ID_KEY);
		category = getIntent().getStringExtra(Constants.INTENT_CATEGORY);
		if(category.equals(Constants.TYPE_GOV)){
			naviView.setImageResource(R.drawable.gov_navi_detail_img);
		}else if(category.equals(Constants.TYPE_GZ)){
			naviView.setImageResource(R.drawable.gz_navi_detail_img);
		}else if(category.equals(Constants.TYPE_NOTICE)){
			naviView.setImageResource(R.drawable.impo_navi_detail_img);
		}
		getDetail();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	private void getDetail(){
		showProgress();
		String url = Constants.WS_ADDRESS + Constants.GET_DETAIL;// 接口地址
		Uri.Builder builder = Uri.parse(url).buildUpon();
		builder.appendQueryParameter("id", id);
		builder.appendQueryParameter("type", category);
		jsonObjRequest = new JsonObjectRequest(Request.Method.GET,
				builder.toString(), null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject respon) {

						try {
							if (respon.getString("code").equals("0")) {// 返回数据正常

								JSONArray contentArray = new JSONArray(
										respon.getString("msg"));
								parse(contentArray);
								titleView.setText(title);
								contentView.setText(Html.fromHtml(content));
								contentView.setMovementMethod(ScrollingMovementMethod.getInstance());
							} else if (respon.getString("code").equals("99")) {// 服务端错误

							} else {// 请求参数错误

							}

						} catch (Exception e) {

							e.printStackTrace();
						} finally {
							stopProgress();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						stopProgress();
						if (error instanceof NetworkError) {
							DebugFlags.logD("test", "NetworkError");
						} else if (error instanceof ClientError) {
							DebugFlags.logD("test", "ClientError");
						} else if (error instanceof ServerError) {
							DebugFlags.logD("test", "ServerError");
						} else if (error instanceof AuthFailureError) {
							DebugFlags.logD("test", "AuthFailureError");
						} else if (error instanceof ParseError) {
							DebugFlags.logD("test", "ParseError");
						} else if (error instanceof NoConnectionError) {
							DebugFlags.logD("test", "NoConnectionError");
						} else if (error instanceof TimeoutError) {
							DebugFlags.logD("test", "TimeoutError");
						}
					}
				});
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

		jsonObjRequest.setTag("MainAvtivity");
		mVolleyQueue.add(jsonObjRequest);
	}
	
	/**
	 * 将JSON数组解析成相应model数组
	 * 
	 * @param jsonArray
	 * @throws Exception
	 */
	private void parse(JSONArray jsonArray) throws Exception {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject item = new JSONObject(jsonArray.getString(i));
			String newsContent = URLDecoder.decode(item.getString("ntf_ctt"),
					"UTF-8");
			this.title = item.getString("ntf_ttl");
			this.content = newsContent.replaceAll("<\\s*img\\s+([^>]*)\\s*>","");
		}
	}
	
	private void showProgress() {
		mProgress = ProgressDialog.show(DetailCotentActivity.this, "", "加载中...");
	}

	private void stopProgress() {
		mProgress.cancel();
	}
}
