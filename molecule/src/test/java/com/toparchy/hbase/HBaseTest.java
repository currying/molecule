//package com.toparchy.hbase;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.MasterNotRunningException;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.ZooKeeperConnectionException;
//import org.apache.hadoop.hbase.client.Admin;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.HTableInterface;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.client.Table;
//
//public class HBaseTest {
//
//	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
//		String tableName = "users";
//		String familyName = "info";
//		Configuration conf = getHBaseConfig(1);
//		// 创建表
//		Connection connection = ConnectionFactory.createConnection(conf);
//		Admin admin = connection.getAdmin();
//
//		if (!admin.isTableAvailable(TableName.valueOf(tableName))) {
//			HTableDescriptor hbaseTable = new HTableDescriptor(TableName.valueOf(tableName));
//			hbaseTable.addFamily(new HColumnDescriptor("info"));
//			admin.createTable(hbaseTable);
//		}
//
//		Table table = connection.getTable(TableName.valueOf(tableName));
//
//		// 多条插入
//		List<Put> list = new ArrayList<Put>();
//		Put p = null;
//		for (int i = 0; i < 10000000; i++) {
//			p = new Put(("rowkey" + i).getBytes());
//			p.addColumn(familyName.getBytes(), "name".getBytes(), "wangwu".getBytes());
//			p.addColumn(familyName.getBytes(), "sex".getBytes(), "male".getBytes());
//			p.addColumn(familyName.getBytes(), "city".getBytes(), "beijing".getBytes());
//			p.addColumn(familyName.getBytes(), "age".getBytes(), "25".getBytes());
//			table.put(p);
//			System.out.println("成功插入" + p);
//		}
//	}
//
//	public static Configuration getHBaseConfig(int flag) {
//		Configuration conf = new Configuration();
//		if (flag > 0) {
//			// 集群
//			conf.set("fs.defaultFS", "hdfs://hadoop1:9000/");
//			conf.set("mapreduce.framework.name", "yarn");
//			conf.set("mapred.job.tracker", "hadoop1:9001");
//			conf.set("hbase.zookeeper.quorum", "hadoop1,hadoop2,hadoop3");
//		} else {
//			// 单机
//			conf.set("fs.defaultFS", "hdfs://ubuntu:9000/");
//			conf.set("mapreduce.framework.name", "local");
//			conf.set("mapred.job.tracker", "ubuntu:9001");
//			conf.set("hbase.zookeeper.quorum", "ubuntu");
//		}
//
//		return conf;
//	}
//}