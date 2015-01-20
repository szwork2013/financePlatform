# 如何配置开发环境
===========================================
1. 安装好JDK1.6以上的版本，设置好JAVA_HOME环境变量
2. 下载activator-1.2.10， 解压，然后把路径添加到path环境变量，如D:\activator-1.2.10
3. 安装好intellij IDEA , 安装好play2 和 scala 插件
4. 到用户目录下，例如：C:\Users\Administrator\.sbt， 下面去创建文件repositories，把下面的内容放到文件里

          [repositories]
            local
            sunlights nexus:http://saturn:8081/nexus/content/groups/public/
                typesafe-ivy-releases: http://repo.typesafe.com/typesafe/            releases/, [organization]/[module]/[revision]/[type]s/[artifact](-[classifier]).[ext]
            maven-central
            sonatype-snapshots: https://oss.sonatype.org/content/repositories/snapshots
5. 安装好git
6. git clone git@EARTH:sunlights/financeplatform.git
7. 导入项目到intellij IDEA.

# 模块介绍和他们的依赖关系
====================================
## 模块介绍
* `common`: 实现了jpa entitiy model和一些基础的工具类
* `customer`:
* `account`：
* `core`: 实现了安全，注册，短信，产品
* `trade`：

## 模块之间的依赖关系
core->customer->common
core->account->common
trade->core

# 代码重构
========================================
1. 把现在代码移动到finacePlatform
2. 完善单元测试用例
3. 为controller接口添加JavaDoc， 参考CustomerController里的方式


# 代码规范
请安装IDEA的checkstyle， PMD， findBugs插件

# Bug 管理原则
我们现在用 GitLab 中得 Issues 做简单的 bug/defect/issue 追踪管理。 GitLab 的这个功能相比 bugzilla, mantis 或者 redmine 的 issue 管理要弱很多，但目前对我们还够用，所以在 issue 管理上，还是要做一些必要的流程要求:

1. 一个 issue 创建以后，创建人(creator)应尽快 assign 给相关项目的 leader (目前 iOS app 的 leader 是承朝，后端和官网的 leader 是罗奎)
2. 创建 issue 时，分配对应的 label (功能错误，功能缺失，等等);
3. 项目 leader 应尽快将 issue 再分配给对应的工程师；
4. 工程师修正了 issue 以后，添加 Fixed 标签，并且 re-assign 给项目 leader;
5. 待新版本发布，项目 leader 将 Fixed 的 issue 重新分配给创建人 (creator)；
6. issue 的创建人重新测试以后，或者 Close 该 issue；或者没有测试通过，那么 re-open 并且 re-assign给项目 leader，同时去除 Fixed 标签；

有几个原则整个团队需要共同遵守:
1. issue 撰写的方式: 要足够详细，能够根据描述重现；
2. 谁创建，谁关闭；
3. 先解决 issue，再做新功能；


# Guava库使用
* [Guava教程](http://outofmemory.cn/java/guava/)

# 设置publish项目到maven库
* 在目录 .ivy2 下面创建 文件.credentials把下面的内容放进去

        realm=Sonatype Nexus Repository Manager
        host=192.168.0.97
        user=admin
        password=admin123

* 在需要publish的项目的build.sbt文件里添加下面的内容

        credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
        publishTo <<= version { v: String =>
          val nexus = "http://192.168.0.97:8081/nexus/"
          if (v.trim.endsWith("SNAPSHOT"))
            Some("snapshots" at nexus + "content/repositories/snapshots")
          else
            Some("releases" at nexus + "content/repositories/releases")
          }

* 运行activator publish， 如果是子项目，需要运行 activator， 然后运行 project <<子项目名>>,  publish


* import dependency to other project

    1. add repositories to build.sbt
    
            resolvers ++= Seq(
            "Sunlights 3rd party" at "http://192.168.0.97:8081/nexus/content/repositories/thirdparty",
            "Sunlights snapshots" at "http://192.168.0.97:8081/nexus/content/repositories/snapshots/",
            "Sunlights releases" at "http://192.168.0.97:8081/nexus/content/repositories/releases/",
              )
      
    2. add dependency to build.sbt
     
             libraryDependencies ++= Seq(
                "cs" % "cs_2.11" % "1.0-SNAPSHOT"
             )


# 数据库脚本维护：

    1.在/common/conf/db/db.sql中增加数据库脚本语句
    
    2.在http://earth/sunlights/documents /Version-1/设计文档/设计文档2.0/表设计/db/db.xlsx
      中维护好单个数据库脚本，写好描述，和各个环境到是否执行的状态，详细的可以看下db.xlsx里到书写要求


# How to use arcanist 做code review流程

```

git clone https://github.com/phacility/arcanist.git
git clone https://github.com/phacility/libphutil.git

然后 把/Users/loki/git/arcanist/bin 添加到path目录。
运行arc help去检查安装是否成功。

In Git, arc diff sends all commits in a range for review. By default, this range is:

`git merge-base origin/master HEAD`..HEAD
That's a fancy way of saying "all the commits on the current branch that you haven't pushed yet". So, to create a revision in Git, run:

$ nano source_code.c  # Make changes.
$ git commit -a       # Commit changes.
$ arc diff            # Creates a new revision out of ALL unpushed commits on
                      # this branch.
The git commit step is optional. If there are uncommitted changes in the working copy then Arcanist will ask you to create a commit from them.

Since it uses all the commits on the branch, you can make several commits before sending your changes for review if you prefer.

You can specify a different commit range instead by running:

$ arc diff <commit>
This means to use the range:

`git merge-base <commit> HEAD`..HEAD
However, this is a relatively advanced feature. The default is usually correct if you aren't creating branches-on-branches, juggling remotes, etc.

To update a revision, just do the same thing:

$ nano source_code.c  # Make more changes.
$ git commit -a       # Commit them.
$ arc diff            # This prompts you to update revision information.
The git commit step is optional. If there are uncommitted changes in the working copy then Arcanist will ask you to amend them to the commit.

When your revision has been accepted, you can usually push it like this:

$ arc land <branch>   # Merges <branch> into master and pushes.
arc land makes some assumptions about your workflow which might not be true. Consult the documentation before you use it. You should also look at arc amend, which may fit your workflow better.

如果revision被打回来了，我们在这个分支上修改后再提交，这个时候需要使用arc patch <你的revision号> 如 D25
```










