#  need to know

This chapter explains how you can develop your own servlet container by presenting tow applications.The first application has been designed to be as simple as possible to make it easy for you to understand how a servlet container works.It then evolves  into the second servlet container.which is slightly more complex.

NOTE: Every servlet container application in each chapter gradually evolves from the application,until a fully-function Tomcat servlet container is built later.

## First application
the first application consists of six classes:
- HttpServlet1
- Request
- Response
- StaticResourceProcessor
- ServletProcessor1
- Constants
- PrimitiveServlet(in **webroot** folder)

**Note** : you must compile PrimitiveServlet.java before you run application.
```
javac -classpath url(servlet-api.jar's url) PrimitiveServlet.java
```
another way:
- HttpServlet1
- Request
- Response
- StaticResourceProcessor
- ServletProcessor1
- Constants
and package **test**
- PrimitiveServlet

## Second application
Here are the classes used in Application2:
- HttpServer2
- Request
- Response
- StaticResourceProcessor
- ServletProcessor2
- Constants
- PrimitiveServlet(in **webroot** folder)


### Difference between of Application1 and Application2
The HttpServer2 class is similar to HttpServer1, except that it uses ServletProcessor2 in its await method, instead of ServletProcessor1:
```
 if (request.getUri().startWith("/servlet/")) {
 servletProcessor2 processor = new ServletProcessor2();
 processor.process(request, response);
 }
 else {
 ...
 }
```
The ServletProcessor2 class is similar to ServletProcessor1, except in the following part of its process method:
```
 Servlet servlet = null;
 RequestFacade requestFacade = new RequestFacade(request);
 ResponseFacade responseFacade = new ResponseFacade(response);
 try {
     servlet = (Servlet) myClass.newInstance();
     servlet.service((ServletRequest) requestFacade,
 (ServletResponse) responseFacade);
 }

```
### Note:
There is a serious problem in the first application. In the ServletProcessor1 class's process method, you upcast the instance of ex02.pyrmont.Request to javax.servlet.ServletRequest and pass it as the first argument to the servlet's service method. You also upcast the instance of ex02.pyrmont.Response to javax.servlet.ServletResponse and pass it as the second argument to the servlet's service method.
```
 try {
 servlet = (Servlet) myClass.newInstance();
 servlet.service((ServletRequest) request,
 (ServletResponse) response);
 }
```
This compromises security. Servlet programmers who know the internal workings of this servlet container can downcast the ServletRequest and ServletResponse instances back to test02.Request and test02.Response respectively and call their public methods. Having a Request instance, they can call its parse method. Having a Response instance, they can call its sendStaticResource method. You cannot make the parse and sendStaticResource methods private because they will be called from other classes. However, these two methods are not supposed to be available from inside a servlet. One solution is to make both Request and Response classes have default access modifier, so that they cannot be used from outside the test02 package. However, there is a more elegant solution: by using facade classes.

In this second application, we add two façade classes: RequestFacade and ResponseFacade.RequestFacade implements the ServletRequest interface and is
instantiated by passing a Request instance that it assigns to a ServletRequest object reference in its constructor. Implementation of each method in the ServletRequest interface invokes the corresponding method of the Request object. However, the ServletRequest object itself is private and cannot be accessed from outside the class. Instead of upcasting the Request object to ServletRequest and passing it to the  service method, we construct a RequestFacade object and pass it to the service method. Servlet programmers can still downcast the ServletRequest instance back to RequestFacade, however they can only access the methods available in the ServletRequest interface. Now, the parseUri method is safe.