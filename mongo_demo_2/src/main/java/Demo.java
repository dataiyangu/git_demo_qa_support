import com.mongodb.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Demo {

	public static void main(String[] args) throws IOException {
		InputStream resourceAsStream = Demo.class.getResourceAsStream("a.properties");
		Properties properties = new Properties();
		properties.load(resourceAsStream);
		String ip = properties.getProperty("mongo.ip");
		int port = Integer.parseInt(properties.getProperty("mongo.port"));
		String dbname = properties.getProperty("mongo.db");
		String colletion = properties.getProperty("mongo.colletion");


		System.out.println("---- Hello MongoDB ----");
		try {
			//获取连接mongodb数据库
			MongoClient client = new MongoClient(ip, port);
			//连接mldn的数据库
			DB db = client.getDB(dbname);
			//进行授权登录
			if (db.authenticate("root", "root".toCharArray())) {
				for (String name : db.getCollectionNames()) {
					System.out.println("集合名称：" + name);
				}
//				db.getCollection(colletion).drop();
			}
//			client.close();


			/*
			 * mongoDatabase.createCollection("city");
			 * System.out.println("集合city创建成功"); //
			 */

			// 获取集合
//			MongoCollection<Document> collection = mongoDatabase
//					.getCollection(colletion);
			DBCollection collection = db.getCollection(colletion);
			System.out.println("集合 city 选择成功");

			DBCursor dbCursor = collection.find();
			System.out.println(collection.getCount());
			while(dbCursor.hasNext()){
				System.out.println(dbCursor.next());
			}

			//向集合中插入文档：

			DBObject c = new BasicDBObject();
			c.put("name","jack");
			c.put("age",24);
			System.out.println("进行了插入操作，name--name，age--24");
			collection.insert(c);
			client.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		}
	}
