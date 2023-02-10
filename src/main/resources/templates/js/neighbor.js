
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

let appendHtml = document.getElementById("listing-display");
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
                        <img class="card-img-top" src="${data.props[19].imgSrc}" alt="Card image cap">
                        <div class="card-body">
                        <h5 class="card-title">YOUR NEIGHBOR</h5>
                        <p>${data.props[0].propertyType}</p>
                        <p class="card-text">NEIGHBORS INTERESTS<br>
                        <p>OUTDOORS</p>
                        <p>INTROVERT</p>
                        <p>EXTROVERT</p>
                        <a href="#" class="btn btn-primary">EDIT</a>
                        </div>
                        </div>`
            $(appendHtml).append(html)
        })
}
getListings();