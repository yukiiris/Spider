/**
 * 
 */

 
var page = require('webpage').create(),
system = require('system'),
t, address;

if (system.args.length === 1) {
console.log('Usage: loadspeed.js <some URL>');
phantom.exit();
}

t = Date.now();
address = system.args[1];
page.open(address, function (status) {
if (status !== 'success') {
    console.log('FAIL to load the address');
} else {
	var el = document.querySelectorAll('.add')[0];
	function click(el){
	    var ev = document.createEvent("MouseEvent");
	    ev.initMouseEvent(
	        "click",
	        true /* bubble */, true /* cancelable */,
	        window, null,
	        0, 0, 0, 0, /* coordinates */
	        false, false, false, false, /* modifier keys */
	        0 /*left*/, null
	    );
	    //el.dispatchEvent(ev);
	console.log(page.content);
	}
    phantom.exit();
}
});