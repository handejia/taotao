package cn.itcast.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.pojo.Product;
import cn.itcast.pojo.Result;
@Service
public class SearcherService {
	
	@Autowired
	private HttpSolrServer httpSolrServer;
	
	/**
	 * 搜索索引库的商品
	 * @param queryString
	 * @param catelog_name
	 * @param price
	 * @param sort
	 * @param page
	 * @return
	 */
	public Result searcherProduct(String queryString, String catelog_name, String price, String sort, Integer page) {
		
		//1.创建搜索对象
		SolrQuery query = new SolrQuery();
		//2.设置搜索语句
		//判断搜索关键词是否为空
		if(StringUtils.isNotBlank(queryString)){
			//不为空按条件搜索
			query.setQuery(queryString);
		}else{
			//如果为空默认搜索所有
			query.setQuery("*:*");
		}
		//3.设置默认搜索域
		query.set("df","product_name");
		
		//4.设置过滤条件
		//拼接商品类名称过滤条件
		if(StringUtils.isNotBlank(catelog_name)){
			catelog_name = "product_catelog_name:"+catelog_name;
		}
		
		//拼接价格过滤条件
		if(StringUtils.isNotBlank(price)){
			String[] str = StringUtils.split(price,"-");
			
			price = "product_price:{"+ str[0] + " 	TO "+ str[1]+"}";
		}
		//设置过滤条件
		query.setFilterQueries(catelog_name,price);
		
		//5.设置分页
		int rows = 16;
		query.setRows(rows);
		//设置从那一条数据开始查询
		if(page == null){
			page = 1;
		}
		query.setStart((page - 1) * rows);
		
		//6.设置排序
		if("1".equals(sort)){
			query.setSort("product_price", ORDER.desc);
		}else{
			query.setSort("product_price", ORDER.asc);
		}
		
		//7.设计高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<font color = 'red'>");
		query.setHighlightSimplePost("</font>");
		//8.执行查询
		
		try {
			QueryResponse response = this.httpSolrServer.query(query);
			//获取高亮数据
			Map<String, Map<String, List<String>>> map = response.getHighlighting();
			//获取结果集
			SolrDocumentList results = response.getResults();
			//获取查询总记录数
			long total = results.getNumFound();
			//声明容器存放结果集
			List<Product> productList = new ArrayList<>();
			
			//9.解析结果集
			for (SolrDocument solrDocument : results) {
				
				//解析高亮数据
				List<String> hist = map.get(solrDocument.get("id").toString()).get(solrDocument.get("product_name"));
				
				//声明结果
				Product product = new Product();
				
				//商品id
				product.setPid(solrDocument.get("id").toString());
				
				//商品名称判断是否有高亮数据
				if(hist != null && hist.size()>0){
					product.setName(hist.get(0));
				}else{
					product.setName(solrDocument.get("product_name").toString());
				}
				
				//图片
				product.setPicture(solrDocument.get("product_picture").toString());
				
				//价格
				product.setPrice(solrDocument.get("product_price").toString());
				
			
				//把结果放入容器中
				productList.add(product);
			}
				//封装返回对象
				Result result = new Result();
				//当前页
				result.setCurPage(page);
				//总页数
				result.setPageCount((int) (total % rows == 0 ? total/rows : (total/rows) + 1));
				//查询的结果集
				result.setProductList(productList);
				//总记录数
				result.setRecordCount(total);
				
				return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//如果有异常返回空
		return null;
	}

}
