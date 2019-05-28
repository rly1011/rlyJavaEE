#   监听器Listener分类

##  第一大类：监听对象的创建和销毁

1.ServletContextListener 
  
    监听servletContext对象的创建以及销毁
-   contextInitialized(ServletContextEvent arg0)   -- 创建时执行
-   contextDestroyed(ServletContextEvent arg0)  -- 销毁时执行

2.HttpSessionListener

    监听session对象的创建以及销毁

-   sessionCreated(HttpSessionEvent se)   -- 创建时执行

-   sessionDestroyed(HttpSessionEvent se) -- 销毁时执行

3.ServletRequestListener

    监听request对象的创建以及销毁

-   requestInitialized(ServletRequestEvent sre) -- 创建时执行

-   requestDestroyed(ServletRequestEvent sre) -- 销毁时执行

##  第二大类：监听对象属性的改变

1.ServletContextAttributeListener

    监听servletContext对象中属性的改变

-   attributeAdded(ServletContextAttributeEvent event) -- 添加属性时执行

-   attributeReplaced(ServletContextAttributeEvent event) -- 修改属性时执行

-   attributeRemoved(ServletContextAttributeEvent event) -- 删除属性时执行

2.HttpSessionAttributeListener

    监听session对象中属性的改变

-   attributeAdded(HttpSessionBindingEvent event) -- 添加属性时执行

-   attributeReplaced(HttpSessionBindingEvent event) -- 修改属性时执行

-   attributeRemoved(HttpSessionBindingEvent event) -- 删除属性时执行

3.ServletRequestAttributeListener

    监听request对象中属性的改变

-   attributeAdded(ServletRequestAttributeEvent srae) -- 添加属性时执行

-   attributeReplaced(ServletRequestAttributeEvent srae) -- 修改属性时执行

-   attributeRemoved(ServletRequestAttributeEvent srae) -- 删除属性时执行

#   第三大类：不常用监听

1.HttpSessionActivationListener

    session对象中属性的钝化与活化

2.sessionWillPassivate(HttpSessionEvent se)

    钝化之前调用

3.sessionDidActivate(HttpSessionEvent se)

    活化之后调用

4.HttpSessionBindingListener

    实例对象与session的绑定与解绑

-   valueBound(HttpSessionBindingEvent event) -- 绑定时调用

-   valueUnbound(HttpSessionBindingEvent event) -- 解绑时调用
