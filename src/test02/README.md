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

and folder **webroot**
- PrimitiveServlet

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

