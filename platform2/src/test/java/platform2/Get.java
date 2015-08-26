package platform2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

public class Get {
	public static void main(String args[]) {
		try {
			Connection con;
			Statement stmt;
			ResultSet rs;
			// Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager
					.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
			con = java.sql.DriverManager
					.getConnection(
							"jdbc:mysql://127.0.0.1:3306/platform?characterEncoding=utf8",
							"root", "822005");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Job");
			while (rs.next()) {
				JSONArray jsArr = new JSONArray(rs.getString("image"));
				for (int i = 0; i < jsArr.length(); i++) {
					byte[] decoderBytes = new BASE64Decoder()
							.decodeBuffer(jsArr.getString(i));
					OutputStream out = new FileOutputStream("c:/test" + i
							+ ".jpg");
					out.write(decoderBytes);
					out.flush();
					out.close();
				}
				//
				// String data[] = rs.getString("image").replace("[", "")
				// .replace("]", "").split(",");
				// for (int i = 0; i < data.length; i++) {
				// byte[] decoderBytes = new BASE64Decoder()
				// .decodeBuffer(data[i].trim());
				// OutputStream out = new FileOutputStream("c:/test" + i
				// + ".jpg");
				// out.write(decoderBytes);
				// out.flush();
				// out.close();
				// }

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
