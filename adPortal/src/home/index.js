import {AuthService} from 'aurelia-auth';
import {inject} from 'aurelia-framework';

@inject(AuthService)
export class Home {

    constructor(auth){
        this.auth = auth;
        this.message = "Just checking... "
        if (auth.isAuthenticated()) {
        	auth.getMe().then(
        		user => this.message += user['email'] + " on sisse logitud!"
        		)
        	
        } else {
        	this.message += "logimata!"
        }
    };

    authenticate(provider){
        return this.auth.authenticate(provider)
        .then((response)=>{
            console.log("auth response " + response);
        });
    }
}