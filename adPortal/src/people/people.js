import {HttpClient, json} from 'aurelia-fetch-client'
import environment from '../environment'

export class people{

	userData = {}
	userList = []

	constructor() {
		this.appName = "adPortal"
		this.count = 0	
	}

	activate() {
		let client = new HttpClient();

		client.fetch(environment.apiUrl + 'users')
			.then(response => response.json())
			.then(users => this.userList = users);
	}

	addUser() {
		let client = new HttpClient();

		client.fetch(environment.apiUrl + 'users/add', {
			'method': "POST",
			'body': json(this.userData)
		})
			.then(response => response.json())
			.then(data => {
				console.log("Server saatis " + data.firstName);
				this.userList.push(data)
		});

		console.log("Method executed!")
	}
}