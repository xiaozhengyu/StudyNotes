package com.learn.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xzy
 * @date 2020-06-30 10:36
 * 说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    /**
     * 当前节点ID
     */
    private String id;
    /**
     * 当前节点名称
     */
    private String name;
    /**
     * 父节点ID
     */
    private String parentId;
    /**
     * 子节点列表
     */
    private List<Node> childNodeList;
}
