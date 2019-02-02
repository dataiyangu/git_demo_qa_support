import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.util.ArrayList;
import java.util.List;

public class Solr {
	public static void main(String[] args) {
		//获得solr服务
		HttpSolrServer hs = new HttpSolrServer("http://10.0.1.225:2222/solr/collection1");
		SolrInputDocument inputDocument = new SolrInputDocument();
		try {
			//向文档中添加域以及对应的值,注意：所有的域必须在schema.xml中定义过,前面已经给出过我定义的域。
			inputDocument.addField("id", "1");
			inputDocument.addField("title", "我们一起学猫叫");
			hs.add(inputDocument);
			hs.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*SorlUtils sorlUtils=SorlUtils.getInstance();*/
		//solr对象实体
		SolrTestBean sb = new SolrTestBean();
//	    sb.setId("2");
//	    sb.setTitle("一起喵喵喵喵喵~");

		List<SolrTestBean> lst = new ArrayList<SolrTestBean>();
		for (int i = 2; i < 15; i++) {
			sb = new SolrTestBean();
			sb.setId(i + "");
			sb.setTitle("一起喵喵喵喵喵~" + i);
			lst.add(sb);
		}
		try {

			//添加数据
//	            UpdateResponse ur=hs.addBean(sb);
//				hs.addBean(sb);
//	            hs.commit();//提交
			//添加批量数据
			UpdateResponse ur = hs.addBeans(lst);
			hs.commit();//提交
			//删除数据
	            hs.deleteById("10");
//	         hs.commit();//提交

//	         查询数据
			SolrQuery query = new SolrQuery("一起喵喵");
//	        query.set("q","id:10");
			query.setRows(15);
			//得到查询响应对象
			QueryResponse response = hs.query(query);
			//得到返回结果集
			SolrDocumentList doclist = response.getResults();

			for (int i = 0; i < doclist.getNumFound(); i++) {
				System.out.println("id:" + doclist.get(i).get("id") + "---title:" + doclist.get(i).get("title"));
			}

	            /*int result= ur.getStatus();
62  System.out.println(result);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static List solr(){
		//获得solr服务
		HttpSolrServer hs = new HttpSolrServer("http://10.0.1.225:2222/solr/collection1");
		SolrInputDocument inputDocument = new SolrInputDocument();
		try {
			//向文档中添加域以及对应的值,注意：所有的域必须在schema.xml中定义过,前面已经给出过我定义的域。
			inputDocument.addField("id", "1");
			inputDocument.addField("title", "我们一起学猫叫");
			hs.add(inputDocument);
			hs.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*SorlUtils sorlUtils=SorlUtils.getInstance();*/
		//solr对象实体
		SolrTestBean sb = new SolrTestBean();
//	    sb.setId("2");
//	    sb.setTitle("一起喵喵喵喵喵~");

		List<SolrTestBean> lst = new ArrayList<SolrTestBean>();
		for (int i = 2; i < 15; i++) {
			sb = new SolrTestBean();
			sb.setId(i + "");
			sb.setTitle("一起喵喵喵喵喵~" + i);
			lst.add(sb);
		}
		try {

			//添加数据
//	            UpdateResponse ur=hs.addBean(sb);
//				hs.addBean(sb);
//	            hs.commit();//提交
			//添加批量数据
			UpdateResponse ur = hs.addBeans(lst);
			hs.commit();//提交
			//删除数据
//	            hs.deleteById("10");
//	         hs.commit();//提交

//	         查询数据
			SolrQuery query = new SolrQuery("一起喵喵");
//	        query.set("q","id:10");
			query.setRows(15);
			//得到查询响应对象
			QueryResponse response = hs.query(query);
			//得到返回结果集
			SolrDocumentList doclist = response.getResults();

			for (int i = 0; i < doclist.getNumFound(); i++) {
				System.out.println("id:" + doclist.get(i).get("id") + "---title:" + doclist.get(i).get("title"));
			}
			return doclist;
	            /*int result= ur.getStatus();
62  System.out.println(result);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null;
		}

	}}
