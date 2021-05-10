package com.hzxy.printpermissionman.service;

import com.hzxy.printpermissionman.model.Inventory;


/*
* ye
* 库存服务接口
* 2021.1.14
*
* */
public interface InventoryService {
    int updateInventory(Inventory inventory);

    Inventory findBlackAndColor(String id);
}
