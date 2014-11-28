package com.eteng.govnews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eteng.govnews.model.Constants;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GuiZhouFragment extends Fragment implements OnItemClickListener{

	private ListView gzListView;
	
	/** 工具类 **/
	private JsonObjectRequest jsonObjRequest;
	private RequestQueue mVolleyQueue;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mVolleyQueue = Volley.newRequestQueue(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.data_list_view, null);
		gzListView = (ListView) view.findViewById(R.id.news_list_view);
		gzListView.setBackgroundColor(Color.BLUE);
//		getNews();
		return view ;
	}
	
	/**
	 * 获取网络新闻列表
	 */
	private void getNews() {
		String url = Constants.WS_ADDRESS;// 接口地址
		Uri.Builder builder = Uri.parse(url).buildUpon();
		jsonObjRequest = new JsonObjectRequest(Request.Method.GET,
				builder.toString(), null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject respon) {
						JSONArray array;
						try {
							array = new JSONArray(respon.getString("Message"));
							JSONObject content = new JSONObject(
									array.getString(0));
							// initData(content);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						

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
