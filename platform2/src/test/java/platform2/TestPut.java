package platform2;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestPut {
	private static URI url = URI
			.create("http://192.168.111.102:8080/platform2/rest/worktickets/10");
	private static final String APPLICATION_JSON = "application/json";

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPut put = new HttpPut(url);
		try {
			StringEntity stringEntity = new StringEntity(new TestGet().get()
					.replace("设备故障，单机不能运行", "设备故障，单机不能运行。"), "UTF-8");
			stringEntity.setContentType(APPLICATION_JSON);
			put.setEntity(stringEntity);
			client.execute(put);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
