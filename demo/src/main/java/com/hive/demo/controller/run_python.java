package com.hive.demo.controller;

import java.io.*;

public class run_python {
    public static void main(String[] args) throws IOException, InterruptedException {
        File pythonFile = new File("script.py"); // 假设在当前目录下创建名为script.py的文件

        System.out.println(pythonFile.getAbsolutePath());

        try (FileWriter fw = new FileWriter(pythonFile)) {
            fw.write("print(\"Hello from Python!\")"); // 写入Python代码
            fw.flush();
            System.out.println("Python script created and written successfully.");

            // 构建ProcessBuilder来运行Python脚本
            ProcessBuilder pb = new ProcessBuilder("python", pythonFile.getAbsolutePath());

            // 合并错误流和输出流
            pb.redirectErrorStream(true);

            // 启动进程
            Process p = pb.start();

            // 读取进程的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待进程结束
            int exitCode = p.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
