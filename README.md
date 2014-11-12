# 如何配置开发环境
===========================================
1. 安装好JDK1.6以上的版本，设置好JAVA_HOME环境变量
2. 下载activator-1.2.10， 解压，然后把路径添加到path环境变量，如D:\activator-1.2.10
3. 安装好intellij IDEA , 安装好play2 和 scala 插件
4. 到用户目录下，例如：C:\Users\Administrator\.sbt， 下面去创建文件repositories，把下面的内容放到文件里
  [repositories]
    local
    sunlights nexus:http://saturn:8081/nexus/content/groups/public/
    typesafe-ivy-releases: http://repo.typesafe.com/typesafe/releases/, [organization]/[module]/[revision]/[type]s/[artifact](-[classifier]).[ext]
    maven-central
    sonatype-snapshots: https://oss.sonatype.org/content/repositories/snapshots
5. 安装好git
6. git clone git@EARTH:sunlights/financeplatform.git
7. 导入项目到intellij IDEA.

# 模块介绍和他们的依赖关系
====================================
## 模块介绍
* common: 实现了jpa entitiy model和一些基础的工具类
* customer:
* account：
* core: 实现了安全，注册，短信，产品
* trade：

## 模块之间的依赖关系
core->customer->common
core->account->common
trade->core

#代码重构
========================================
1. 把现在代码移动到finacePlatform
2. 完善单元测试用例
3. 为controller接口添加JavaDoc， 参考CustomerController里的方式


#代码规范
请安装IDEA的checkstyle， PMD， findBugs插件

# Guava库使用
* [Guava教程](http://outofmemory.cn/java/guava/)










