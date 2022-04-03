Prerequisite
1. Java 11
2. Intellij Idea or other idea
3. postgresql already installed on local machine/ already installed on docker container
4. Maven as build tools


How to run app
1. import app to intellij idea
2. download dependency using maven
3. Run Main class



List API
1. category
   POST 	->localhost:8080/categories
   GET  	->localhost:8080/categories
   GET		->localhost:8080/categories/1
   PUT 		->localhost:8080/categories
   DELETE 	-> localhost:8080/categories/3
   catefory api -> used for manipulate category

 2. Video
   POST 	->localhost:8080/videos     	-> used for save/upload  new video
   GET 		->localhost:8080/videos			-> used for list movies and filtering by category
   GET 		->localhost:8080/videos/3		-> used for detail video
   DELETE 	->localhost:8080/videos/3  		-> used for delete video with spesific id
   GET 		->localhost:8080/videos/search	-> filtering video by name


