package com.hive.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/python")
public class PythonExecutorController {


    @PostMapping(value = "/execute")
    public String executePython(@RequestBody Map params) {
        String pythonCode = params.get("code").toString();

        String code = pythonCode.replace("\\", "");

        File pythonFile = new File("script.py");

        try (FileWriter fw = new FileWriter(pythonFile)) {
            fw.write(code); // 写入Python代码
            fw.flush();
            System.out.println("Python script created and written successfully.");

            // 构建ProcessBuilder来运行Python脚本
            ProcessBuilder pb = new ProcessBuilder("python", pythonFile.getAbsolutePath());

            // 合并错误流和输出流
            pb.redirectErrorStream(true);

            // 启动进程
            Process p = pb.start();

            StringBuilder output = new StringBuilder();

            // 读取进程的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                output.append(line).append(System.lineSeparator());
            }

            // 等待进程结束
            p.waitFor();

            return output.toString();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return "error";
    }
}
