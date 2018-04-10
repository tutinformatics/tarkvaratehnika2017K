import {HttpClient, json} from 'aurelia-fetch-client'

export class people{

	userData = {}
	userList = []
	searchStr = ""

	constructor() {
		this.appName = "adPortal"
		this.count = 0
	}

	activate() {
		let client = new HttpClient();

		client.fetch('http://localhost:8080/users')
			.then(response => response.json())
			.then(users => this.userList = users);
	}

	addUser() {
		let client = new HttpClient();

		client.fetch('http://localhost:8080/users/add', {
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
		let client = new HttpClient();

		client.fetch('http://localhost:8080/users/search/' + this.searchStr)
			.then(response => response.json())
			.then(users => this.userList = users);
	}
}