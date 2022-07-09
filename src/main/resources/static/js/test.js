const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id')

const test = new XMLHttpRequest();
test.open("GET", `/api/v1/test/${id}`);
test.send();

let questions = [];

test.onload = function() {
    if (test.status === 200) {
      data = JSON.parse(test.responseText);

      let main = document.getElementById('test');
      let name = document.createElement('h2');
      name.innerHTML = data.name;
      main.appendChild(name);

      data.questions.forEach(element => {
        let newQ = document.createElement('form');
        newQ.setAttribute("id", element.id);
        newQ.innerHTML = `<p>${element.text}</p>`;
        questions.push(element.id);
        let ans = 1;
        if(element.multipleChoice) {
            element.options.forEach(option => {
                newQ.innerHTML += `<input type="checkbox" name="${element.id}" value="${ans}">&nbsp${option}<br>`;
                ans++;
            })
        } else {
            element.options.forEach(option => {
                newQ.innerHTML += `<input type="radio" name="${element.id}" value="${ans}">&nbsp${option}<br>`;
                ans++;
            });
        }
        newQ.innerHTML += '</br></br>'
        main.appendChild(newQ);
      });
    main.innerHTML += '<button type="button" onclick="getInputValue();">Отправить</button>';
    } else {
        let res = document.getElementById('test');
        let newP = document.createElement('p');
        newP.innerHTML =  `Тест недоступен. Возможно ваши попытки закончились: <a href="/results?id=${id}">Результат последней попытки</a>`;
        res.appendChild(newP);
    }
}

function getInputValue(){
    let ans = {testId : id, answers : []};
    for(let i = 0; i < questions.length; i++) {
        let a = {questionId : questions[i], answer : ""}
        const inputs = document.getElementById(questions[i]).getElementsByTagName('input');
        let single = true;
        for(let j = 0; j < inputs.length; j++) {
            if(inputs[j].checked) {
                if(!single) {
                    a.answer += `/${inputs[j].value}`;
                    
                } else {
                    a.answer += inputs[j].value;
                }
                single = false;
            }
        }
        ans.answers.push(a);
    }
    //отправка json
    let xhr = new XMLHttpRequest();
    let json = JSON.stringify(ans);
    xhr.open("POST", `/api/v1/test/${id}`);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    window.location.replace(`/results?id=${id}`);
}