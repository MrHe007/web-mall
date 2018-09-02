package com.bigguy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigguy.jedis.JedisClient;
import com.bigguy.mapper.TbItemDescMapper;
import com.bigguy.mapper.TbItemMapper;
import com.bigguy.pojo.EasyUITreeNode;
import com.bigguy.pojo.PageTO;
import com.bigguy.pojo.TbItem;
import com.bigguy.pojo.TbItemCatExample;
import com.bigguy.pojo.TbItemCatExample.Criteria;
import com.bigguy.pojo.TbItemDesc;
import com.bigguy.pojo.TbItemExample;
import com.bigguy.service.ItemService;
import com.bigguy.utils.IDUtils;
import com.bigguy.utils.JsonUtils;
import com.bigguy.utils.MsgResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper mapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Value("ITEM_LIST")
	String ITEM_LIST;
	
	
	@Override
	public MsgResult addItem(TbItem item, String desc) {
		
		long id = IDUtils.genItemId();
		
		item.setId(id);
		
		//商品状态，1-正常，2-下架，3-删除
		
		item.setStatus((byte)1);
		
		Date data = new Date();
		
		item.setCreated(data);
		item.setUpdated(data);
		
		mapper.insert(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(data);
		itemDesc.setUpdated(data);
		itemDesc.setItemDesc(desc);

		itemDescMapper.insert(itemDesc);
		
		return MsgResult.ok(); 		// 返回一个结果集
		
	}
	
	
	public TbItem findItem(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public PageTO getPageTO(Integer pageNum, Integer pageSize) {
		
		// 从数据库取时先判断jedis内存中是否有
		// 通过 hamp存储
		
		String json = jedisClient.hget(ITEM_LIST, pageNum+""+pageSize) ;
				
		if( !StringUtils.isEmpty(json)) {	// 如果字符串不为空 ,就从该内存中取出 redis 数据 
			PageTO pageto = JsonUtils.jsonToPojo(json, PageTO.class);
			return pageto;
		} 
		
		
		PageHelper.startPage(pageNum, pageSize);
		
		TbItemExample example = new TbItemExample();
		
		List<TbItem> list = mapper.selectByExample(example);

		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		PageTO pageTo= new PageTO();
		
		pageTo.setRows(list);
		pageTo.setTotal( (int) pageInfo.getTotal()); 	// 表示得到的数据总数  
		
		// 在数据返回之前，将数据放到缓存中
		
		jedisClient.hset(ITEM_LIST, pageNum+""+pageSize, JsonUtils.objectToJson(pageTo));
		
		return pageTo;
	}


}
