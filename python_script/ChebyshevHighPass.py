#切比雪夫高通滤波器
import numpy as np
from matplotlib import pyplot as plt
from scipy import signal
from scipy.signal import lfilter, butter, filtfilt, freqz
from matplotlib import patches
import sys
import os

plt.rcParams['font.sans-serif'] = ['SimHei']

fs = 300.0  # 采样频率

T = 2.0  # 周期2s
n = int(T * fs)  # 总样本数
t = np.linspace(0, T, n, endpoint=False)
data=np.random.rand(n)    #高斯噪声
for i in [1,3,5,7,9]:    #基频加四个谐波组成的信号
    data=data+(1-(i-1)*0.1)*np.sin(i*2*np.pi*t)

worN = 1024

# 获取角度，转换成 360 度形式
def angle(h):
    return np.unwrap(np.angle(h)) * 360 / 2 / np.pi


## 画零极点图
def plot_plane(ax, z, p):
    unit_circle = patches.Circle((0,0), radius=1, fill=False,
                                 color='black', ls='solid', alpha=0.8)
    ax.add_patch(unit_circle)
    poles = plt.plot(p.real, p.imag, 'x', markersize=9, alpha=0.8,color='red')
    zeros = plt.plot(z.real, z.imag, 'o', markersize=9,
             color='none', alpha=0.8,
             markeredgecolor=poles[0].get_color())
    plt.axis('scaled')
    r = 1.1 * np.amax(np.concatenate((abs(z), abs(p), [1])))
    plt.axis([-r, r, -r, r])



#切比雪夫高通波波器，cutoff ：设定截止频率,高于该频率滤掉，order：设定滤波器阶数，阶数越高波纹越陡
def cheby1_highpass_filter(data,N,rp,fs, cutoff):
    normal_f = 2*np.array(cutoff)/fs
    #cheby1(N, rp, Wn, btype='low', analog=False, output='ba', fs=None)
    b, a = signal.cheby1(N,rp,Wn=normal_f,btype='highpass',analog=False)
    # 根据阶数n和归一化截止频率Wn计算切比雪夫滤波器分子分母系数（b为分子系数的矢量形式，a为分母系数的矢量形式)
    y=np.array(filtfilt(b,a,data))
    #w, H = signal.freqz(b, a, worN=worN, whole=False)
    w, h = signal.freqz(b, a, worN)  # 计算滤波器的频率响应(Response),也就是对不同频段的放大倍数,0.707倍的是截止频率


    # 获取零极点
    z, p, k = signal.tf2zpk(b, a)

    plt.figure(figsize=(10,10))

    ax = plt.subplot(421)
    plt.title('零极点')
    plot_plane(ax, z, p)
    plt.plot()

    plt.subplot(422)
    plt.plot(0.5 * fs * w / np.pi, np.abs(h), 'b')
    plt.plot(cutoff, 0.5 * np.sqrt(2), 'ko')
    plt.axvline(cutoff, color='r')
    plt.xlim(0, 10)
    plt.title("频率响应图")
    plt.xlabel('频率[radians / second]')
    plt.ylabel('幅度[dB]')
    plt.margins(0, 0.1)
    plt.grid(which='both', axis='both')
    plt.axvline(cutoff, color='green') # cutoff frequency
    #plt.axhline(rp, color='green') # rp
    #plt.grid()  # 画线

    plt.subplot(423)
    plt.plot(t, data, 'b-')
    plt.title('滤波前（基频、3、5、7、9次谐波）')
    plt.xlabel('时间[second]')
    plt.ylabel('振幅')
    plt.grid()  #画线

    plt.subplot(424)
    plt.plot(t, y, 'g-', linewidth=2, label='滤波后')
    plt.title('滤波后]')
    plt.xlabel('时间]second]')
    plt.ylabel('振幅')
    plt.grid()  #画线

    plt.subplots_adjust(hspace=0.6)
    # 保存图像到文件
    image_filename = "./tmp_img/cyhighpass_" + str(cutoff) + "_" + str(N) + "_" + str(rp) + ".png"
    plt.savefig(image_filename)
    plt.close()  # 关闭图形以避免内存泄漏

    # 返回图像文件名（如果需要进一步处理）
    return image_filename

if __name__ == "__main__":
    cutoff = float(sys.argv[1])  # 截止频率
    N = int(sys.argv[2])  # 滤波器阶数
    rp = float(sys.argv[3])  # 通带最大波纹（dB）
    print(f"cutoff = {cutoff}, N = {N}, rp = {rp}")
    cheby1_highpass_filter(data,N,rp,fs, cutoff)