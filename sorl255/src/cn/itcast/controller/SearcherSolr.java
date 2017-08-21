package cn.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.pojo.Result;
import cn.itcast.service.SearcherService;

@Controller
@RequestMapping("search")
public class SearcherSolr {
	@Autowired
	private SearcherService searcherService;
	
	/**
	 * 条件搜索
	 * @param model
	 * @param queryString
	 * @param catelog_name
	 * @param price
	 * @param sort
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String searcherProduct(Model model,String queryString,String catelog_name,String price,String sort,Integer page){
		
		//根据条件搜索
		Result result = searcherService.searcherProduct(queryString,catelog_name,price,sort,page);
		
		//把结果集放到模型中
		model.addAttribute("result",result);
		
		//数据的回显
		model.addAttribute("queryString", queryString);
		model.addAttribute("catelog_name", catelog_name);
		model.addAttribute("price", price);
		model.addAttribute("sort", sort);
		model.addAttribute("page", page);
		
		return "product_list";
		
	}
}
