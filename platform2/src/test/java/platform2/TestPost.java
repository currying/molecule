package platform2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestPost {
	public static void main(String[] args) {
		try {
			Thread t1 = new Thread(new MutliThread());
			t1.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Post {
	private static URI url = URI
			.create("http://www.wilmar-cn.com/paihangbang/Shop.aspx");
	int p = 0;

	public void tp(int l1, int l2, int l3, int l4) {
		try {
			HttpPost httppost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pid", "013"));
			params.add(new BasicNameValuePair("ip", l1 + "." + l2 + "." + l3
					+ "." + l4));
			params.add(new BasicNameValuePair("act", "cktp"));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = new DefaultHttpClient().execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				System.out.print(l1 + "." + l2 + "." + l3 + "." + l4);
				System.out.print(" " + result);
				System.out.println(" " + p++);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (NoHttpResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class MutliThread implements Runnable {
	int l1 = 0;
	int l2 = 0;
	int l3 = 0;
	int l4 = 0;
	private static String[][] LL = { { "58", "100" }, { "223", "2" },
			{ "58", "210" }, { "58", "101" }, { "223", "4" }, { "218", "2" },
			{ "218", "4" }, { "202", "99" }, { "202", "100" }, { "210", "47" },
			{ "202", "104" }, { "202", "101" }, { "212", "64" },
			{ "210", "26" }, { "58", "216" }, { "210", "48" }, { "58", "215" },
			{ "210", "25" }, { "58", "194" }, { "202", "103" },
			{ "218", "93" }, { "58", "212" }, { "58", "211" }, { "210", "35" },
			{ "58", "208" }, { "218", "94" }, { "58", "213" }, { "210", "36" },
			{ "58", "223" }, { "58", "209" }, { "58", "217" }, { "58", "218" },
			{ "58", "200" }, { "222", "247" }, { "58", "220" },
			{ "58", "221" }, { "58", "222" } };

	MutliThread() {
	}

	public void run() {
		Post post = new Post();
		for (int i = 0; i < 20; i++)
			for (String[] a : LL) {
				int ran = (int) (Math.random() * 200);
				for (int ii = ran; ii <= ran + 5; ii++) {
					int ran2 = (int) (Math.random() * 200);
					for (int iii = ran2; iii <= ran2 + 9; iii++) {
						post.tp(Integer.valueOf(a[0]), Integer.valueOf(a[1]),
								ii, iii);
					}
				}
			}
	}
}