import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  valueCheck() {
    let lowPriceStr = (<HTMLInputElement>document.getElementById('lowPrice')).value
    let highPriceStr = (<HTMLInputElement>document.getElementById('highPrice')).value
    let lowPrice = parseFloat(lowPriceStr)
    let highPrice = parseFloat(highPriceStr)
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


  callFetch() {
    if (this.valueCheck()) {
      // str:String = String.toString(formData)
      const formData = new FormData(form)
      let params = new URLSearchParams(formData)
      console.log(params.toString())

      let req = new XMLHttpRequest()
      req.open("GET", "/q?" + params, false)
      req.send();
      let res = req.responseText
      console.log(res)
      let obj = JSON.parse(res)
      let num_entries = obj.numEntries
      let searchResult = obj.searchResult
      let numValidCards = obj.validCards

      // updateSummary();
      // setAllData();
    }
  }
}


