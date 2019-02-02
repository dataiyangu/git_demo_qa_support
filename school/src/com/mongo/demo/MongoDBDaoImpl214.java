//package com.mongo.demo;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Properties;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//
//import com.mongodb.AggregationOutput;
//import com.mongodb.BulkWriteOperation;
//import com.mongodb.BulkWriteResult;
//import com.mongodb.CommandResult;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBObject;
//import com.mongodb.BasicDBObject;  
//import com.mongodb.DBCursor;  
//import com.mongodb.MongoClient;  
//import com.mongodb.MongoClientOptions;  
//import com.mongodb.MongoException;  
//import com.mongodb.WriteResult; 
//
//public class MongoDBDaoImpl214 implements MongoDBDao {
//
//	private static final Log log = LogFactory.getLog(MongoDBDaoImpl214.class);
//	/** 
//     * MongoClient的实例代表数据库连接池，是线程安全的，可以被多线程共享，客户端在多线程条件下仅维持一个实例即可 
//     * Mongo是非线程安全的，目前mongodb API中已经建议用MongoClient替代Mongo 
//     */  
//    private MongoClient mongoClient = null;  
//    
//    
//    private String host = "10.0.5.83";
//    private int port = 10000;
//    private String db = "leodb";
//    private String coll = "leocoll";
//    
//    public void init(){
//    	String path = "/WEB-INF/classes/redis.properties"; //读取WEB-INF中的配置文件
////		sc = ServletActionContext.getServletContext();
////	    String realPath = sc.getContextPath() + path;
//	    
//	    Properties props = new Properties();
//	    try {
////			props.load(new InputStreamReader(new FileInputStream(realPath),"utf-8"));
//	    	props=PropertiesLoaderUtils.loadAllProperties("redis.properties");
//		} catch (UnsupportedEncodingException e) {
//			log.error("--UnsupportedEncod-->", e);
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			log.error("--FileNotFound-->", e);
//			e.printStackTrace();
//		} catch (IOException e) {
//			log.error("--IO-->", e);
//			e.printStackTrace();
//		}
//	    
//	    host = props.getProperty("mongo.ip");
//	    db = props.getProperty("mongo.db");
//	    coll = props.getProperty("mongo.colletion");
//	    try {
//	    	port = Integer.valueOf(props.getProperty("mongo.port"));
//		} catch (Exception e) {
//			port = 10000;
//		}
//    }
//    
//    /** 
//     *  
//     */  
//    public MongoDBDaoImpl214(){  
//    	init();
//        if(mongoClient == null){  
//            MongoClientOptions.Builder build = new MongoClientOptions.Builder();          
//            build.connectionsPerHost(50);   //与目标数据库能够建立的最大connection数量为50  
//            build.autoConnectRetry(true);   //自动重连数据库启动  
//            build.threadsAllowedToBlockForConnectionMultiplier(50); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待  
//            /* 
//             * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟 
//             * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception 
//             * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败 
//             */  
//            build.maxWaitTime(1000*60*2);  
//            build.connectTimeout(1000*60*1);    //与数据库建立连接的timeout设置为1分钟  
//              
//            MongoClientOptions myOptions = build.build();         
//            try {  
//                //数据库连接实例  
////                mongoClient = new MongoClient(host, myOptions);
//                mongoClient = new MongoClient(host, port);
//            } catch (UnknownHostException e) {  
//                // TODO 这里写异常处理的代码  
//                e.printStackTrace();  
//            } catch (MongoException e){  
//                e.printStackTrace();  
//            }  
//              
//        }  
//    }  
//      
//    /********单例模式声明开始，采用饿汉式方式生成，保证线程安全********************/  
//      
//    //类初始化时，自行实例化，饿汉式单例模式  
//    private static final MongoDBDaoImpl214 mongoDBDaoImpl = new MongoDBDaoImpl214();  
//    /** 
//     *  
//     * @return 
//     */  
//    public static MongoDBDaoImpl214 getMongoDBDaoImplInstance(){  
//        return mongoDBDaoImpl;  
//    }  
//      
//    /************************单例模式声明结束*************************************/  
//      
//    @Override  
//    public boolean delete(String dbName, String collectionName, String[] keys,  
//            Object[] values) {  
//        DB db = null;  
//        DBCollection dbCollection = null;  
//        if(keys!=null && values!=null){  
//            if(keys.length != values.length){   //如果keys和values不对等，直接返回false  
//                return false;  
//            }else{  
//                try {  
//                    db = mongoClient.getDB(dbName); //获取指定的数据库  
//                    dbCollection = db.getCollection(collectionName);    //获取指定的collectionName集合  
//                      
//                    BasicDBObject doc = new BasicDBObject();    //构建删除条件  
//                    WriteResult result = null;  //删除返回结果  
//                    String resultString = null;  
//                      
//                    for(int i=0; i<keys.length; i++){  
//                        doc.put(keys[i], values[i]);    //添加删除的条件  
//                    }  
//                    result = dbCollection.remove(doc);  //执行删除操作  
//                      
//                    resultString = result.getError();  
//                      
//                    if(null != db){  
//                        try {  
//                            db.requestDone();   //请求结束后关闭db  
//                            db = null;  
//                        } catch (Exception e) {  
//                            // TODO: handle exception  
//                            e.printStackTrace();  
//                        }  
//                          
//                    }  
//                      
//                    return (resultString!=null) ? false : true; //根据删除执行结果进行判断后返回结果  
//                } catch (Exception e) {  
//                    // TODO: handle exception  
//                    e.printStackTrace();  
//                } finally{  
//                    if(null != db){  
//                        db.requestDone();   //关闭db  
//                        db = null;  
//                    }  
//                }  
//                  
//            }  
//        }  
//        return false;  
//    }  
//  
//    @Override  
//    public ArrayList<DBObject> find(String dbName, String collectionName,  
//            String[] keys, Object[] values, int num) {  
//        ArrayList<DBObject> resultList = new ArrayList<DBObject>(); //创建返回的结果集  
//        DB db = null;  
//        DBCollection dbCollection = null;  
//        DBCursor cursor = null;  
//        if(keys!=null && values!=null){  
//            if(keys.length != values.length){  
//                return resultList;  //如果传来的查询参数对不对，直接返回空的结果集  
//            }else{  
//                try {  
//                    db = mongoClient.getDB(dbName); //获取数据库实例  
//                    dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
//                      
//                    BasicDBObject queryObj = new BasicDBObject();   //构建查询条件  
//                      
//                    for(int i=0; i<keys.length; i++){    //填充查询条件  
//                        queryObj.put(keys[i], values[i]);  
//                    }                 
//                    cursor = dbCollection.find(queryObj);   //查询获取数据  
//                    int count = 0;  
//                    if(num != -1){  //判断是否是返回全部数据，num=-1返回查询全部数据，num!=-1则返回指定的num数据  
//                        while(count<num && cursor.hasNext()){  
//                            resultList.add(cursor.next());  
//                            count++;  
//                        }  
//                        return resultList;  
//                    }else{  
//                        while(cursor.hasNext()){  
//                            resultList.add(cursor.next());  
//                        }  
//                        return resultList;  
//                    }  
//                } catch (Exception e) {  
//                    // TODO: handle exception  
//                } finally{                
//                    if(null != cursor){  
//                        cursor.close();  
//                    }  
//                    if(null != db){  
//                        db.requestDone();   //关闭数据库请求  
//                    }  
//                }  
//            }  
//        }  
//  
//        return resultList;  
//    }  
//  
//    @Override  
//    public DBCollection getCollection(String dbName, String collectionName) {  
//        // TODO Auto-generated method stub  
//        return mongoClient.getDB(dbName).getCollection(collectionName);  
//    }  
//  
//    @Override  
//    public DB getDb(String dbName) {  
//        // TODO Auto-generated method stub  
//        return mongoClient.getDB(dbName);  
//    }  
//  
//    @Override  
//    public boolean inSert(String dbName, String collectionName, String[] keys,  
//            Object[] values) {  
//        DB db = null;  
//        DBCollection dbCollection = null;  
//        WriteResult result = null;  
//        String resultString = null;  
//        if(keys!=null && values!=null){  
//            if(keys.length != values.length){  
//                return false;  
//            }else{  
//                db = mongoClient.getDB(dbName); //获取数据库实例  
//                dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
//                BasicDBObject insertObj = new BasicDBObject();  
//                for(int i=0; i<keys.length; i++){    //构建添加条件  
//                    insertObj.put(keys[i], values[i]);  
//                }  
//                  
//                try {  
//                    result = dbCollection.insert(insertObj);  
//                    resultString = result.getError();  
//                } catch (Exception e) {  
//                    // TODO: handle exception  
//                    e.printStackTrace();  
//                }finally{  
//                    if(null != db){  
//                        db.requestDone();   //请求结束后关闭db  
//                    }  
//                }                 
//                return (resultString != null) ? false : true;  
//            }  
//        }  
//        return false;  
//    }  
//  
//    @Override  
//    public boolean isExit(String dbName, String collectionName, String key,  
//            Object value) {  
//        // TODO Auto-generated method stub  
//        DB db = null;  
//        DBCollection dbCollection = null;  
//        if(key!=null && value!=null){  
//            try {  
//                db = mongoClient.getDB(dbName); //获取数据库实例  
//                dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
//                BasicDBObject obj = new BasicDBObject();    //构建查询条件  
//                obj.put(key, value);  
//                  
//                if(dbCollection.count(obj) > 0) {  
//                    return true;  
//                }else{  
//                    return false;  
//                }  
//            } catch (Exception e) {  
//                // TODO: handle exception  
//                e.printStackTrace();  
//            } finally{  
//                if(null != db){  
//                    db.requestDone();   //关闭db  
//                    db = null;  
//                }  
//            }  
//              
//        }  
//        return false;  
//    }  
//  
//    @Override  
//    public boolean update(String dbName, String collectionName,  
//            DBObject oldValue, DBObject newValue) {  
//        DB db = null;  
//        DBCollection dbCollection = null;  
//        WriteResult result = null;  
//        String resultString = null;  
//          
//        if(oldValue.equals(newValue)){  
//            return true;  
//        }else{  
//            try {  
//                db = mongoClient.getDB(dbName); //获取数据库实例  
//                dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
//                  
//                result = dbCollection.update(oldValue, newValue);  
//                resultString = result.getError();  
//                  
//                return result.isUpdateOfExisting();  
//            } catch (Exception e) {  
//                // TODO: handle exception  
//                e.printStackTrace();  
//            } finally{  
//                if(null != db){  
//                    db.requestDone();   //关闭db  
//                    db = null;  
//                }  
//            }  
//              
//        }  
//          
//        return false;  
//    }  
//    
//    @Override
//    public void drop(String dbName, String collectionName){
//    	DB db = null;  
//        DBCollection dbCollection = null;  
//        try {
//        	db = mongoClient.getDB(dbName); //获取数据库实例 
//        	dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
//        	dbCollection.drop(); 
//		} catch (Exception e) {
//			 e.printStackTrace();  
//		} finally{  
//            if(null != db){  
//                db.requestDone();   //关闭db  
//                db = null;  
//            }  
//        }  
//    }
//    
//    @Override
//    public void insertbulkordereddocs(String dbName, String collectionName,DBObject...dbObjects){
//    	
//    	DB db = null;  
//        DBCollection dbCollection = null;  
//        BulkWriteOperation  bwo = null;
//        try {
//        	db = mongoClient.getDB(dbName); //获取数据库实例 
//        	dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
//        	bwo = dbCollection.initializeOrderedBulkOperation();
//        	for (int i = 0; i < dbObjects.length; i++) {
//				bwo.insert(dbObjects[i]);
//			}
//			BulkWriteResult r1 = bwo.execute();
//
//			DBCursor carmuldocs = dbCollection.find();
//
//			System.out.println("-----------------------------------Modif->"+r1.isModifiedCountAvailable());
//
//			try {
//				while (carmuldocs.hasNext()) {
//					System.out.println(carmuldocs.next());
//				}
//			} finally {
//				carmuldocs.close();// close the cursor
//			}
////			return r1;
//        } catch (Exception e) {
//			 e.printStackTrace();  
//		} finally{  
//            if(null != db){  
//                db.requestDone();   //关闭db  
//                db = null;  
//            }  
//        }  
////        return null;   
//    }
//    
//    public String findAll(String dbName, String collectionName){
//    	DB db = null;  
//        DBCollection dbCollection = null;  
////		System.out.println("----------------------------------");
//		DBCursor carmuldocs = null;
//		StringBuilder str = new StringBuilder();
//		try {
//			db = mongoClient.getDB(dbName); //获取数据库实例 
//        	dbCollection = db.getCollection(collectionName);    //获取数据库中指定的collection集合  
//        	carmuldocs = dbCollection.find();
//			while (carmuldocs.hasNext()) {
//				str.append(carmuldocs.next().toString());
//				str.append("\r");
//			}
////			System.out.println(str);
//		} finally {
//			if(null != carmuldocs){
//				carmuldocs.close();// close the cursor
//			}
//			if(null != db){  
//                db.requestDone();   //关闭db  
//                db = null;  
//            }  
//		}
//		return str.toString();
//    }
//    
//    public CommandResult aggregate(String dbName, String collectionName,DBObject...dbObjects){
//    	
//		DB db = null;
//		DBCollection dbCollection = null;
//		try {
//			db = mongoClient.getDB(dbName); // 获取数据库实例
//			dbCollection = db.getCollection(collectionName); // 获取数据库中指定的collection集合
//			// create our pipeline operations, first with the $match
//			DBObject match = new BasicDBObject("$match", new BasicDBObject(
//					"type", "airfare"));
//			// build the $projection operation
//			DBObject fields = new BasicDBObject("department", 1);
//			fields.put("amount", 1);
//			fields.put("_id", 0);
//			DBObject project = new BasicDBObject("$project", fields);
//			// Now the $group operation
//			DBObject groupFields = new BasicDBObject("_id", "$department");
//			groupFields.put("average", new BasicDBObject("$avg", "$amount"));
//			DBObject group = new BasicDBObject("$group", groupFields);
//			// run aggregation
//			AggregationOutput output = dbCollection.aggregate(match, project,
//					group);
//
//    	return output.getCommandResult();
//        } catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//        
//    	return null;
//    }
//    
//    public DBObject findAndModify(String dbName, String collectionName,DBObject...dbObjects){
//    	DB db = null;
//		DBCollection dbCollection = null;
//		DBObject result = null;
//		try {
//			db = mongoClient.getDB(dbName); // 获取数据库实例
//			dbCollection = db.getCollection(collectionName); // 获取数据库中指定的collection集合
//			
//			DBObject query = null;
//			DBObject update = null;
//			
//			query = dbObjects[0];
//			update = dbObjects[1];
//			
//			result = dbCollection.findAndModify(query, update);
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//    	
//    	return result;
//    }
//    
//	public String find(String dbName, String collectionName, BasicDBObject condition, String str1) {  
//        System.out.println("================" + str1 + "==================");  
//        DB db = null;
//		DBCollection dbCollection = null;
//		DBObject result = null;
//		StringBuilder str = new StringBuilder();
//		try {
//			db = mongoClient.getDB(dbName); // 获取数据库实例
//			dbCollection = db.getCollection(collectionName); // 获取数据库中指定的collection集合
//			
//			DBObject query = null;
//			DBObject update = null;
//			DBCursor find = dbCollection.find(condition);  
//	        while (find.hasNext()) {  
//	        	str.append(find.next().toString());
//				str.append("\r");
//	        }  
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return str.toString();
//  
//    }  
//    
//    /** 
//     * @param args 
//     */  
//    public static void main(String[] args) {  
//        // TODO Auto-generated method stub  
//    	boolean result = false;
//    	ArrayList<DBObject> reslist = null;
//    	MongoDBDao mdao = MongoDBDaoImpl214.getMongoDBDaoImplInstance();
//    	String[] keys = {"1","2"};
//    	String[] values = {"dana","xiaoyu"};
//    	
//    	String[] keys1 = {"1"};
//    	String[] values1 = {"dana"};
//    	
//    	String[] keys2 = {"555"};
//    	String[] values2 = {"ddaaiissyy"};
////    	result = mdao.inSert("leodb", "leocoll", keys, values);
////        System.out.println("--test insert over-->"+result);
////        reslist = mdao.find("leodb", "leocoll", keys1, values1, -1);
//    	if (reslist != null) {
//    		for (int i = 0; i < reslist.size(); i++) {
//    			System.out.println("--test find over "+i+" -->" + reslist.get(i).toString());
//    		}
//		}
//        result = false;
////        result = mdao.delete("leodb", "leocoll", keys, values);
////        System.out.println("--test delete -->"+result);
//        result = false;
////        result = mdao.isExit("leodb", "leocoll", "1", "dana");
////        System.out.println("--test exist-->" + result);
////        mdao.drop("leodb", "leocoll");
//        BasicDBObject oldobj = new BasicDBObject();
//		for (int i = 0; i < keys2.length; i++) { // 填充查询条件
//			oldobj.put(keys2[i], values2[i]);
//		}
//		BasicDBObject newobj = new BasicDBObject();
//		for (int i = 0; i < keys1.length; i++) { // 填充查询条件
//			newobj.put(keys1[i], values1[i]);
//		}
////		result = mdao.update("leodb", "leocoll", oldobj, newobj);
////		System.out.println("--test update over-->" + result);
//		// 批量存入
////        mdao.insertbulkordereddocs("leodb", "leocoll", oldobj, newobj);
////		
////		DBObject res = mdao.findAndModify("leodb", "leocoll", oldobj, newobj);
////		
////		System.out.println("--findAndModify--\n"+res.toString());
//		
//		String all = mdao.findAll("leodb", "leocoll");
//		System.out.println("--all--\n"+all);
//		
//		
//		
//        
//    }  
//}
