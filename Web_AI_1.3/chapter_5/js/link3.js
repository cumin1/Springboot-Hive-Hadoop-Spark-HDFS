document.addEventListener('DOMContentLoaded', function() {
    let items = [
        { id: 1, name: 'Item 1' },
        { id: 2, name: 'Item 2' },
    ];

    const tbody = document.getElementById('data-tbody');
    const form = document.getElementById('data-form');
    const itemIdField = document.getElementById('item-id');
    const itemNameField = document.getElementById('item-name');
    const formTitle = document.getElementById('form-title');
    const cancelBtn = document.getElementById('cancel-btn');

    function renderTable() {
        tbody.innerHTML = '';
        items.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>
                    <button class="btn edit" onclick="editItem(${item.id})">Edit</button>
                    <button class="btn delete" onclick="deleteItem(${item.id})">Delete</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    }

    window.editItem = function(id) {
        const item = items.find(i => i.id === id);
        itemIdField.value = item.id;
        itemNameField.value = item.name;
        formTitle.textContent = 'Edit Data';
    };

    window.deleteItem = function(id) {
        items = items.filter(item => item.id !== id);
        renderTable();
    };

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const id = itemIdField.value;
        const name = itemNameField.value.trim();

        if (name) {
            if (id) {
                const item = items.find(i => i.id == id);
                item.name = name;
            } else {
                const newId = items.length ? Math.max(...items.map(i => i.id)) + 1 : 1;
                items.push({ id: newId, name });
            }
            renderTable();
            form.reset();
            formTitle.textContent = 'Add New Data';
        }
    });

    cancelBtn.addEventListener('click', function() {
        form.reset();
        formTitle.textContent = 'Add New Data';
    });

    renderTable();
});
