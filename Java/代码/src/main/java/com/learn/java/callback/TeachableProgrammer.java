package com.learn.java.callback;

/**
 * @author xzy
 * @date 2020/11/29 19:16
 * 说明：
 */
public class TeachableProgrammer extends Programmer {
    private Sideline sideline = new Sideline();

    public TeachableProgrammer(String name) {
        super(name);
    }

    private void teach() {
        System.out.println(this.getName() + " is teaching.");
    }

    private class Sideline implements Teachable {
        @Override
        public void work() {
            TeachableProgrammer.this.teach();
        }
    }

    public Teachable getSideline() {
        return this.sideline;
    }
}
