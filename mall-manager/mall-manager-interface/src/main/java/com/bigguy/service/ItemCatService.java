package com.bigguy.service;

import java.util.List;

import com.bigguy.pojo.EasyUITreeNode;

public interface ItemCatService {
	public List<EasyUITreeNode> getItemCatList(Long parentId);
}
