package com.toparchy.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HBaseTest {

	public static void main(String[] args) {
		HBaseTest object = new HBaseTest();
		object.createTable();
	}

	public void createTable() {
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "hadoop1");
		config.set("hbase.zookeeper.property.clientPort", "2181");

		Connection connection = null;
		Admin admin = null;

		try {
			connection = ConnectionFactory.createConnection(config);
			admin = connection.getAdmin();

			String tableName = "users";

			if (!admin.isTableAvailable(TableName.valueOf(tableName))) {
				HTableDescriptor hbaseTable = new HTableDescriptor(TableName.valueOf(tableName));
				hbaseTable.addFamily(new HColumnDescriptor("name"));
				hbaseTable.addFamily(new HColumnDescriptor("contact_info"));
				hbaseTable.addFamily(new HColumnDescriptor("personal_info"));
				admin.createTable(hbaseTable);
			} else
				System.out.println("表存在");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (admin != null) {
					admin.close();
				}

				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}