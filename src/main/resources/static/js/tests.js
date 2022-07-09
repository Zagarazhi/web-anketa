const tests = new XMLHttpRequest();
tests.open("GET", "/api/v1/tests");
tests.send();

tests.onload = function() {
    if (tests.status === 200) {
      let i = 1;
      let table = document.getElementById('tests');
      data = JSON.parse(tests.responseText);
      data.forEach(element => {
        let newTr = document.createElement('tr');
        newTr.innerHTML = 
          `<td scope="col">${i}</td>
          <td scope="col"><a href="/test?id=${element.id}">${element.name}</a></td>`
        i++;
        table.appendChild(newTr);
      });
    }
}