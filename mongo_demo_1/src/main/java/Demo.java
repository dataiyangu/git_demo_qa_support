import com.mongodb.*;
import com.mongodb.util.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Demo {

	public static void main(String[] args) throws Exception {

		InputStream resourceAsStream = Demo.class.getResourceAsStream("a.properties");
		Properties properties = new Properties();
		properties.load(resourceAsStream);
		String ip = properties.getProperty("mongo.ip");
		int port = Integer.parseInt(properties.getProperty("mongo.port"));
		String dbname = properties.getProperty("mongo.db");
		String colletion = properties.getProperty("mongo.colletion");

		// 链接一台服务器
		// Mongo mongo=new Mongo();//默认连本机127.0.0.1 端口为27017
		// Mongo mongo=new Mongo("127.0.0.1");//可以指定ip 端口默认为27017
		Mongo mg = new Mongo(ip, port);// 也可以指定ip及端口号
		// 链接多台服务器 Replication 同步数据缓解压力主从集群
		// List<ServerAddress> mongoHostList = new ArrayList<ServerAddress>();
		// mongoHostList.add(new ServerAddress("ip", port));
		// mongoHostList.add(new ServerAddress("ip", port));
		// mongoHostList.add(new ServerAddress("ip", port));
		// Mongo mg = new Mongo(mongoHostList);

		DB db = mg.getDB(dbname);// 链接数据库，无则创建
		// 如果添加安全则需要验证
		// boolean auth=db.authenticate("username", "password".toCharArray());
		// if (auth) {
		// //
		// }
		// 1.获取数据库信息
		// 获取所有数据库
		List<String> list = mg.getDatabaseNames();
		for (String string : list) {
			System.out.println(string);
		}
		// 获取所有集合
		Set<String> set = db.getCollectionNames();
		for (String string : set) {
			System.out.println(string);
		}
		// 获得集合 没有的话自动创建也可以使用下一个方法创建
		DBCollection dCollection = db.getCollection(colletion);

		// 2.添加操作
		// 第一种方式 最常用
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", 1);
		dbObject.put("name", "sen");
		dbObject.put("age", 21);
		dbObject.put("date", new Date());
		dCollection.save(dbObject);
		System.out.println("添加成功");
		// dCollection.insert(dbObject);
		// 第二种方式
//		BasicDBObjectBuilder b = BasicDBObjectBuilder.start();
//
//		b.add("_id", Tools.getNext(dCollection, "sen"));
//		b.add("name", "qiyu");
//		b.add("date", new Date());
//		dCollection.save(b.get());
		// dCollection.insert(b.get());
		// 第三种方式
//		String json = "{'_id':" + Tools.getNext(dCollection, "sen")
//				+ ",'name':'senssic','age':22,'date':" + "'"
//				+ new Date().toString() + "'}";
//		dbObject = (DBObject) JSON.parse(json);
//		dCollection.save(dbObject);
		// 3.查询操作
		// 查询集合中所有数据
//		DBCursor cur = dCollection.find();
//		while (cur.hasNext()) {
//			System.out.println(cur.next());
//		}
//		System.out.println(cur.count());
//		System.out.println(cur.getCursorId());
//		System.out.println(JSON.serialize(cur));
//		// 根据条件查询where _id=12 条件查询集合查询等和操作数据库差不多可以看下mongodb的数据库操作语法
//		cur = dCollection.find(new BasicDBObject("_id", 12));
//		while (cur.hasNext()) {
//			System.out.println(cur.next());
//		}
		// 查询_id>3 且只取5条
//		cur = dCollection.find(
//				new BasicDBObject("_id", new BasicDBObject("$gte", 3)))
//				.limit(5);
//		while (cur.hasNext()) {
//			System.out.println(cur.next());
//		}
//		// 查询id大于3且id降序 -1降序 1升序
//		cur = dCollection.find(
//				new BasicDBObject("_id", new BasicDBObject("$gte", 3))).sort(
//				new BasicDBObject("_id", -1));
//		while (cur.hasNext()) {
//			System.out.println(cur.next());
//		}
		// 查询id大于等于1的记录，跳过前5条记录并且只显示5条记录。相当
		// 于分页功能where id>5 and id<=10
//		cur = dCollection
//				.find(new BasicDBObject("_id", new BasicDBObject("$gte", 1)))
//				.skip(5).limit(5);
//		while (cur.hasNext()) {
//			System.out.println(cur.next());
//		}
		// 返回>1的记录条数
//		System.out
//				.println(dCollection.find(
//						new BasicDBObject("_id", new BasicDBObject("$gte", 1)))
//						.count());
//		// 查询一条数据
//		@SuppressWarnings("unchecked")
//		Map<String, Object> map = dCollection.findOne().toMap();
//
//		for (Map.Entry<String, Object> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + "--->" + entry.getValue());
//		}
		// dCollection.findOne(new BasicDBObject("_id",12));
		// dCollection.findOne(new BasicDBObject("_id", 12), new BasicDBObject(
		// "name", true));// 返回_id和name的值

		// 查询并删除
//		dCollection.findAndRemove(new BasicDBObject("_id", 12));
		// 查询并修改
////		dCollection.findAndModify(new BasicDBObject("_id", 10), // 查询_id=28的数据
//				new BasicDBObject("name", true), // 查询name属性
//				new BasicDBObject("age", true), // 按照age排序
//				false, // 查询到的记录是否删除，true表示删除
//				new BasicDBObject("date", new Date()), // 将date的值改为当前时间
//				true, // 是否返回新记录 true返回，false不返回
//				true // 如果没有查询到该数据是否插collection true入库，false不入
//		);
		// 4.更新
		// 替换更新 讲查找到的记录覆盖更新
//		System.out.println(dCollection.update(new BasicDBObject("_id", 10),
//				new BasicDBObject("city", "hefei")).getN());
//		// 局部更新 只是更新选定的字段，并不会覆盖更新
//		System.out.println(dCollection
//				.update(new BasicDBObject("_id", 3),
//						new BasicDBObject("$set", new BasicDBObject("city",
//								"chizhou")), false, // 如果数据库不存在，是否添加
//						true // 多条修改，false只修改一条记录
//				).getN());

		// 5.删除
//		dCollection.remove(new BasicDBObject("_id", "5")).getN();
//		// 移除id>=1的数据
//		dCollection.remove(
//				new BasicDBObject("_id", new BasicDBObject("$gte", 20))).getN();
//		// 移除整个collection
//		dCollection.drop();
	}

//	private static final class Tools {
//		// 实现mongodb主键自增长的功能,原理在数据库中插入一条当前记录数据{ "_id" : "sen", "next" : 12 }
//		public static long getNext(DBCollection users, String tableName) {
//			long incId = 0;
//			try {
//				DBObject ret = users.findAndModify(new BasicDBObject("_id",
//						tableName), null, null, false, new BasicDBObject(
//						"$inc", new BasicDBObject("next", 1)), true, true);
//				incId = Long.valueOf(ret.get("next").toString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return incId;
//
//		}
//
//	}

	}
