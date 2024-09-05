const audioList = document.getElementById('file-list');
files = []

async function fetchAudioFiles() {
    try {
        const response = await fetch('http://localhost:8080/hdfs/file/findAudio');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        files.length = 0;
        files.push(...data.data)
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

        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = '删除';
        deleteBtn.className = 'delete-btn';
        deleteBtn.onclick = () => deleteAudio(filename);

        li.appendChild(audio);
        li.appendChild(h3);
        li.appendChild(deleteBtn);
        audioList.appendChild(li);
    });
}

async function uploadAudio() {
    const fileInput = document.getElementById('file-input');
    const file = fileInput.files[0];

    if (!file) {
        alert('请选择一个音频文件');
        return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
        const response = await fetch('http://localhost:8080/hdfs/file/uploadAudio', {
            method: 'POST',
            body: formData,
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        alert(result.message);
        fetchAudioFiles(); // 更新音频列表

    } catch (error) {
        console.error('There was a problem with your upload:', error);
        alert('上传失败!');
    }

    fileInput.value = ''; // 清空文件输入框
}

async function deleteAudio(filename) {
    const confirmDelete = confirm(`确定要删除 ${filename} 吗？`);

    if (!confirmDelete) {
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/hdfs/file/deleteAudio/${filename}`, {
            method: 'GET',
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        alert('删除成功!');
        fetchAudioFiles(); // 更新音频列表

    } catch (error) {
        console.error('There was a problem with your delete operation:', error);
        alert('删除失败!');
    }
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