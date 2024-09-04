const csvs = [];
const fileList = document.getElementById('file-list');

async function fetchImages() {
    try {
        const response = await fetch('http://localhost:8080/hdfs/file/findCsv');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json(); // 服务器返回的是JSON格式的数据
        csvs.length = 0;
        csvs.push(...data.data); // 将新数据追加到images数组
        renderFileList(csvs); // 更新文件列表
    } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
    }
}

function renderFileList(files) {
    fileList.innerHTML = ''; // 清空现有列表

    files.forEach((file) => {
        const li = document.createElement('li');
        li.className = 'list-item';
        li.textContent = file;
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.className = 'delete-btn';
        deleteButton.onclick = () => handleDelete(file);

        li.appendChild(deleteButton);
        fileList.appendChild(li);
    });
}

// 在页面加载时获取并渲染文件列表
window.onload = fetchImages;

// 上传文件的函数
function uploadFile(event, uploadpoint) {
    const file = event.target.files[0];
    console.log(`Uploading ${file.name} to ${uploadpoint}`);

    const formData = new FormData();
    formData.append('file', file);

    // 发送 POST 请求
    fetch('http://localhost:8080' + uploadpoint, {
        method: 'POST',
        body: formData,
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // 假设服务器返回 JSON 格式的数据
        })
        .then(data => {
            console.log('Upload successful:', data);
            alert(data.message);
            fetchImages(); // 上传成功后，更新文件列表
        })
        .catch(error => {
            console.error('There was a problem with your upload:', error);

        });

    // 清空文件输入框的值
    event.target.value = '';
}

function downloadFile(fileName) {
    alert('Downloading ' + fileName);
    // Implement actual download functionality
}

// 删除文件的函数
async function handleDelete(csv) {
    console.log(`点击了删除按钮: ${csv}`);
    // 弹出确认对话框
    const isConfirmed = confirm(`Are you sure you want to delete ${image}?`);

    if (!isConfirmed) {
        // 用户点击取消，不执行删除操作
        return;
    }
    try {
        const response = await fetch(`http://localhost:8080/hdfs/file/deleteCsv/${csv}`, {
            method: 'GET' // 使用 DELETE 请求来删除文件
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        console.log('Delete successful:', data);
        alert(data.message);
        fetchImages();
    } catch (error) {
        console.error('There was a problem with your delete operation:', error);
    }
}

function goBack() {
    window.history.back();
}