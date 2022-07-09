document.getElementById("ob").disabled = true;
document.getElementById("sb").disabled = true;

let test = {questions : []};
let question = {options : [], correctAnswer : ''};

let firstQuestion = true;
let noEx = false;
let option = 1;

function addQuestion(){
    option = 1;
    const questions = document.getElementById("questions");
    newQ = document.createElement('h4');
    let line = document.getElementById('question').value;
    if(line !== '') {
        if(!firstQuestion) {
            test.questions.push(question);
        }
        question = {options : [], correctAnswer : ''};
        question.text = line;
        newQ.innerHTML = line;
        questions.appendChild(newQ);
        document.getElementById('question').value = '';
        document.getElementById("qb").disabled = true;
        document.getElementById("sb").disabled = true;
        document.getElementById("ob").disabled = false;
        firstQuestion = false;
    }
}

function addOption() {
    const questions = document.getElementById("questions");
    newO = document.createElement('p');
    let line = document.getElementById('option').value;
    const correct = document.getElementById("correct").checked;
    if(line !== '') {
        newO.innerHTML = line;
        document.getElementById('option').value = '';
        question.options.push(line);
        if(correct) {
            question.multipleChoice = question.correctAnswer !== '';
            question.correctAnswer += (question.correctAnswer === '') ? option : `/${option}`;
            document.getElementById("qb").disabled = false;
            newO.innerHTML += ' (+)';
            document.getElementById("sb").disabled = false;
        }
        option++;
        questions.appendChild(newO);
    }
}

function saveTest() {
    let name = document.getElementById("name").value;
    let maxAttempt = document.getElementById("attempts").value;
    test.questions.push(question);

    if(name !== '' && !isNaN(parseInt(maxAttempt)) && parseInt(maxAttempt) > 0) {
        test.name = name;
        test.maxAttempt = parseInt(maxAttempt);
         //отправка json
        let xhr = new XMLHttpRequest();
        let json = JSON.stringify(test);
        xhr.open("POST", `/api/v1/admin/save`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);
        window.location.replace('/tests');
    } else if(noEx) {
        const b = document.getElementById("b");
        const err = document.createElement('p');
        err.innerHTML = 'Не забудьте название и максимальное число попыток';
        b.appendChild(err); 
        noEx = false;
    }
}