package com.hadoop.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin(origins = "*")
public class ButterWorthController {

    // 低通滤波器接口
    @GetMapping("/generate-image-lowpass")
    public void generate_lowpass_Image(@RequestParam double cutoff, @RequestParam int order, HttpServletResponse response) throws IOException {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ButterWorthLowPass.py",
                String.valueOf(cutoff), String.valueOf(order));
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 读取生成的图像
        File imageFile = new File("./tmp_img/lowpass_" + cutoff + "_" + order + ".png");
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
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // 高通滤波器接口
    @GetMapping("/generate-image-highpass")
    public void generate_high_pass_Image(@RequestParam double cutoff, @RequestParam int N, @RequestParam int rp, HttpServletResponse response) throws IOException {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ButterWorthHighPass.py",
                String.valueOf(cutoff), String.valueOf(N), String.valueOf(rp));
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cutoff: " + cutoff);
        System.out.println("N: " + N);
        System.out.println("rp: " + rp);

        // 读取生成的图像
        File imageFile = new File("./tmp_img/highpass_" + cutoff + "_" + N + "_" + rp + ".png");
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
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // 带通滤波器接口
    @GetMapping("/generate-image-bandpass")
    public void generate_band_pass_Image(@RequestParam double cutoff, @RequestParam int N, @RequestParam int rp, HttpServletResponse response) throws IOException {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ButterWorthBandPass.py",
                String.valueOf(cutoff), String.valueOf(N), String.valueOf(rp));
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cutoff: " + cutoff);
        System.out.println("N: " + N);
        System.out.println("rp: " + rp);

        // 读取生成的图像
        File imageFile = new File("./tmp_img/bandpass_" + cutoff + "_" + N + "_" + rp + ".png");
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
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // 巴氏带阻滤波器
    @GetMapping("/generate-image-bandstop")
    public void generate_band_stop_Image(@RequestParam double cutoff, @RequestParam int N, @RequestParam int rp, HttpServletResponse response) throws IOException {
        // 调用 Python 脚本
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./python_script/ButterWorthBandStop.py",
                String.valueOf(cutoff), String.valueOf(N), String.valueOf(rp));
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cutoff: " + cutoff);
        System.out.println("N: " + N);
        System.out.println("rp: " + rp);

        // 读取生成的图像
        File imageFile = new File("./tmp_img/bandstop_" + cutoff + "_" + N + "_" + rp + ".png");
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
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
