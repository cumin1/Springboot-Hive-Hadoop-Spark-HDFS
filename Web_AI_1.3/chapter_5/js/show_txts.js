const files = []; // 用于存储获取到的文件名

async function fetchTxtFiles() {
    try {
        const response = await fetch('http://localhost:8080/hdfs/file/findTxt');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        files.push(...data.data);
        renderFileList(files); // 渲染文件列表
        console.log(files)
    } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
    }
}

function renderFileList(files) {
    const fileList = document.getElementById('file-list');
    fileList.innerHTML = '';

    files.forEach(file => {
        const li = document.createElement('li');
        li.className = 'file-item';
        li.textContent = file;
        fileList.appendChild(li);
    });
}

// 获取并渲染文件列表
fetchTxtFiles();

function goBack() {
    window.history.back();
}

function searchFiles(query) {
    const filteredFiles = files.filter(file => file.toLowerCase().includes(query.toLowerCase()));
    renderFileList(filteredFiles);
}

// 监听搜索框的输入事件
document.getElementById('search-input').addEventListener('input', (event) => {
    searchFiles(event.target.value);
});