package com.learn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @date 2020-05-11 23:50
 * 说明：测试堆溢出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> oomObjectList = new ArrayList<>();
        while (true) {
            oomObjectList.add(new OOMObject());
        }
    }
}
