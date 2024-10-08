import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import butter, filtfilt, freqz
import io
import sys

plt.rcParams['font.sans-serif'] = ['SimHei']

def generate_high_pass_plot(cutoff, order, rp):
    fs = 300.0  # 采样频率
    T = 2.0     # 周期2秒
    n = int(T * fs)  # 样本数
    t = np.linspace(0, T, n, endpoint=False)

    # 生成基频和谐波信号
    data = sum([(1 - (i - 1) * 0.1) * np.sin(i * 2 * np.pi * t) for i in [1, 3, 5, 7, 9]])

    # 创建Butterworth高通滤波器
    b, a = butter(N=order, Wn=cutoff / (0.5 * fs), btype='highpass')
    y = filtfilt(b, a, data)  # 应用滤波器
    w, h = freqz(b, a, worN=800)

    # 创建图像
    plt.figure(figsize=(10, 4))

    # 频率响应图
    plt.subplot(1, 3, 1)
    plt.plot(0.5 * fs * w / np.pi, np.abs(h), 'b')
    plt.axvline(cutoff, color='r', linestyle='--')
    plt.title("Highpass Frequency Response")
    plt.xlabel('Frequency (Hz)')
    plt.grid()

    # 滤波前信号图
    plt.subplot(1, 3, 2)
    plt.plot(t, data, 'b-')
    plt.title('Before Highpass Filter')
    plt.xlabel('Time (s)')
    plt.grid()

    # 滤波后信号图
    plt.subplot(1, 3, 3)
    plt.plot(t, y, 'g-', linewidth=2)
    plt.title('After Highpass Filter')
    plt.xlabel('Time (s)')
    plt.grid()

    plt.tight_layout()

    # 使用 io.BytesIO 保存图像到内存
    buf = io.BytesIO()
    plt.savefig(buf, format='png')
    plt.close()

    # 将指针移动到开头
    buf.seek(0)

    return buf  # 返回字节流对象

# 入口函数
if __name__ == "__main__":
    cutoff = float(sys.argv[1])
    order = int(sys.argv[2])
    rp = float(sys.argv[3])  # rp 参数可以在这里用作绘图的其他目的或调整
    print(f"cutoff = {cutoff}, order = {order}, rp = {rp}")
    image_stream = generate_high_pass_plot(cutoff, order, rp)

    # 你可以在这里将 image_stream 写入文件或通过 HTTP 发送
    with open("./tmp_img/lowpass_" + str(cutoff) + "_" + str(order) + "_" + str(int(rp)) + ".png", 'wb') as f:
        f.write(image_stream.read())

