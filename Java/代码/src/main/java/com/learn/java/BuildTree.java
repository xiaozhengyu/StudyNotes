package com.learn.java;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProductName: Hundsun HEP
 * @ProjectName: demo
 * @Package: com.learn.java
 * @Description:
 * @Author: xiaozy37528
 * @CreateDate: 2021/7/14 14:51
 * @UpdateUser: xiaozy37528
 * @UpdateDate: 2021/7/14 14:51
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2021 Hundsun Technologies Inc. All Rights Reserved
 **/
public class BuildTree {
    public static List<ResourceTreeDTO> nodeList = new LinkedList<>();

    static {
        nodeList.add(new ResourceTreeDTO(1L, null, "系统管理"));
        nodeList.add(new ResourceTreeDTO(2L, 1L, "用户管理"));
        nodeList.add(new ResourceTreeDTO(3L, 1L, "资源管理"));
        nodeList.add(new ResourceTreeDTO(4L, 2L, "A1"));
        nodeList.add(new ResourceTreeDTO(5L, 2L, "A2"));
        nodeList.add(new ResourceTreeDTO(6L, 3L, "B1"));
        nodeList.add(new ResourceTreeDTO(7L, 3L, "B2"));
    }

    public static List<ResourceTreeDTO> getParent(List<ResourceTreeDTO> nodeList) {
        // 找出菜单
        List<ResourceTreeDTO> parentList = new LinkedList<>();
        for (ResourceTreeDTO node : nodeList) {
            if (node.getParentId() == null) {
                // 找出下级
                node.setChildList(getChile(node.getId(), nodeList));

                parentList.add(node);
            }
        }

        return parentList;
    }

    public static List<ResourceTreeDTO> getChile(Long parentId, List<ResourceTreeDTO> nodeList) {
        // 找出下级
        List<ResourceTreeDTO> childList = new LinkedList<>();
        for (ResourceTreeDTO node : nodeList) {
            if (parentId.equals(node.getParentId())) {
                // 找出下级的下级
                node.setChildList(getChile(node.getId(), nodeList));

                childList.add(node);
            }
        }

        return childList;
    }

    public static void main(String[] args) {
        List<ResourceTreeDTO> resourceTree = getParent(nodeList);
    }
}

@Data
class ResourceTreeDTO {

    private Long id;
    private Long parentId;
    private String resourceCode;
    List<ResourceTreeDTO> childList;

    public ResourceTreeDTO(Long id, Long parentId, String resourceCode) {
        this.id = id;
        this.parentId = parentId;
        this.resourceCode = resourceCode;
    }
}