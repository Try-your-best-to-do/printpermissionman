package com.hzxy.printpermissionman.controller;

import com.hzxy.printpermissionman.model.Inventory;
import com.hzxy.printpermissionman.service.InventoryService;
import com.hzxy.printpermissionman.utils.Code;
import com.hzxy.printpermissionman.utils.ResultInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/*
* ye
* 库存控制器
* 2021.1.14
* */
@Controller
public class InventoryController {

    Logger logger = LogManager.getLogger(this.getClass());
    private ResultInfo info;

    @ModelAttribute
    public void init() {
        info = new ResultInfo();
    }

    @Resource
    private InventoryService inventoryService;


    //编辑库存
    @ResponseBody
    @RequestMapping("/inventory/update")
    public ResultInfo update(Inventory inventory){
        try{
            if (inventory.getId().equals("")){
                info.setData(Code.NODATA);
                return info;
            }
            int temp = inventoryService.updateInventory(inventory);
            if (temp==1){
                info.setData(temp);
                return info;
            }else {
                info.setCode(Code.UNKOWNERR);
                return info;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setData(Code.UNKOWNERR);
        return info;
    }


    //获取库存
    @ResponseBody
    @RequestMapping("/inventory/find")
    public ResultInfo update(String id){
        try{
            if (id.equals("")){
                info.setData(Code.NODATA);
                return info;
            }
            Inventory inventory = inventoryService.findBlackAndColor(id);
            if (inventory!=null){
                info.setData(inventory);
                return info;
            }else {
                info.setCode(Code.UNKOWNERR);
                return info;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setData(Code.UNKOWNERR);
        return info;
    }
}
