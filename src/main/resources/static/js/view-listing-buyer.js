// // This returns houses that have recently been sold in the houston area
// const options = {
//     method: 'GET',
//     headers: {
//         'X-RapidAPI-Key': '4b79262d6emsh7cc3f4048fc7718p12b945jsndb86ef3ab993',
//         'X-RapidAPI-Host': 'zillow-com1.p.rapidapi.com'
//     }
// };
// let imgSrc
// let address
// let propertyType
// let listing
// const button = document.getElementById('showMyListings')
// button.addEventListener('click', getListings)
// let appendHtml = document.getElementById("results")
//
// function getListings() {
//     let User_address = document.getElementById("dateUserInput").value//link the button id to ths
//
//     fetch('https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location=' + `${User_address}` + '%20tx&page=1&status_type=ForSale&home_type=Houses&sort=Homes_for_You', options)
//         .then(response => response.json())
//         .then(data => {
//
//             for (let i = 0; i < 3; i++) {
//
//                 imgSrc = data.props[i].imgSrc
//                 address = data.props[i].address
//                 propertyType = data.props[i].propertyType
//                 console.log(imgSrc);
//                 console.log(address);
//                 console.log(propertyType);
//                 // console.log(userInput)
//                 let html = `
//                         <div class="card" style="width: 18rem;">
//                         <img class="card-img-top" src="${data.props[i].imgSrc}" alt="Card image cap">
//                         <div class="card-body">
//                         <h6  class="card-title">${data.props[i].address}</h6>
//                         <p   class="card-text">${data.props[i].propertyType}</p>
//                         <p class="card-text">NEIGHBORS INTERESTS<br> 1. <br>2. <br>3</p>
//                         <a href="#" class="btn btn-primary">VIEW</a>
//                         </div>
//                         </div>`
//                 $(appendHtml).append(html)
//             }
//         })
// }
//
// getListings();