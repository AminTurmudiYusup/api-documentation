# **How to create documentation for Rest API**
Prerequisite
1. You have api which build using springboot
2. API already import to intellij IDEA
3. You have connection internet for download springdoc-openapi


# **Let's jump right in**
1. add springdoc-openapi to pom.xml
2. reload project, for download openapi from maven central to your maven local
3. Setting up springdoc-openapi, add this in the appliaction.properties = springdoc.api-docs.path=/api-docs
3. to see documentation in swagger ui add this in the application.properties = springdoc.swagger-ui.path=/swagger-ui-custom.html
4. add some description to our api (open your controller)
5. run your api
