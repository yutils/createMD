package com.yujing;

import com.yujing.utlis.YFileUtil;

import java.io.File;
import java.io.FileFilter;

public class Create {
    //目录
    public File rootDir = new File("");
    //创建的文件名称
    public String createName = "README.md";
    //忽略文件名
    public String[] ignoreList = new String[0];

    //文件头部写入的内容
    public String headString = "";
    //文件尾部写入的内容
    public String footString = "";

    public void run() {
        StringBuilder str = new StringBuilder(headString);
        str.append("\r\n");
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                //忽略
                boolean isIgnore = false;
                //相对路径，前面不带/后面也不带/
                String relative = file.getPath().substring(rootDir.getPath().length() + 1);
                relative = relative.replace("\\", "/");
                //忽略文件
                for (String ignore : ignoreList) {
                    //防止空串
                    if (ignore.trim().isEmpty()) continue;
                    //这是一行注释
                    if (ignore.charAt(0) == '#') continue;
                    //头部是/
                    if (ignore.charAt(0) == '/') {
                        if (relative.startsWith(ignore.substring(1))) {
                            System.out.println("忽略文件：" + relative);
                            isIgnore = true;
                        }
                    }
                    //头部是*
                    if (ignore.charAt(0) == '*') {
                        if (relative.endsWith(ignore.substring(1))) {
                            System.out.println("忽略文件：" + relative);
                            isIgnore = true;
                        }
                    }
                }
                //是否忽略
                if (!isIgnore) {
                    str.append(line(file)).append("\r\n");
                }
                //递归
                if (file.isDirectory() && !isIgnore) file.listFiles(this);
                return false;
            }
        };
        rootDir.listFiles(filter);
        str.append("\r\n");
        str.append(footString);
        File write = new File(rootDir.getPath() + File.separator + createName);
        System.out.println(str);
        System.out.println("写入磁盘：" + write.getPath());
        //写盘
        YFileUtil.stringToFile(write, str.toString());
    }

    //生成1行
    /*
    - ##  [web](web)
       - ### [nacos](web/nacos)
          - #### [java中使用Nacos.md](web/nacos/java中使用Nacos.md)
     */
    public String line(File file) {
        StringBuilder str = new StringBuilder();
        //相对路径，前面不带/后面也不带/
        String relative = file.getPath().substring(rootDir.getPath().length() + 1);
        //统计目录级数
        int count = relative.length() - relative.replace(File.separator, "").length();
        /*
        - [android](android)
          - [AS使用](android/AS使用)
            - [常用引用.md](android/AS使用/常用引用.md)
              - [镜像代理.md](android/AS使用/镜像代理.md)
                - [AS打包jar.md](android/AS使用/AS打包jar.md)
                  - [AS配置SVN.md](android/AS使用/AS配置SVN.md)
         */
        //添加空格，每一级添加2空格
        str.append("  ".repeat(Math.max(0, count)));
        str.append("- ##");
        //添加#，2#起步，每一级添加1#
        for (int i = 0; i < count && i < 4; i++) {
            str.append("#");
        }
        str.append(" [").append(file.getName()).append("](").append(relative.replace("\\", "/")).append(")");
        return str.toString();
    }
}
