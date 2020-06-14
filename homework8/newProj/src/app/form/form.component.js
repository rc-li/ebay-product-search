
function valueCheck() {
    let lowPrice = parseFloat(document.getElementById('lowPrice').value)
    let highPrice = parseFloat(document.getElementById('highPrice').value)
    if (lowPrice < 0.0) {
        alert("Price Range values cannot be negative! Please try a value greater than or equal to 0.0")
        return false
    }
    else if (lowPrice > highPrice) {
        alert("Oops! Lower price limit cannot be greater than upper price limit! Please try again.")
        return false
    }
    else return true
}

valueCheck()


alert("im jsut using javascript")