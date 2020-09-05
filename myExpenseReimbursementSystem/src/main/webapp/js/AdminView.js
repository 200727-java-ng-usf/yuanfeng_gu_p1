document.addEventListener("DOMContentLoaded", function (e) {
  createOnStartUp();
})

let url = "../Admin";

const createOnStartUp = () => {
	sendAjaxGet(url, display);
}

const sendAjaxGet = (url, func) => {
	//ActiveXObject for IE9 and older compatibility
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

console.log(requestArr);

let table = document.getElementById("empTable");
	table.removeChild(document.getElementById("empList"));
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "empList");
	table.appendChild(newBody);


for(let i=0;i<requestArr.length;i++){

		let newRow = document.createElement("tr");

		newRow.innerHTML = "<td>" + requestArr[i].id + "</td>" +
		 "<td>" + requestArr[i].username + "</td>" +
		  "<td>" + requestArr[i].firstName + "</td>" +
		   "<td>" + requestArr[i].lastName + "</td>" +
		    "<td>" + requestArr[i].email + "</td>" ;


		    	newBody.appendChild(newRow);

}


}