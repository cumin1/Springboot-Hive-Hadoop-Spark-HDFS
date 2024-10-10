import cv2
import numpy as np
import matplotlib.pyplot as plt
import sys
import os
from hdfs import InsecureClient
from io import BytesIO
from PIL import Image

plt.rcParams['font.sans-serif'] = ['SimHei']

def butterworthPassFilter(image, d, n):
    f = np.fft.fft2(image)
    fshift = np.fft.fftshift(f)

    def make_transform_matrix(d):
        transfor_matrix = np.zeros(image.shape)
        center_point = tuple(map(lambda x: (x - 1) / 2, image.shape))
        for i in range(transfor_matrix.shape[0]):
            for j in range(transfor_matrix.shape[1]):
                def cal_distance(pa, pb):
                    from math import sqrt
                    dis = sqrt((pa[0] - pb[0]) ** 2 + (pa[1] - pb[1]) ** 2)
                    return dis

                dis = cal_distance(center_point, (i, j))
                # 使用巴特沃斯滤波器的公式
                transfor_matrix[i, j] = 1 / ((1 + (d / dis)) ** n)
        return transfor_matrix

    d_matrix = make_transform_matrix(d)
    new_img = np.abs(np.fft.ifft2(np.fft.ifftshift(fshift * d_matrix)))
    return new_img

def show_image(d,n):
    client = InsecureClient('http://192.168.114.128:9870', user='root')
    with client.read('/stiei/image/zhoufanxuan.jpg') as file:
        image_data = file.read()

    image_array = np.frombuffer(image_data, np.uint8)
    img = cv2.imdecode(image_array, cv2.IMREAD_GRAYSCALE)

    # img = cv2.imread('./python_script/zhoufanxuan.jpg', 0)
    if img is None:
        print("无法读取图像，请检查文件路径和文件完整性。")
        sys.exit(1)
    plt.figure(figsize=(10,10))
    plt.subplot(121)
    plt.imshow(img,cmap="gray")
    plt.title("原图像")
    plt.axis("off")

    plt.subplot(122)
    butter_image = butterworthPassFilter(img, d, n)
    plt.imshow(butter_image,cmap="gray")
    plt.title('d={},n={}'.format(d,n))
    #plt.title('d=%d,n=%d'%(d,n))
    plt.axis("off")
    plt.subplots_adjust(hspace=0.6)
    # 保存图像到文件
    image_filename = "./tmp_img/pichandle_" + str(d) + "_" + str(n) + ".png"
    plt.savefig(image_filename)
    plt.close()  # 关闭图形以避免内存泄漏

    # 返回图像文件名（如果需要进一步处理）
    return image_filename

if __name__ == "__main__":
    cutoff = int(sys.argv[1])  # 截止频率
    order = int(sys.argv[2])  # 滤波器阶数
    print(f"cutoff = {cutoff}, order = {order}")
    show_image(cutoff,order)