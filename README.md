[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d548996945ea4ebc83a7cb89beeb6b89)](https://www.codacy.com/app/dongzhuming/math-training?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=dongzhuming/math-training&amp;utm_campaign=Badge_Grade)

# math-training
Spring boot 项目，启动后访问http://localhost:8080/download?count=?  
为方便家长打印并生成指定数量的练习题，可根据不同的模板进行配置，如model-40.xlsx中每个Sheet为40道题目  
参数配置位于application.yml中  
后期将会以此版本逐步完善，并提供交互界面  

目标，提升孩子的算术正确率！  

## 应用后台
使用Spring Boot编写后台程序，默认参数可在src/main/resources/application.yml中修改  


## docker 启动
sudo docker build -t math-training:1.0 https://github.com/dongzhuming/math-training.git

