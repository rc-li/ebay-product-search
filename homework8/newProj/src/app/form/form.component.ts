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
    alert('value check called')
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
}


