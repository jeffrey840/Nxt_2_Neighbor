// This returns houses that have recently been sold in the houston area
const options = {
    method: 'GET',
    headers: {
        'X-RapidAPI-Key': '4b79262d6emsh7cc3f4048fc7718p12b945jsndb86ef3ab993',
        'X-RapidAPI-Host': 'zillow-com1.p.rapidapi.com'
    }
};
let deleteListing = 'https://zillow-com1.p.rapidapi.com/propertyExtende…Sold&home_type=Houses&sort=Homes_for_Youundefined'
let listingUrl = 'https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location=houston%2C%20Tx&page=1&status_type=RecentlySold&home_type=Houses&sort=Homes_for_You'

let imgSrc
let address
let propertyType
let listing
let appendHtml = document.getElementById("listing-display");
function getListings() {
    fetch(listingUrl, options)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            imgSrc = data.props[0].imgSrc
            address = data.props[0].address
            propertyType = data.props[0].propertyType
            console.log(imgSrc);
            console.log(address);
            console.log(propertyType);
            for(let i = 0; i < data.length; i++){
                console.log(data[i]);
            }
            let html = `

<!--CARD ONE--------------------------------------------->
                          <div class="card" style="width: 18rem;">
                          <img class="card-img-top" src="${data.props[19].imgSrc}" alt="Card image cap">
                          <div class="card-body">
                          <h5 class="card-title">SAVED HOMES</h5>
                          <p class="card-text">${data.props[19].address}</p>
                          <p class="card-text">${data.props[19].propertyType}</p>
                          <p>PRICE</p>
                          <p>$200,000</p>
                          <a href="#" class="btn btn-primary" id=${data.props[19].id}" onclick="deleteFunction(this.parentNode.parentNode)">DELETE</a>
                          <!-- Trigger/Open The Modal -->
                                 <button class="btn btn-primary" id="update-btn-1">EDIT</button>

                    <!-- The Modal -->
                                <div id="myModal-1" class="modal-1">
                    <!-- Modal content -->
                                <div class="modal-content-1">
                                <span class="close-1" id="close-1">&times;</span>
                               <form id="edit-form-1">
                                        <label>
                                        <h2>title</h2>
                                        <input type="text" placeholder="title">
                                        </label>
                                        <br>
                                        <label>
                                        <h2>description</h2>
                                        <input type="text" placeholder="description">
                                        </label>
                                        <label>
                                        <h2>price</h2>
                                        <input type="text" placeholder="price">
                                        </label>
                                        <br>
                                        <label>
                                        <h2>address</h2>
                                        <input type="text" placeholder="address">
                                        </label>
                                        <br>
                                        <button class="btn btn-primary">SAVE</button>
                                        </form>
                                </div>
                            
                                </div>
                          </div>
                          </div>
                          <script>
                          let modalOne = document.getElementById("myModal-1");
                          let btnOne = document.getElementById("update-btn-1")
                          let spanOne = document.getElementById("close-1")
                          btnOne.onclick = function() {
                          modalOne.style.display = "block";
                            }
                          spanOne.onclick = function() {
                          modalOne.style.display = "none";
                            }
                          window.onclick = function(event) {
                          if (event.target === modalOne) {
                          modalOne.style.display = "none";
                            }
                           }
                          </script>`
            $(appendHtml).append(html)

        })
}
getListings();

//DELETE FUNCTION
function deleteFunction(element) {
    let id = $(element).remove('.card').data('id');
    fetch(deleteListing + id, {
        method: 'DELETE',
    }).then(response => {
        console.log(response);
        $(element).remove('.card').remove();
    });
}


//NOT USED ATM
function setButtons() {
    let deleteBtn = document.querySelectorAll('a');
    deleteBtn.forEach(x => {
        deleteBtn.addEventListener('click', function (e) {
            e.preventDefault();
        });
    });
}

//EDIT FORM FUNCTION
function putListing(title, address, description, price) {
    fetch(listingUrl + options, {
        method: 'POST',
        body: JSON.stringify({
            title: title,
            address: address,
            description: description,
            price: price
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) => response.json())
        .then((data) => {
        });
}

