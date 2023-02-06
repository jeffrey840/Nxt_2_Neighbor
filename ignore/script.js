// endpoints

// returns the address/ img of the recently sold houses
const R_k = "4b79262d6emsh7cc3f4048fc7718p12b945jsndb86ef3ab993"

const options = {
	method: 'GET',
	headers: {
		'X-RapidAPI-Key': `${R_k}`,
		'X-RapidAPI-Host': 'zillow-com1.p.rapidapi.com'
	}
};

// Returns 3 houses that were recently sold
// Each house has the address, img
// These houses represent the neighbors
// fetch('https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location=houston%2C%20Tx&page=1&status_type=RecentlySold&home_type=Houses&sort=Homes_for_You', options)
// 	.then(response => response.json())
// 	.then(response => {
// 		// console.log(response);
// 		for(var i =0;i < 3;i++) {
// 			console.log("neighbors houses " + response.props[i].address)
// 			console.log( "neighbors images " + response.props[i]["imgSrc"])
// 		}
// 	})
// .catch(err => console.error(err));

// Returns 3 houses that are on sale
// Each house has the address, price, and returns a zpid, the zpid will be used to make another fetch
// fetch('https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location=houston%20tx&page=1&status_type=ForSale&home_type=Houses&sort=Homes_for_You', options)
// 	.then(response => response.json())
// 	.then(response => {
// 		console.log("===============================");
// 		for(var i =0;i < 3;i++) {
// 			console.log("address " + response.props[i].address)
// 			console.log("price " +response.props[i]["price"])
// 			console.log("this zpid will then make another fetch req " + response.props[i]["zpid"])
// 			var zp_id = response.props[i]["zpid"]
// 			console.log(zp_id);
// 		}
// 	}
// 	)
// 	.catch(err => console.error(err));
//
// let zp_id = "28485710"
// fetch('https://zillow-com1.p.rapidapi.com/images?zpid=' + `${zp_id}`, options)
// 	.then(response => response.json())
// 	.then(response => console.log(response))
// 	.catch(err => console.error(err));

// the user will only see the houses on sale
// they get recommended a house based on the house being sold, the neighbors will be tied to sold listings




myFunction();

async function myFunction() {
	let User_address = "houston"
	// let User_address = document.getElementById("Uaddress").textContent
	const response = await fetch('https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location='+`${User_address}`+'%20tx&page=1&status_type=ForSale&home_type=Houses&sort=Homes_for_You', options)
	const data = await response.json();
	const {props} = data

	for(let i =0;i < 1;i++) {

		addresses = props[i].address
		zpid = props[i]["zpid"]
		image = props[i]["imgSrc"]
		console.log(addresses,zpid,image)

		// this will print out the zpid along with the
		// const response2 = await fetch('https://zillow-com1.p.rapidapi.com/images?zpid=' + `${zpid}`, options)
		// const data2 = await response2.json();
		// const {images} = data2
		//
		// for(var j =0;j < 5;j++) {
		// 	zimg = images[j]
		// 	console.log(zimg);
		// }

	}

}

