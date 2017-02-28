import {HttpClient, json} from 'aurelia-fetch-client'

export class people{

	constructor() {
		this.appName = "adPortal"
		this.count = 0
	}

	addUser() {
		let client = new HttpClient();

		let userData = {
			"firstName": "MyName",
			"lastName": "MyLAstName",
			"numOfPets": 11
		}

		client.fetch('http://localhost:8080/users/add', {
			'method': "POST",
			'body': json(userData)
		})
			.then(response => response.json())
			.then(data => {
				console.log("Server saatis " + data.firstName);
		});

		console.log("Method executed!")
	}
}