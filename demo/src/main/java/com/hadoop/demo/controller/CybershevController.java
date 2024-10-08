package com.hadoop.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin(origins = "*")
public class CybershevController {

    @GetMapping("/generate-image-cylowpass")
    public void generateImage_cylowpass(@RequestParam double cutoff, @RequestParam int N, @RequestParam double rp, HttpServletResponse response) {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ChebyshevLowPass.py",
                Double.toString(cutoff), Integer.toString(N), Double.toString(rp));
        processBuilder.inheritIO();
        Process process = null;
        try {
            process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cutoff: " + cutoff);
        System.out.println("N: " + N);
        System.out.println("rp: " + rp);

        // 读取生成的图像
        File imageFile = new File("./tmp_img/cylowpass_" + cutoff + "_" + N + "_" + rp + ".png");
        if (imageFile.exists()) {
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "inline; filename=\"" + imageFile.getName() + "\"");

            try (InputStream inputStream = new FileInputStream(imageFile);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/generate-image-cyhighpass")
    public void generateImage_cyhighpass(@RequestParam double cutoff, @RequestParam int N, @RequestParam double rp, HttpServletResponse response) {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ChebyshevHighPass.py",
                Double.toString(cutoff), Integer.toString(N), Double.toString(rp));
        processBuilder.inheritIO();
        Process process = null;
        try {
            process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cutoff: " + cutoff);
        System.out.println("N: " + N);
        System.out.println("rp: " + rp);

        // 读取生成的图像
        File imageFile = new File("./tmp_img/cyhighpass_" + cutoff + "_" + N + "_" + rp + ".png");
        if (imageFile.exists()) {
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "inline; filename=\"" + imageFile.getName() + "\"");

            try (InputStream inputStream = new FileInputStream(imageFile);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/generate-image-cybandpass")
    public void generateImage_cybandpass(@RequestParam double cutoff, @RequestParam int N, @RequestParam double rp, HttpServletResponse response) {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ChebyshevBandPass.py",
                Double.toString(cutoff), Integer.toString(N), Double.toString(rp));
        processBuilder.inheritIO();
        Process process = null;
        try {
            process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cutoff: " + cutoff);
        System.out.println("N: " + N);
        System.out.println("rp: " + rp);

        // 读取生成的图像
        File imageFile = new File("./tmp_img/cybandpass_" + cutoff + "_" + N + "_" + rp + ".png");
        if (imageFile.exists()) {
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "inline; filename=\"" + imageFile.getName() + "\"");

            try (InputStream inputStream = new FileInputStream(imageFile);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/generate-image-cybandstop")
    public void generateImage_cybandstop(@RequestParam double cutoff, @RequestParam int N, @RequestParam double rp, HttpServletResponse response) {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ChebyshevBandStop.py",
                Double.toString(cutoff), Integer.toString(N), Double.toString(rp));
        processBuilder.inheritIO();
        Process process = null;
        try {
            process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cutoff: " + cutoff);
        System.out.println("N: " + N);
        System.out.println("rp: " + rp);

        // 读取生成的图像
        File imageFile = new File("./tmp_img/cybandstop_" + cutoff + "_" + N + "_" + rp + ".png");
        if (imageFile.exists()) {
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "inline; filename=\"" + imageFile.getName() + "\"");

            try (InputStream inputStream = new FileInputStream(imageFile);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
