import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Demo {

	public static void main(String[] args) throws IOException {
		InputStream resourceAsStream = Demo.class.getResourceAsStream("a.properties");
		Properties properties = new Properties();
		properties.load(resourceAsStream);
		String ip = properties.getProperty("mongo.ip");
		int port = Integer.parseInt(properties.getProperty("mongo.port"));
		String db = properties.getProperty("mongo.db");
		String colletion = properties.getProperty("mongo.colletion");


		System.out.println("---- Hello MongoDB ----");
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient(ip, port);

			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
			System.out.println("Connect to database successfully");

			/*
			 * mongoDatabase.createCollection("city");
			 * System.out.println("集合city创建成功"); //
			 */

			// 获取集合
			MongoCollection<Document> collection = mongoDatabase
					.getCollection(colletion);
			System.out.println("集合 city 选择成功");

			// 在集合中插入文档
			/**
			 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List<Document>
			 * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>)
			 * 插入单个文档可以用 mongoCollection.insertOne(Document)
			 * */
			Document document = new Document("name", "beijing")
					.append("country", "china").append("provice", "BEIJING")
					.append("people-count", 1000);
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			System.out.println("文档插入成功-Many");

			Document mydocument = new Document("name", "washington")
					.append("country", "america")
					.append("provice", "WASHINGTON")
					.append("people-count", 1200);
			collection.insertOne(mydocument);

			System.out.println("文档插入成功-One");

			// 检索所有文档
			showDocuments(collection);

			// 删除符合条件的第一个文档
			// collection.deleteOne(Filters.eq("name", "beijing"));
			// 删除所有符合条件的文档
			collection.deleteMany(Filters.eq("name", "beijing"));
			System.out.println("----     deleted OK   ----");
			System.out.println("--------------------------");

			// 更新文档 将文档中likes=100的文档修改为likes=200
			collection.updateMany(Filters.eq("name", "washington"),
					new Document("$set", new Document("country", "JAPAN")));
			// 检索所有文档
			showDocuments(collection);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		System.out.println("---- over ----");
	}

	private static void showDocuments(MongoCollection<Document> collection) {
		/**
		 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
		 * 通过游标遍历检索出的文档集合
		 * */
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next()); // .get("name")
		}
	}

}