const info = new XMLHttpRequest();
info.open("GET", "/api/v1/info");
info.send();

let username;

const users = new XMLHttpRequest();

info.onload = function() {
    if (info.status === 200) {
      data = JSON.parse(info.responseText);
      username = data.username;
      let newLine = document.createElement('div');
      newLine.innerHTML = `
      <table class="table table-striped">
        <thead class="thead">
            <tr>
                <th scope="col">№</th>
                <th scope="col">Имя</th>
                <th scope="col">Рейтинг</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td scope="col" id="userNum">№</td>
                <td scope="col">${data.username}</td>
                <td scope="col">${data.rating}</td>
            </tr>
            </tbody>
      </table>`;
      document.getElementById('info').appendChild(newLine);
    } else if (info.status === 403) {
        let newLine = document.createElement('h3');
        newLine.innerHTML = 'Пользователь не активирован';
        document.getElementById('info').appendChild(newLine);
    }
    users.open("GET", "/api/v1/users");
    users.send();
}

users.onload = function() {
    if (users.status === 200) {
      let i = 1;
      let table = document.getElementById('rating');
      data = JSON.parse(users.responseText);
      data.forEach(element => {
        if(element.username === username) {
            document.getElementById('userNum').innerHTML = i;
        }
        let newTr = document.createElement('tr');
        newTr.innerHTML = 
          `<td scope="col">${i}</td>
          <td scope="col">${element.username}</td>
          <td scope="col">${element.rating}</td>`
        i++;
        table.appendChild(newTr);
      });
    }
}