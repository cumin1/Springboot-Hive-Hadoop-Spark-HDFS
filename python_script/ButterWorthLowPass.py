# 低通滤波器
import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import butter, filtfilt, freqz
import io
import sys

def generate_plot(cutoff, order):
    fs = 300.0
    T = 2.0
    n = int(T * fs)
    t = np.linspace(0, T, n, endpoint=False)
    data = sum([(1 - (i - 1) * 0.1) * np.sin(i * 2 * np.pi * t) for i in [1, 3, 5, 7, 9]])

    b, a = butter(N=order, Wn=cutoff / (0.5 * fs), btype='lowpass')
    y = filtfilt(b, a, data)
    w, h = freqz(b, a, worN=800)

    plt.figure(figsize=(10, 4))

    plt.subplot(1, 3, 1)
    plt.plot(0.5 * fs * w / np.pi, np.abs(h), 'b')
    plt.plot(cutoff, 0.5 * np.sqrt(2), 'ko')
    plt.axvline(cutoff, color='r')
    plt.xlim(0, 10)
    plt.title("Frequency Response")
    plt.xlabel('Frequency (Hz)')
    plt.grid()

    plt.subplot(1, 3, 2)
    plt.plot(t, data, 'b-')
    plt.title('Before Filter')
    plt.xlabel('Time (s)')
    plt.grid()

    plt.subplot(1, 3, 3)
    plt.plot(t, y, 'g-', linewidth=2)
    plt.title('After Filter')
    plt.xlabel('Time (s)')
    plt.grid()

    plt.tight_layout()
    plt.savefig(f'./tmp_img/lowpass_{cutoff}_{order}.png')

if __name__ == "__main__":
    cutoff = float(sys.argv[1])
    order = int(sys.argv[2])
    generate_plot(cutoff, order)
