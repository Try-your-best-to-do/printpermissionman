package com.hzxy.printpermissionman.service.impl;

import com.hzxy.printpermissionman.dao.InventoryMapper;
import com.hzxy.printpermissionman.model.Inventory;
import com.hzxy.printpermissionman.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/*
 * ye
 * 库存服务接口实现
 * 2021.1.14
 *
 * */
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    @Override
    public int updateInventory(Inventory inventory) {
        int temp = inventoryMapper.updateByPrimaryKey(inventory);
        return temp;
    }

    @Override
    public Inventory findBlackAndColor(String id) {
        Inventory inventory = inventoryMapper.selectByPrimaryKey(id);
        return inventory;
    }
}
