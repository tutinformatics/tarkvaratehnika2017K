import {AuthService} from 'aurelia-auth';
import {inject} from 'aurelia-framework';

@inject(AuthService)
export class Home {

    constructor(auth){
        this.auth = auth;
        this.message = "Just checking..."
    };

    authenticate(provider){
        return this.auth.authenticate(provider)
        .then((response)=>{
            console.log("auth response " + response);
        });
    }
}