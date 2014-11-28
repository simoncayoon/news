package com.eteng.govnews;

import org.json.JSONArray;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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

public class ImpNoticFragment extends Fragment implements OnItemClickListener{

	private ListView impoListView;
	
	/** 工具类 **/
	private JsonObjectRequest jsonObjRequest;
	private RequestQueue mVolleyQueue;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mVolleyQueue = Volley.newRequestQueue(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		DebugFlags.logD("test", "fragment 调用!");
		View view = inflater.inflate(R.layout.data_list_view, null);
		impoListView = (ListView) view.findViewById(R.id.news_list_view);
		getNews();
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(impoListView);// 先移除
		}
		return view ;
	}
	
	/**
	 * 获取网络新闻列表
	 */
	private void getNews() {
		DebugFlags.logD("test", "获取新闻内容");
		String url = Constants.WS_ADDRESS;// 接口地址
		Uri.Builder builder = Uri.parse(url).buildUpon();
		builder.appendQueryParameter("PageIndex", "1");
		builder.appendQueryParameter("PageSize", "20");
		builder.appendQueryParameter("type", "2");
		jsonObjRequest = new JsonObjectRequest(Request.Method.GET,
				builder.toString(), null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject respon) {
						DebugFlags.logD("test", respon.toString());
						JSONArray array;
//						try {
//							array = new JSONArray(respon.getString("Message"));
//							JSONObject content = new JSONObject(
//									array.getString(0));
//							// initData(content);
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						
						if( error instanceof NetworkError) {
							DebugFlags.logD("test", "NetworkError");
						} else if( error instanceof ClientError) { 
							DebugFlags.logD("test", "ClientError");
						} else if( error instanceof ServerError) {
							DebugFlags.logD("test", "ServerError");
						} else if( error instanceof AuthFailureError) {
							DebugFlags.logD("test", "AuthFailureError");
						} else if( error instanceof ParseError) {
							DebugFlags.logD("test", "ParseError");
						} else if( error instanceof NoConnectionError) {
							DebugFlags.logD("test", "NoConnectionError");
						} else if( error instanceof TimeoutError) {
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
}
