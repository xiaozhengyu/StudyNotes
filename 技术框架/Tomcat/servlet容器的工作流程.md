# Servlet容器的工作流程

---

servlet容器是一个复杂的系统，但是它有3个基本任务，对于每个HTTP请求，servlet容器会为其完成以下3个操作：

1. 创建一个request对象，用调用servlet时可能会使用到的信息填充request对象，如请求参数、请求头、cookie、URI等。
2. 创建一个调用servlet的response对象，用于向客户端返回响应信息。
3. 调用servlet的service()方法，将request对象和response对象作为参数传入。servlet从request对象中获取信息，并通过response对象发送响应信息。