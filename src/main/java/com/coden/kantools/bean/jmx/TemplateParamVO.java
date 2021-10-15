package com.coden.kantools.bean.jmx;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板参数对象VO
 *
 * @author yunlong.liu
 * @date 2020-11-04 15:16:56
 */

public class TemplateParamVO {

    /**
     * 对应服务的标题
     */
    private String title;

    /**
     * 服务的描述
     */
    private String description;

    /**
     * 服务server的主机地址
     */
    private String host;

    /**
     * 服务server的端口
     */
    private int port;

    /**
     * tag列表
     */
    private List<TagNode> tagList = new ArrayList<>();

    /**
     * 自定义参数变量
     */
    private List<VariableNode> customVariableList = new ArrayList<>();


    public List<TagNode> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagNode> tagList) {
        this.tagList = tagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<VariableNode> getCustomVariableList() {
        return customVariableList;
    }

    public void setCustomVariableList(List<VariableNode> customVariableList) {
        this.customVariableList = customVariableList;
    }
}
