package com.hive.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.io.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/python")
public class PythonExecutorController {

    @PostMapping(value = "/execute")
    public ResponseEntity<String> executePython(@RequestBody Map<String, Object> params) {
        // 验证参数
        if (!params.containsKey("code") || !(params.get("code") instanceof String)) {
            return ResponseEntity.badRequest().body("Missing or invalid 'code' parameter.");
        }

        String pythonCode = (String) params.get("code");
        // 简单的清理，但注意这不足以防止所有类型的注入攻击
        String cleanedCode = pythonCode.replace("\\", "").trim();

        File pythonFile = new File("script.py");

        try (FileWriter fw = new FileWriter(pythonFile)) {
            fw.write(cleanedCode); // 写入Python代码
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
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    output.append(line).append(System.lineSeparator());
                }
            }

            // 等待进程结束并处理可能的异常
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                return ResponseEntity.internalServerError().body("Python script execution failed with exit code " + exitCode);
            }

            return ResponseEntity.ok(output.toString());
        } catch (IOException | InterruptedException e) {
            // 记录更详细的错误信息到日志中
            e.printStackTrace(); // 在生产环境中，建议使用日志框架（如SLF4J）
            return ResponseEntity.internalServerError().body("An error occurred while executing the Python script.");
        }

        // 理论上不应该执行到这里，但保留以符合原始逻辑
        // 如果确实执行到这里，可能是因为FileWriter或ProcessBuilder构造时抛出了RuntimeException
//        return ResponseEntity.internalServerError().body("Unexpected error occurred.");
    }
}
