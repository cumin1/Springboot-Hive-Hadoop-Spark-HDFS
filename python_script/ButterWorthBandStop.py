#巴氏带阻滤波器
import numpy as np
from matplotlib import pyplot as plt
from scipy import signal
from scipy.signal import lfilter, butter, filtfilt, freqz
import sys
import os

plt.rcParams['font.sans-serif'] = ['SimHei']

#巴氏带阻波波器，cutoff ：设定截止频率,低于该频率滤掉，order：设定滤波器阶数，阶数越高那么越能够有效地抑制不需要的频率成分
def butter_bandstop_filter(data, fs, cutoff1,cutoff2,order):
    normal_f1 = 2*cutoff1/fs
    normal_f2 = 2*cutoff2/fs
    normal_f=[normal_f1,normal_f2]
    b, a = butter(N=order, Wn=normal_f, btype='bandstop', analog=False)
    # 根据阶数n和归一化截止频率Wn计算ButterWorth滤波器分子分母系数（b为分子系数的矢量形式，a为分母系数的矢量形式)
    y=np.array(filtfilt(b,a,data))
    w, h = freqz(b, a, worN=800)  # 计算滤波器的频率响应(Response),也就是对不同频段的放大倍数,0.707倍的是截止频率

    plt.figure(figsize=(16,5))

    plt.subplot(1, 3, 1)
    plt.plot(0.5 * fs * w / np.pi, np.abs(h), 'b')
    plt.plot(cutoff1, 0.5 * np.sqrt(2), 'ko')
    plt.axvline(cutoff1, color='r')
    plt.plot(cutoff2, 0.5 * np.sqrt(2), 'ko')
    plt.axvline(cutoff2, color='r')
    plt.xlim(0, 10)
    plt.title("频率响应图")
    plt.xlabel('频率')
    plt.grid()  # 画线

    plt.subplot(1, 3, 2)
    plt.plot(t, data, 'b-')
    plt.title('滤波前（基频、3、5、7、9次谐波）')
    plt.xlabel('时间')
    plt.grid()  #画线

    plt.subplot(1,3,3)
    plt.plot(t, y, 'g-', linewidth=2, label='滤波后')
    plt.title('滤波后(保留基频）')
    plt.xlabel('时间')
    plt.grid()  #画线

    # 保存图像到指定路径
    output_file = os.path.join("./tmp_img/bandstop_" + str(cutoff1) + "_" + str(int(cutoff2)) + "_" + str(order) + ".png")
    plt.savefig(output_file)
    plt.close()

# 入口函数
if __name__ == "__main__":
    fs = 300.0  # 采样频率
    T = 2.0  # 周期2s
    n = int(T * fs)  # 总样本数
    t = np.linspace(0, T, n, endpoint=False)
    data=0
    for i in [1,3,5,7,9]:    #基频加四个谐波组成的信号
        data=data+(1-(i-1)*0.1)*np.sin(i*2*np.pi*t)


    cutoff1 = float(sys.argv[1])
    cutoff2 = float(sys.argv[2])
    order = int(sys.argv[3])
    print(f"cutoff = {cutoff1}, order = {cutoff2}, rp = {order}")
    # 调用滤波器并保存图像
    output_image = butter_bandstop_filter(data, fs, cutoff1, cutoff2, order)

    print(output_image)  # 输出文件路径供后端读取
