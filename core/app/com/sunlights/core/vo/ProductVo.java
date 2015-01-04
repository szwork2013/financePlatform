package com.sunlights.core.vo;

import com.sunlights.common.utils.CommonUtil;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ProductVo {
    private Long id;
    private String name;
    // 0:基金 1：P2P
    private String type;


    // 0:基金 1：P2P
    private String typeDesc;
    // 产品编码
    private String code;
    // （货币基金，短期理财）；（）；（）
    private String category;
    private String categoryDesc;
    // 推荐，其他
    private String group;
    private String groupDesc;
    private String tag;
    private String tagDesc;

    public ProductVo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return CommonUtil.format(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return CommonUtil.format(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return CommonUtil.format(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return CommonUtil.format(category);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGroup() {
        return CommonUtil.format(group);
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTypeDesc() {
        return CommonUtil.format(typeDesc);
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getCategoryDesc() {
        return CommonUtil.format(categoryDesc);
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getGroupDesc() {
        return CommonUtil.format(groupDesc);
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getTag() {
        return CommonUtil.format(tag);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagDesc() {
        return CommonUtil.format(tagDesc);
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductVo)) return false;

        ProductVo productVo = (ProductVo) o;

        if (category != null ? !category.equals(productVo.category) : productVo.category != null) return false;
        if (categoryDesc != null ? !categoryDesc.equals(productVo.categoryDesc) : productVo.categoryDesc != null)
            return false;
        if (code != null ? !code.equals(productVo.code) : productVo.code != null) return false;
        if (group != null ? !group.equals(productVo.group) : productVo.group != null) return false;
        if (groupDesc != null ? !groupDesc.equals(productVo.groupDesc) : productVo.groupDesc != null) return false;
        if (id != null ? !id.equals(productVo.id) : productVo.id != null) return false;
        if (name != null ? !name.equals(productVo.name) : productVo.name != null) return false;
        if (tag != null ? !tag.equals(productVo.tag) : productVo.tag != null) return false;
        if (tagDesc != null ? !tagDesc.equals(productVo.tagDesc) : productVo.tagDesc != null) return false;
        if (type != null ? !type.equals(productVo.type) : productVo.type != null) return false;
        if (typeDesc != null ? !typeDesc.equals(productVo.typeDesc) : productVo.typeDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (typeDesc != null ? typeDesc.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (categoryDesc != null ? categoryDesc.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (groupDesc != null ? groupDesc.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (tagDesc != null ? tagDesc.hashCode() : 0);
        return result;
    }

}
