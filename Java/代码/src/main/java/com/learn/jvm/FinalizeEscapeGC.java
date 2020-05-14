package com.learn.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

/**
 * @author xzy
 * @date 2020-05-13 19:01
 * 说明：一次对象自我拯救的演示
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        //自我拯救
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception{
        SAVE_HOOK = new FinalizeEscapeGC();

        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒等待它
        Thread.sleep(500);
        if (SAVE_HOOK!=null){
            System.out.println("yes, i am still alive...A");
        }else {
            System.out.println("no, i am dead...A");
        }

        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒等待它
        Thread.sleep(500);
        if (SAVE_HOOK!=null){
            System.out.println("yes, i am still alive...B");
        }else {
            System.out.println("no, i am dead...B");
        }
    }
}
