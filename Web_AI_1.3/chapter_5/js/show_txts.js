const fileList = document.getElementById('file-list');

        async function fetchTxtFiles() {
            try {
                const response = await fetch('http://localhost:8080/hdfs/file/findTxt');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                renderFileList(data.data); // 调用渲染文件列表函数
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