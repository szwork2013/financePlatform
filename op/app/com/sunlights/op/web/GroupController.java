package com.sunlights.op.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.common.MsgCode;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.GroupService;
import com.sunlights.op.service.impl.GroupServiceImpl;
import com.sunlights.op.vo.GroupVo;
import models.Customer;
import models.Group;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: GroupController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class GroupController extends Controller{

    private GroupService groupService = new GroupServiceImpl();
    
    public Result findGroups(){
        PageVo pageVo = new PageVo();
        JsonNode jsonNode = request().body().asJson();
        if (jsonNode != null) {
            pageVo = Json.fromJson(jsonNode, PageVo.class);
        }
        List<GroupVo> list = groupService.findGroups(pageVo);
        pageVo.setList(list);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), pageVo);
        
        return ok(Json.toJson(pageVo));
    }
    
    public Result save(){
        GroupVo groupVo = new GroupVo();
        JsonNode jsonNode = request().body().asJson();
        if (jsonNode != null) {
            groupVo = Json.fromJson(jsonNode, GroupVo.class);
        }
        try {
            Group group = ConverterUtil.toEntity(new Group(), groupVo);
            if (group.getId() != null) {
                groupService.updateGroup(group);
                MessageUtil.getInstance().setMessage(new Message(MsgCode.UPDATE_SUCCESS));
            }else{
                groupService.createGroup(group);
                MessageUtil.getInstance().setMessage(new Message(MsgCode.CREATE_SUCCESS));
            }
        } catch (ConverterException e) {
            e.printStackTrace();
        }

        return ok(MessageUtil.getInstance().toJson());
    }

    public Result delete(){
        GroupVo groupVo = new GroupVo();
        JsonNode jsonNode = request().body().asJson();
        if (jsonNode != null) {
            groupVo = Json.fromJson(jsonNode, GroupVo.class);
        }else{
            groupVo = RequestUtil.fromQueryString(request().queryString(), GroupVo.class);
        }
        try {
            Group group = ConverterUtil.toEntity(new Group(), groupVo);
            groupService.deleteGroup(group.getId());
        } catch (ConverterException e) {
            e.printStackTrace();
        }

        return ok(MessageUtil.getInstance().msgToJson(new Message(MsgCode.DELETE_SUCCESS)));
    }
    
    public Result findCustomers(){
        PageVo pageVo = new PageVo();
        String customerFilter = null;
        JsonNode jsonNode = request().body().asJson();
        if (jsonNode != null) {
            pageVo = Json.fromJson(jsonNode, PageVo.class);
        }else{
            pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
        }
        customerFilter = (String)pageVo.get("customerFilter");
        List<Customer> list = groupService.findCustomers(customerFilter, pageVo);
        pageVo.setList(list);

        return ok(MessageUtil.getInstance().msgToJson(new Message(MsgCode.DELETE_SUCCESS), pageVo));
    }

    public Result addCustomers(){
        PageVo pageVo = new PageVo();
        JsonNode jsonNode = request().body().asJson();
        if (jsonNode != null) {
            pageVo = Json.fromJson(jsonNode, PageVo.class);

            JsonNode listJson = Json.toJson(pageVo.getList());
            List<Customer> customerList = Lists.newArrayList();
            if (listJson != null && listJson.isArray()) {
                for (JsonNode node : listJson) {
                    Customer customer = Json.fromJson(node, Customer.class) ;
                    customerList.add(customer);
                }
                if (!customerList.isEmpty()) {
                    groupService.addCustomers(customerList, Long.valueOf(pageVo.get("groupId").toString()));
                }
            }

        }

        return ok(MessageUtil.getInstance().msgToJson(new Message(MsgCode.DELETE_SUCCESS)));
    }

}
