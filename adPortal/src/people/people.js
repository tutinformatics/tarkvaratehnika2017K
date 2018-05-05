import {inject} from 'aurelia-framework';
import {HttpClient, json} from 'aurelia-fetch-client'

@inject(HttpClient)
export class people{

	userData = {}
	userList = []
	searchStr = ""

	constructor(http) {
		this.appName = "adPortal"
		this.count = 0
		this.http = http
	}

	activate() {
		this.http.fetch('http://localhost:8080/users')
			.then(response => response.json())
			.then(users => this.userList = users);
	}

	addUser() {

		this.http.fetch('http://localhost:8080/users/add', {
			'method': "POST",
			'body': json(this.userData)
		})
			.then(response => response.json())
			.then(data => {
				console.log("Server saatis " + data.firstName);
				this.userList.push(data);
			});

		console.log("Method executed!")
	}

	searchUser() {

		this.http.fetch('http://localhost:8080/users/search/' + this.searchStr)
			.then(response => response.json())
			.then(users => this.userList = users);
	}
}