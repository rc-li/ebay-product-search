import { Component, OnInit} from '@angular/core';
import { DataService } from "../data.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  emptyKeywordAlert: boolean = false;
  minmaxAlert: boolean = false;
  responseEmptyAlert: boolean = false;

  valueCheck():boolean {
    let retVal: boolean = true;

    //check for empty keyword
    let keyWord = (<HTMLInputElement>document.getElementsByTagName('input')[0]).value
    if (keyWord == '') {
      this.emptyKeywordAlert = true;
      retVal = false;
    }
    else {
      this.emptyKeywordAlert = false;
    }

    //check for minPrice and maxPrice
    let lowPriceStr = (<HTMLInputElement>document.getElementById('lowPrice')).value
    let highPriceStr = (<HTMLInputElement>document.getElementById('highPrice')).value
    let lowPrice = parseFloat(lowPriceStr)
    let highPrice = parseFloat(highPriceStr)
    if (lowPrice < 0.0) {
      this.minmaxAlert = true
      retVal = false
    }
    else if (lowPrice > highPrice) {
      this.minmaxAlert = true
      retVal = false
    }
    else if (highPrice < 0.0) {
      this.minmaxAlert = true
      retVal = false
    }
    else {
      this.minmaxAlert = false
    }

    //if any of the check fails, return false
    if (retVal == true) {
      return true
    }
    else {
      return false
    }

  }
  
  keyword: string;
  response: any;
  data: any;
  dataLength: number;

  searchClicked():void {
    this.data = null
    this.dataLength = 0
    if (this.valueCheck()) {
      // str:String = String.toString(formData)

      let form = document.getElementsByTagName('form')[0]
      let formData = new FormData(form)
      
      //@ts-ignore
      let params = new URLSearchParams(formData)
      console.log(params.toString())

      fetch("/q?" + params)
        .then(response => response.json())
        .then((response) => {
          this.response = response
        })
        .then(() => {
          this.data = this.response.searchResult.item
          this.dataLength = this.data.length
          this.keyword = document.getElementsByTagName('input')[0].value
          console.log(this.keyword)

          if (this.data.length == 0) {
            this.responseEmptyAlert = true
          }
          else {
            this.responseEmptyAlert = false
          }
        })
    }
  }

  formReset(): void {
    document.getElementsByTagName('form')[0].reset()
    this.emptyKeywordAlert = false
    this.minmaxAlert = false
    this.responseEmptyAlert = false
    this.data = null
    this.dataLength = null
  }
}


