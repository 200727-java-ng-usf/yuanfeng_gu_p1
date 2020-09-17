document.addEventListener("DOMContentLoaded", function (e) {
  createOnStartUp();
})

let url = "../ManagerPending";

const createOnStartUp = () => {
	sendAjaxGet(url, display);
}

const sendAjaxGet = (url, func) => {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState==4 && this.status==200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}


const display = (xhr) => {


requestArr = JSON.parse(xhr.responseText);

tableRows = xhr.responseText;
let table = document.getElementById("reqTable");
table.removeChild(document.getElementById("reqTableBody"));
let newBody = document.createElement("tbody");
newBody.setAttribute("id", "reqTableBody");
table.appendChild(newBody);

console.log(requestArr);


for(let i=0;i<requestArr.length;i++){

        let status;
         if(requestArr[i].statusId == 1){ status = "Approved"}else if(requestArr[i].statusId == 0){
         status = "Denied"}else{  status = "Pending"}

		let newRow = document.createElement("tr");

		newRow.innerHTML =
		"<td>" + requestArr[i].reimbId + "</td>" +
		 "<td>" + new Date(parseInt(requestArr[i].dateSubmitted)).toLocaleDateString()+ "</td>" +
		  "<td>" + new Date(parseInt(requestArr[i].resolvedDate)).toLocaleDateString()+ "</td>" +
		  "<td>"+ " $ " + requestArr[i].amount + "</td>" +
		   "<td>" + requestArr[i].description + "</td>" +
		   "<td>" + status + "</td>" +
		    "<td>" + requestArr[i].resolverId + "</td>";


		    	newBody.appendChild(newRow);

}

}


function formatDate(date) {
    dates = date.split("/");
    if(dates.length == 3) {
        if(dates[1].length == 1) {
            dates[1] = "0" + dates[1];
        }
        if (dates[2].length == 1) {
            dates[2] = "0" + dates[2];
        }
        date = dates.join("-");
        return date;
    } else {
        return null;
    }
}