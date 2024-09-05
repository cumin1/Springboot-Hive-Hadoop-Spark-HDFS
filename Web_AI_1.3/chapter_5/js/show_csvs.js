const fileList = document.getElementById('file-list');
files = []

async function fetchTxtFiles() {
    try {
        const response = await fetch('http://localhost:8080/hdfs/file/findCsv');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        files.length = 0;
        files.push(...data.data);
        renderFileList(files); // 调用渲染文件列表函数
    } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
    }
}

function renderFileList(files) {
    fileList.innerHTML = ''; // 清空现有列表

    files.forEach(filename => {
        const li = document.createElement('li');

        const fileNameDiv = document.createElement('div');
        fileNameDiv.className = 'file-name';
        fileNameDiv.textContent = filename;

        li.appendChild(fileNameDiv);
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