package com.yujing;

import com.yujing.utlis.YFileUtil;

import java.io.File;

/**
 * 修改build.gradle文件中的 gradle 和 kotlin版本
 *
 * @author yujing 2020年5月20日10:59:29
 */
/*
打包jar：
  1.项目右键 ——> 构建模块
  2.文件菜单 ——> 项目结构 ——> 点击构建（工作）——> 点击+ ——> 选择jar ——> 选择来自具有依赖项的模块 ——> 选择main函数，选择提取到目标jar --> 点击应用
  3.菜单构建 ——> 构建工件... ——> 构建
  4.生成的jar目录：build\artifacts\replace_jar\***.jar

使用方法：
call java -jar -Dfile.encoding=utf-8 createMD.jar  md文件夹路径  创建md文件名称  忽略列表文件  文件头内容  文件尾内容
call java -jar -Dfile.encoding=utf-8 createMD.jar ./ README.md ./ignore.txt ./HEAD.txt
 */
public class Main {
    public static void main(String... args) {
        Create create = new Create();
        //路径
        if (args.length > 0) create.rootDir = new File(args[0]);
        //创建的文件名
        if (args.length > 1) create.createName = args[1];
        //忽略列表文件
        if (args.length > 2) {
            File ignoreFile=new File(args[2]);
            String ignore=YFileUtil.fileToString(ignoreFile);
            if (ignore==null){
                System.out.println("忽略文件不存在");
                return;
            }
            //添加忽略目录
            create.ignoreList = ignore
                    .replace("\\", "/")
                    .replace("\r\n", "\n")
                    .split("\n");
        }
        //头部
        if (args.length > 3) {
            File headFile=new File(args[3]);
            create.headString=YFileUtil.fileToString(headFile);
        }
        //尾部
        if (args.length > 4) {
            File footFile=new File(args[4]);
            create.headString=YFileUtil.fileToString(footFile);
        }
        create.run();
    }
}
