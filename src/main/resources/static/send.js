document.getElementById("send").addEventListener("click", function() {
    const name = document.getElementById("name").value;
    const age = document.getElementById("age").value;
    const description = document.getElementById("description").value;

    const applicationData = {
        name: name,
        age: parseInt(age), // Приводим к числу
        description: description
    };

    fetch("http://localhost/application", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(applicationData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(data => {
        document.getElementById("res").innerText = "Заявка успешно отправлена!";
    })
    .catch(error => {
        document.getElementById("res").innerText = "Ошибка: " + error.message;
    });
});