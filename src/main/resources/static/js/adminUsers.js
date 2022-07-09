const users = new XMLHttpRequest();
users.open("GET", "/api/v1/admin/users");
users.send();

users.onload = function() {
    if (users.status === 200) {
      let i = 1;
      let table = document.getElementById('rating');
      data = JSON.parse(users.responseText);
      data.forEach(element => {
        let newTr = document.createElement('tr');
        newTr.innerHTML = 
          `<td scope="col">${i}</td>
          <td scope="col">${element.username}</td>
          <td scope="col">${element.rating}</td>
          <td scope="col">${element.email}</td>
          <td scope="col">${
            links(element)
          }</td>`
        i++;
        table.appendChild(newTr);
      });
    }
}

function links(element) {
  let res = '';
  element.answeredTestDtos.forEach(ans => {
    res += `<a href="/admin/results?uId=${element.id}&id=${ans.testId}">${ans.testId}</a>&nbsp`
  })
  return res;
}