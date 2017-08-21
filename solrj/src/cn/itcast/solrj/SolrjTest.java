package cn.itcast.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

public class SolrjTest {

	private HttpSolrServer httpSolrServer;
	@Before
	public void init(){
		
//		创建HttpSolrServer的连接对象
		String baseURL = "http://127.0.0.1:8082/solr/";
		this.httpSolrServer = new HttpSolrServer(baseURL);
		
	}
	
	//新增
	@Test
	public void testSaveAndUpdate() throws Exception{
		
		//创建solrInputDocument对象   封装要新增和修改的对象
		SolrInputDocument document = new SolrInputDocument();
		
		//放数据、
		document.addField("id", "c003");
		document.addField("title", "找到工作,走上生活巅峰");
		
		this.httpSolrServer.add(document);
		this.httpSolrServer.commit();
	}
	
	//删除
	@Test
	public void testDelete() throws Exception{
		//使用httpSolrServer 根据唯一域删除数据
		this.httpSolrServer.deleteById("change.me");
		//删除所有  慎用！
		//this.httpSolrServer.deleteByQuery("*:*");
		this.httpSolrServer.commit();
	}
	
}
