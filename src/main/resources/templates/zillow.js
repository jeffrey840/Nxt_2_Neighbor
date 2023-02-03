// This returns houses that have recently been sold in the houston area
const options = {
    method: 'GET',
    headers: {
        'X-RapidAPI-Key': '4b79262d6emsh7cc3f4048fc7718p12b945jsndb86ef3ab993',
        'X-RapidAPI-Host': 'zillow-com1.p.rapidapi.com'
    }
};

fetch('https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location=houston%2C%20Tx&page=1&status_type=RecentlySold&home_type=Houses&sort=Homes_for_You', options)
    .then(response => response.json())
    .then(response => console.log(response))
    .catch(err => console.error(err));