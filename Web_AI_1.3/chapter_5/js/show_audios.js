const audioList = document.getElementById('file-list');
const files = []; // 用于存储获取到的文件名

async function fetchAudioFiles() {
    try {
        const response = await fetch('http://localhost:8080/hdfs/file/findAudio');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        files.push(...data.data);
        renderAudioList(files); // 渲染音频列表
    } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
    }
}

function renderAudioList(audios) {
    audioList.innerHTML = ''; // 清空现有列表

    audios.forEach(filename => {
        const li = document.createElement('li');
        li.className = 'audio-item';

        const h3 = document.createElement('h3');
        h3.textContent = filename;

        const audio = document.createElement('audio');
        audio.controls = true;
        audio.src = `http://localhost:8080/hdfs/file/getFile/${filename}`;

        // const downloadBtn = document.createElement('a');
        // downloadBtn.href = audio.src;
        // downloadBtn.download = filename;
        // downloadBtn.textContent = '下载';
        // downloadBtn.className = 'download-btn';

        li.appendChild(audio);
        li.appendChild(h3);
        // li.appendChild(downloadBtn);
        audioList.appendChild(li);
    });
}

// 获取并渲染音频文件列表
fetchAudioFiles();

function goBack() {
    window.history.back();
}

function searchFiles(query) {
    const filteredFiles = files.filter(file => file.toLowerCase().includes(query.toLowerCase()));
    renderAudioList(filteredFiles);
}

// 监听搜索框的输入事件
document.getElementById('search-input').addEventListener('input', (event) => {
    searchFiles(event.target.value);
});