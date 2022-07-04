# markdown文件夹自动创建首页md目录

当我们一个文件夹下有很多markdown文件的时候，我们寻找markdown文件可能不方便，如果有文件夹嵌套就更加麻烦了。当我们添加一个md作为目录，连接到其他md文件就方便很多，但是新增的文件又要再次维护目录md文件。

我闲下来写一个小工具，自动生成一个mark当目录文件。这样我们直接创建md文件或者文件夹就不再需要维护我们的目录了。
项目案例查看此工程：[https://gitee.com/yos/note](https://gitee.com/yos/note)

## 用法：

```bat
call java -jar -Dfile.encoding=utf-8 createMD.jar  md文件夹路径  创建md文件名称  忽略列表文件  文件头内容  文件尾内容
call java -jar -Dfile.encoding=utf-8 createMD.jar ./ README.md ./ignore.txt ./HEAD.txt
```

## 项目案例

[https://gitee.com/yos/note](https://gitee.com/yos/note)

## IDEA中把项目打包成jar

  1.项目右键 ——> 构建模块

  2.文件菜单 ——> 项目结构 ——> 点击构建（工作）——> 点击+ ——> 选择jar ——> 选择来自具有依赖项的模块 ——> 选择main函数，选择提取到目标jar --> 点击应用

  3.菜单构建 ——> 构建工件... ——> 构建
  
  4.生成的jar目录：build\artifacts\replace_jar\***.jar

### 各个文件内容
 1. createMD.bat
    ```bat
    echo off
    ::配置
    call java -jar -Dfile.encoding=utf-8 createMD.jar ./ README.md ./ignore.txt ./HEAD.txt
    echo 程序执行完毕！
    pause
    ```

 2. HEAD.txt
    ```markdown
    # 私人笔记 #
    ---
    ### 特此记录。
    ```

 3. ignore.txt
    ```ignorelang
    /.git
    /LICENSE
    /ignore.txt
    /README.md
    /createMD.bat
    /createMD.jar
    /HEAD.txt
    *images
    ```
    
开源地址：[https://github.com/yutils/createMD](https://github.com/yutils/createMD)
