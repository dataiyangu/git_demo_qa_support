//package com.leo.bdb.bfour;
//
//import com.sleepycat.bind.serial.ClassCatalog;
//import com.sleepycat.bind.serial.SerialBinding;
//import com.sleepycat.bind.serial.StoredClassCatalog;
//import com.sleepycat.bind.tuple.TupleBinding;
//import com.sleepycat.collections.StoredSortedMap;
//import com.sleepycat.collections.TransactionRunner;
//import com.sleepycat.collections.TransactionWorker;
//import com.sleepycat.db.Database;
//import com.sleepycat.db.DatabaseConfig;
//import com.sleepycat.db.DatabaseException;
//import com.sleepycat.db.DatabaseType;
//import com.sleepycat.db.Environment;
//import com.sleepycat.db.EnvironmentConfig;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintStream;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//import java.util.SortedMap;
//
//public class HelloDatabaseWorld
//  implements TransactionWorker
//{
//  private static final String[] INT_NAMES = { "Hello", "Database", "World", "2LO" };
//
//  private static boolean create = true;
//  private Environment env;
//  private ClassCatalog catalog;
//  private Database db;
//  private SortedMap map;
//
//  public static void main(String[] argv)
//    throws Exception
//  {
//    String dir = ".//";
//
//    EnvironmentConfig envConfig = new EnvironmentConfig();
//    envConfig.setTransactional(true);
//    envConfig.setInitializeCache(true);
//    envConfig.setInitializeLocking(true);
//    if (create) {
//      envConfig.setAllowCreate(true);
//    }
//    Environment env = new Environment(new File(dir), envConfig);
//
//    HelloDatabaseWorld worker = new HelloDatabaseWorld(env);
//    TransactionRunner runner = new TransactionRunner(env);
//    try
//    {
//      runner.run(worker);
//    }
//    finally {
//      worker.close();
//    }
//  }
//
//  private HelloDatabaseWorld(Environment env)
//    throws Exception
//  {
//    this.env = env;
//    open();
//  }
//  public HelloDatabaseWorld(){
//	  
//  }
//
//  public void doWork()
//    throws Exception
//  {
//    writeAndRead();
//  }
//
//  private void open()
//    throws Exception
//  {
//    DatabaseConfig dbConfig = new DatabaseConfig();
//    dbConfig.setTransactional(true);
//    if (create) {
//      dbConfig.setAllowCreate(true);
//      dbConfig.setType(DatabaseType.BTREE);
//    }
//
//    Database catalogDb = this.env.openDatabase(null, "catalog", null, dbConfig);
//    this.catalog = new StoredClassCatalog(catalogDb);
//
//    TupleBinding keyBinding = TupleBinding.getPrimitiveBinding(Integer.class);
//
//    SerialBinding dataBinding = new SerialBinding(this.catalog, String.class);
//
//    this.db = this.env.openDatabase(null, "helloworld", null, dbConfig);
//
//    this.map = new StoredSortedMap(this.db, keyBinding, dataBinding, true);
//  }
//
//  private void close()
//    throws Exception
//  {
//    if (this.catalog != null) {
//      this.catalog.close();
//      this.catalog = null;
//    }
//    if (this.db != null) {
//      this.db.close();
//      this.db = null;
//    }
//    if (this.env != null) {
//      this.env.close();
//      this.env = null;
//    }
//  }
//
//  private void writeAndRead()
//  {
//    Integer key = new Integer(0);
//    String val = (String)this.map.get(key);
//    if (val == null) {
//      System.out.println("Writing data length " + INT_NAMES.length);
//
//      for (int i = INT_NAMES.length - 1; i >= 0; i--) {
//        this.map.put(new Integer(i), INT_NAMES[i]);
//      }
//    }
//
//    Iterator iter = this.map.entrySet().iterator();
//    System.out.println("Reading data length " + INT_NAMES.length);
//    while (iter.hasNext()) {
//      Map.Entry entry = (Map.Entry)iter.next();
//      System.out.println(entry.getKey().toString() + ' ' + entry.getValue());
//    }
//  }
//  
//  
//  public void doTest() throws Exception{
//	    String dir = ".//";
//
//	    EnvironmentConfig envConfig = new EnvironmentConfig();
//	    envConfig.setTransactional(true);
//	    envConfig.setInitializeCache(true);
//	    envConfig.setInitializeLocking(true);
//	    if (create) {
//	      envConfig.setAllowCreate(true);
//	    }
//	    Environment env = new Environment(new File(dir), envConfig);
//
//	    HelloDatabaseWorld worker = new HelloDatabaseWorld(env);
//	    TransactionRunner runner = new TransactionRunner(env);
//	    try
//	    {
//	      runner.run(worker);
//	    }
//	    finally {
//	      worker.close();
//	    }
//  }
//}