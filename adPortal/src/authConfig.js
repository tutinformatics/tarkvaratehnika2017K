var configForDevelopment = {
    
    providers: {
        google: {
            clientId: '93685841879-p0qhjbe4irgsgcv2v5dujl148dujd9u4.apps.googleusercontent.com'
        }
    }
};

var configForProduction = {
    providers: {
        google: {
            clientId: '93685841879-p0qhjbe4irgsgcv2v5dujl148dujd9u4.apps.googleusercontent.com'
        }
    }
};
var config ;
if (window.location.hostname==='localhost') {
    config = configForDevelopment;
}
else{
    config = configForProduction;
}


export default config;