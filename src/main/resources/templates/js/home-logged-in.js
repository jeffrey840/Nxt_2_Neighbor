
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
                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="${data.props[0].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h6  class="card-title">${data.props[0].address}</h6>
                        <p   class="card-text">${data.props[0].propertyType}</p>
                        <p class="card-text">NEIGHBORS INTERESTS<br> 1. <br>2. <br>3</p>
                        <a href="#" class="btn btn-primary">VIEW</a>
                        </div>
                        </div>

                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="${data.props[2].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h6  class="card-title">${data.props[2].address}</h6>
                        <p   class="card-text">${data.props[2].propertyType}</p>
                        <p class="card-text">NEIGHBORS INTERESTS<br> 1. <br>2. <br>3</p>
                        <a href="#" class="btn btn-primary">VIEW</a>
                        </div>
                        </div>

                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="${data.props[4].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h6  class="card-title">${data.props[4].address}</h6>
                        <p   class="card-text">${data.props[4].propertyType}</p>
                        <p class="card-text">NEIGHBORS INTERESTS<br> 1. <br>2. <br>3</p>
                        <a href="#" class="btn btn-primary">VIEW</a>
                        </div>
                        </div>`
            $(appendHtml).append(html)
        })
}
getListings();