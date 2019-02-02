package com.mongo.demo;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface MongoDBDao {  
    /** 
     *  
     * 方法名：getDb 
     * 描述：获取指定的mongodb数据库 
     * @param dbName 
     * @return 
     */  
    public DB getDb(String dbName);  
    /** 
     *  
     * 方法名：getCollection 
     * 描述：获取指定mongodb数据库的collection集合 
     * @param dbName    数据库名 
     * @param collectionName    数据库集合 
     * @return 
     */  
    public DBCollection getCollection(String dbName, String collectionName);  
    /** 
     *  
     * 方法名：inSert 
     * 描述：向指定的数据库中添加给定的keys和相应的values 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @return 
     */  
    public boolean inSert(String dbName, String collectionName, String[] keys, Object[] values);  
    /** 
     *  
     * 方法名：delete 
     * 描述：删除数据库dbName中，指定keys和相应values的值 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @return 
     */  
    public boolean delete(String dbName, String collectionName, String[] keys, Object[] values);  
    /** 
     *  
     * 方法名：find 
     * 描述：从数据库dbName中查找指定keys和相应values的值 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @param num 
     * @return 
     */  
    public ArrayList<DBObject> find(String dbName, String collectionName, String[] keys, Object[] values, int num);  
    /** 
     *  
     * 方法名：update 
     * 描述：更新数据库dbName，用指定的newValue更新oldValue 
     * @param dbName 
     * @param collectionName 
     * @param oldValue 
     * @param newValue 
     * @return 
     */  
    public boolean update(String dbName, String collectionName, DBObject oldValue, DBObject newValue);  
    /** 
     *  
     * 方法名：isExit 
     * 描述：判断给定的keys和相应的values在指定的dbName的collectionName集合中是否存在 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @return 
     */  
    public boolean isExit(String dbName, String collectionName, String key, Object value);  
    
    
    public void drop(String dbName, String collectionName);
    
    public void insertbulkordereddocs(String dbName, String collectionName,DBObject...dbObjects);
    
    public String findAll(String dbName, String collectionName);
    
    public DBObject findAndModify(String dbName, String collectionName,DBObject...dbObjects);
     
    public String find(String dbName, String collectionName, BasicDBObject condition, String str1);
}  
