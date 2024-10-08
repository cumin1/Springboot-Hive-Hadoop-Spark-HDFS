import sys  
import numpy as np  
import matplotlib.pyplot as plt  
import matplotlib.patches as patches  
from scipy.signal import cheby1, filtfilt, freqz, tf2zpk  

plt.rcParams['font.sans-serif'] = ['SimHei']

# 生成信号（这部分代码与之前相同）  
fs = 300.0  # 采样频率  
T = 2.0  # 周期2s  
n = int(T * fs)  # 总样本数  
t = np.linspace(0, T, n, endpoint=False)  
data = np.random.rand(n)  # 高斯噪声  
for i in [1, 3, 5, 7, 9]:  # 基频加四个谐波组成的信号  
    data += (1 - (i - 1) * 0.1) * np.sin(i * 2 * np.pi * t)  
  
# 切比雪夫低通滤波器函数（修改后的版本，不返回图像流）  
def cheby1_lowpass_filter(data, order, rp, fs, cutoff):  
    normal_f = cutoff / (fs / 2)  # 归一化截止频率  
    b, a = cheby1(order, rp, Wn=normal_f, btype='lowpass', analog=False)  
    y = filtfilt(b, a, data)  
    w, h = freqz(b, a, worN=1024)  
    z, p, k = tf2zpk(b, a)  
      
    # 绘图  
    plt.figure(figsize=(10, 10))  
      
    ax = plt.subplot(221)  
    plt.title('零极点')  
    plot_plane(ax, z, p)  
      
    plt.subplot(222)  
    plt.plot(0.5 * fs * w / np.pi, 20 * np.log10(np.abs(h)), 'b')  
    plt.axvline(cutoff, color='r')  
    plt.xlim(0, fs / 2)  # 只显示到奈奎斯特频率  
    plt.title("频率响应图")  
    plt.xlabel('频率 [Hz]')  
    plt.ylabel('幅度 [dB]')  
    plt.grid(which='both', axis='both')  
      
    plt.subplot(223)  
    plt.plot(t, data, 'b-')  
    plt.title('滤波前（基频、3、5、7、9次谐波）')  
    plt.xlabel('时间 [second]')  
    plt.ylabel('振幅')  
    plt.grid()  
      
    plt.subplot(224)  
    plt.plot(t, y, 'g-', linewidth=2, label='滤波后')  
    plt.title('滤波后')  
    plt.xlabel('时间 [second]')  
    plt.ylabel('振幅')  
    plt.grid()  
      
    plt.subplots_adjust(hspace=0.6)  
      
    # 保存图像到文件  
    image_filename = "./tmp_img/cylowpass_" + str(cutoff) + "_" + str(order) + "_" + str(rp) + ".png"
    plt.savefig(image_filename)  
    plt.close()  # 关闭图形以避免内存泄漏  
      
    # 返回图像文件名（如果需要进一步处理）  
    return image_filename  
  
# 辅助函数（与之前相同）  
def plot_plane(ax, z, p):  
    unit_circle = patches.Circle((0, 0), radius=1, fill=False, color='black', ls='solid', alpha=0.8)  
    ax.add_patch(unit_circle)  
    poles = plt.plot(p.real, p.imag, 'x', markersize=9, alpha=0.8, color='red')  
    zeros = plt.plot(z.real, z.imag, 'o', markersize=9, color='none', alpha=0.8, markeredgecolor=poles[0].get_color())  
    plt.axis('scaled')  
    r = 1.1 * np.amax(np.concatenate((abs(z), abs(p), [1])))  
    plt.axis([-r, r, -r, r])  
  
# 从命令行获取参数  
if __name__ == "__main__":  
    cutoff = float(sys.argv[1])  
    order = int(sys.argv[2])  
    rp = float(sys.argv[3])  # 通带最大波纹（dB）  
    print(f"cutoff = {cutoff}, order = {order}, rp = {rp}")  
      
    # 调用滤波器函数（注意修正了参数列表中的逗号错误）  
    image_filename = cheby1_lowpass_filter(data, order, rp, fs, cutoff)  
      
    # 在这里，image_filename 是保存的图像的文件名，你可以根据需要进一步处理它  
    # 例如，你可以打印它，或者用它来做其他事情（尽管在这个例子中我们没有其他操作）  
    print(f"Image saved to {image_filename}")