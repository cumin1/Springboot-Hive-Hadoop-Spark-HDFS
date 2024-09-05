const videoList = document.getElementById('video-list');
files = []

async function fetchVideos() {
    try {
        const response = await fetch('http://localhost:8080/hdfs/file/findVideo');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        files.length = 0;
        files.push(...data.data);
        renderVideoList(data.data); // 调用渲染视频列表函数
    } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
    }
}

function createThumbnail(videoSrc, callback) {
    const video = document.createElement('video');
    video.src = videoSrc;
    video.crossOrigin = 'anonymous'; // 解决跨域问题
    video.addEventListener('loadeddata', () => {
        if (video.readyState >= 2) { // 确保视频数据已加载
            const canvas = document.createElement('canvas');
            canvas.width = 320; // 设定缩略图的宽度
            canvas.height = 180; // 设定缩略图的高度
            const ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
            const img = canvas.toDataURL('image/jpeg');
            callback(img);
        }
    });
}

function renderVideoList(videos) {
    videoList.innerHTML = ''; // 清空现有列表

    videos.forEach(filename => {
        const li = document.createElement('li');
        li.className = 'video-item';

        const videoSrc = `http://localhost:8080/hdfs/file/getFile/${filename}`;
        createThumbnail(videoSrc, (thumbnailSrc) => {
            const img = document.createElement('img');
            img.src = thumbnailSrc;
            img.alt = filename;

            const h3 = document.createElement('h3');
            h3.textContent = filename;

            // const downloadBtn = document.createElement('a');
            // downloadBtn.href = videoSrc;
            // downloadBtn.download = filename;
            // downloadBtn.textContent = '下载';
            // downloadBtn.className = 'download-btn';

            li.appendChild(img);
            li.appendChild(h3);
            // li.appendChild(downloadBtn);
            videoList.appendChild(li);
        });
    });
}

// 获取并渲染视频列表
fetchVideos();


function goBack() {
    window.history.back();
}

function searchFiles(query) {
    const filteredFiles = files.filter(file => file.toLowerCase().includes(query.toLowerCase()));
    renderVideoList(filteredFiles);
}

// 监听搜索框的输入事件
document.getElementById('search-input').addEventListener('input', (event) => {
    searchFiles(event.target.value);
});