const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id')
const userId = urlParams.get('uId');

const results = new XMLHttpRequest();
results.open("GET", `/api/v1/admin/results/${userId}/${id}`);
results.send();

results.onload = function() {
    if (results.status === 200) {
      let i = 1;
      let info = document.getElementById('info');
      let newLine = document.createElement('p');
      let table = document.getElementById('results');
      data = JSON.parse(results.responseText);
      newLine.innerHTML = `Общий балл: ${data.rating}`;
      info.appendChild(newLine);
      for(let i = 0; i < data.userAnswers.length; i++) {
        let newTr = document.createElement('tr');
        newTr.innerHTML = 
          `<td scope="col">${i + 1}</td>
          <td scope="col">${data.userAnswers[i]}</td>
          <td scope="col">${data.ratings[i]}</td>
          <td scope="col">${(data.correctAnswers === null) ? '' : data.correctAnswers[i]}</td>`
        table.appendChild(newTr);
      }
    }
}