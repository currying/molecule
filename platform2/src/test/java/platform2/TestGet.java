package platform2;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestGet {
	private static String GET = "http://192.168.111.102:8080/platform2/rest/workticket/get/workTicketNumber/20140923152243";
	private static String PUT = "http://192.168.111.102:8080/platform2/rest/workticket/put/workTicketNumber/20140923152243";
	private static String POST = "http://www.wilmar-cn.com/paihangbang/Shop.aspx";
	static Date now = new Date();
	static Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String JSON = "{\"pid\": \"013\",\"ip\": \"10.1.1.10\",\"act\": \"cktp\"}";

	public static void main(String[] args) {
		TestGet t = new TestGet();
		System.out.println(t.post());
	}

	public String get() {
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target(GET);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		String value = response.readEntity(String.class);
		response.close();
		return value;
	}

	public String put() {
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target(PUT);
		Response response = target.request().put(
				Entity.json(get().replace("设备故障，单机不能运行", "设备故障，单机不能运行。")));
		String value = response.readEntity(String.class);
		response.close();
		return value;
	}

	public String post() {
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target(POST);
		Response response = target.request()
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(JSON));
		String value = response.readEntity(String.class);
		response.close();
		return value;
	}
}
