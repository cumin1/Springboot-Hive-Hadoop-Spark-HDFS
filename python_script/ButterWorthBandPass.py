import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import butter, filtfilt, freqz
import io
import sys
import os

plt.rcParams['font.sans-serif'] = ['SimHei']

def butter_bandpass_filter(data, fs, cutoff1, cutoff2, order):
    normal_f1 = 2 * cutoff1 / fs
    normal_f2 = 2 * cutoff2 / fs
    b, a = butter(N=order, Wn=[normal_f1, normal_f2], btype='bandpass', analog=False)

    # 应用滤波器
    y = filtfilt(b, a, data)
    w, h = freqz(b, a, worN=800)

    # 绘制图像
    plt.figure(figsize=(10, 4))

    # 频率响应图
    plt.subplot(1, 3, 1)
    plt.plot(0.5 * fs * w / np.pi, np.abs(h), 'b')
    plt.axvline(cutoff1, color='r', linestyle='--')
    plt.axvline(cutoff2, color='r', linestyle='--')
    plt.title("Bandpass Frequency Response")
    plt.xlabel('Frequency (Hz)')
    plt.grid()

    # 滤波前信号图
    plt.subplot(1, 3, 2)
    plt.plot(t, data, 'b-')
    plt.title('Before Bandpass Filter')
    plt.xlabel('Time (s)')
    plt.grid()

    # 滤波后信号图
    plt.subplot(1, 3, 3)
    plt.plot(t, y, 'g-', linewidth=2)
    plt.title('After Bandpass Filter')
    plt.xlabel('Time (s)')
    plt.grid()

    plt.tight_layout()

    # 保存图像到指定路径
    output_file = os.path.join("./tmp_img/bandpass_" + str(cutoff1) + "_" + str(int(cutoff2)) + "_" + str(order) + ".png")
    plt.savefig(output_file)
    plt.close()

    return output_file

# 入口函数
if __name__ == "__main__":
    fs = 300.0  # 采样频率
    T = 2.0     # 周期2秒
    n = int(T * fs)
    t = np.linspace(0, T, n, endpoint=False)

    # 生成基频和谐波信号
    data = sum([(1 - (i - 1) * 0.1) * np.sin(i * 2 * np.pi * t) for i in [1, 3, 5, 7, 9]])

    cutoff1 = float(sys.argv[1])
    cutoff2 = float(sys.argv[2])
    order = int(sys.argv[3])
    print(f"cutoff = {cutoff1}, order = {cutoff2}, rp = {order}")
    # 调用滤波器并保存图像
    output_image = butter_bandpass_filter(data, fs, cutoff1, cutoff2, order)

    print(output_image)  # 输出文件路径供后端读取
