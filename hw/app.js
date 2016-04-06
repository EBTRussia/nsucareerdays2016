
console.log('device started.');

var pin = 5;
var resource = 'light01';

function initpins() {
	GPIO.setmode(pin, 0, 0);
	GPIO.write(pin, false);
}

function changedFunc(state) {
	console.log('Wifi state: ', state, Wifi.status());
	if (state == 2) {
		mainFunc();
	}
}

function mainFunc() {
	Http.request({
		hostname: 'ngurestexample.us-east-1.elasticbeanstalk.com',
		port: 80,
		path: '/',
		method: 'GET'
	}, function (response){
		console.log(response.body);
		try {
			var states = JSON.parse(response.body);
			console.log('GPIO5 state: ', !!states[resource]);
			GPIO.write(pin, !!states[resource]);
		} finally {
			setTimeout(mainFunc, 1000);
		}
	})
	.end()
	.setTimeout(5000, function(){
		console.log('timeout error');
		setTimeout(mainFunc, 1000);
	});
}

initpins();
Wifi.changed(changedFunc);
