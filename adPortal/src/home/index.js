import {AuthService} from 'aurelia-auth';
import {inject} from 'aurelia-framework';

@inject(AuthService)
export class Home {

    constructor(auth){
        this.auth = auth;
        this.message = "Just checking... "
        if (auth.isAuthenticated()) {
        	this.message += "logitud!"
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