import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestPost {
	private static URI url = URI
			.create("http://www.wilmar-cn.com/paihangbang/Shop.aspx");
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
	int p = 0;

	public static void main(String[] args) {
		TestPost t = new TestPost();
		try {
			for (int i = 0; i < 20; i++)
				for (String[] a : LL) {
					int ran = (int) (Math.random() * 200);
					for (int ii = ran; ii <= ran + 5; ii++) {
						int ran2 = (int) (Math.random() * 200);
						for (int iii = ran2; iii <= ran2 + 9; iii++) {
							t.tp(Integer.valueOf(a[0]), Integer.valueOf(a[1]),
									ii, iii);
						}
					}
				}
			//
			// for (int l1 = 180; l1 <= 180; l1++) {
			// for (int l2 = 116; l2 <= 118; l2++) {
			// for (int l3 = 13; l3 <= 20; l3++) {
			// for (int l4 = 1; l4 <= 254; l4++) {
			// t.tp(l1, l2, l3, l4);
			// }
			// }
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tp(int l1, int l2, int l3, int l4) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		DefaultHttpClient client = new DefaultHttpClient();
		HttpHost proxy = new HttpHost("202.106.16.36", 3128);
		HttpPost targetHost = new HttpPost(url);
//		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		params.add(new BasicNameValuePair("pKey", "66216da174428001"));
		params.add(new BasicNameValuePair("id", "222.2.1.1"));
		params.add(new BasicNameValuePair("act", "cktp"));
		targetHost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		targetHost.setHeader("X-Forwarded-For", "202.106.16.36");
		HttpResponse response = client.execute(targetHost);

		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity());
			System.out.print(l1 + "." + l2 + "." + l3 + "." + l4);
			System.out.print(" " + result);
			System.out.println(" " + p++);
		}
	}
}
