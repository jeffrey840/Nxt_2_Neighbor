
// This returns houses that have recently been sold in the houston area
const options = {
    method: 'GET',
    headers: {
        'X-RapidAPI-Key': '4b79262d6emsh7cc3f4048fc7718p12b945jsndb86ef3ab993',
        'X-RapidAPI-Host': 'zillow-com1.p.rapidapi.com'
    }
};
let imgSrc
let address
let propertyType
let listing

let appendHtml = document.getElementById("results")
let appendHtmlTwo = document.getElementById("results-two")
function getListings() {
    fetch('https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location=houston%2C%20Tx&page=1&status_type=RecentlySold&home_type=Houses&sort=Homes_for_You', options)
        .then(response => response.json())
        .then(data => {
            imgSrc = data.props[0].imgSrc
            address = data.props[0].address
            propertyType = data.props[0].propertyType
            console.log(imgSrc);
            console.log(address);
            console.log(propertyType);

            let html = `
                        <div class="card mb-3" id="top-card">
                        <img class="card-img-top" src="${data.props[2].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h5 class="card-title">FEATURED HOME</h5>
                        <hr>
                        <p class="card-text">${data.props[2].address}</p>
                        <p class="card-text"><small class="text-muted">${data.props[2].propertyType}</small></p>
                        </div>
                        </div>`

            let html2 = `<div class="card mb-3" id="btm-card">
                        <img class="card-img-top" src="${data.props[9].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h5 class="card-title">Featured Home</h5>
                        <p class="card-text-address">${data.props[9].address}</p>
                        <p class="card-text-property"><small class="text-muted">${data.props[9].propertyType}</small></p>
                        </div>
                        </div>

                        <div class="card mb-3" id="btm-card">
                        <img class="card-img-top" src="${data.props[0].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h5 class="card-title">Featured Home</h5>
                        <p class="card-text">${data.props[0].address}</p>
                        <p class="card-text"><small class="text-muted">${data.props[0].propertyType}</small></p>
                        </div>
                        </div>
                           
                        <div class="card mb-3" id="btm-card">
                        <img class="card-img-top" src="${data.props[4].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h5 class="card-title">Featured Home</h5>
                        <p class="card-text">${data.props[4].address}</p>
                        <p class="card-text"><small class="text-muted">${data.props[4].propertyType}</small></p>
                        </div>
                        </div>`
            $(appendHtml).append(html)
            $(appendHtmlTwo).append(html2)

        })
}
getListings();