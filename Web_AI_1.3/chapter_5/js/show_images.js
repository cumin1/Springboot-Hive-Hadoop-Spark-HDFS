const images = [];

// 获取图片列表
async function fetchImages() {
    try {
        const response = await fetch('http://localhost:8080/hdfs/file/findImage');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        images.length = 0;
        images.push(...data.data);

        // 调用 renderImages 来渲染图片
        renderImages(images);
    } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
    }
}

// 渲染图片到页面
function renderImages(images) {
    const imageGallery = document.querySelector('.image-gallery');
    imageGallery.innerHTML = ''; // 清空现有的图片

    images.forEach(filename => {
        const imageItem = document.createElement('div');
        imageItem.className = 'image-item';

        const img = document.createElement('img');
        img.src = `http://localhost:8080/hdfs/file/getFile/${filename}`;
        img.alt = filename;

        const imageName = document.createElement('div');
        imageName.className = 'image-name';
        imageName.textContent = filename;

        // const downloadBtn = document.createElement('a');
        // downloadBtn.href = img.src;
        // downloadBtn.download = filename;
        // downloadBtn.textContent = 'Download';
        // downloadBtn.className = 'download-btn';

        imageItem.appendChild(img);
        imageItem.appendChild(imageName);
        // imageItem.appendChild(downloadBtn);
        imageGallery.appendChild(imageItem);

    });
}

// 获取并渲染图片
fetchImages();

function goBack() {
    window.history.back();
}