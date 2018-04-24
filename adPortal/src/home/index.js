import {AuthService} from 'aurelia-auth';
import {inject} from 'aurelia-framework';

@inject(AuthService)
export class Home {

    constructor(auth){
        this.auth = auth;
        this.message = "Just checking..."
    };

    authenticate(provider){
    	let url = "http://localhost:9000/login";
    	console.log(url)
    	console.log(provider)
        return this.auth.authenticate(provider, url)
        .then((response)=>{
            console.log("auth response " + response);
        });
    }
}