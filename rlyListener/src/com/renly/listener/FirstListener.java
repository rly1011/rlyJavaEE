package com.renly.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author ${任岚杨}
 * @title: com.renly.listener.FirstListener
 * @projectName rlyJavaEE
 * @description: 监听器是一个专门用于对其他对象身上发生的事件或状态改变进行监听和相应处理的对象，
 * 当被监视的对象发生情况时，立即采取相应的行动。监听器其实就是一个实现特定接口的普通java程序，
 * 这个程序专门用于监听另一个java对象的方法调用或属性改变，当被监听对象发生上述事件后，监听器某个方法立即被执行。
 * @date 2019/5/219:40
 */
//@WebListener
public class FirstListener implements ServletContextListener {
    /**
     * 应用启动，该方法被调用
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("我是间谍，专门监听你们这个项目");
    }

    /**
     * 应用关闭，该方法调用
      * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("任务完成，回到我方阵地");
    }
}
