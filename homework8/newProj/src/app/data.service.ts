import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { 
  }

  data:any;

  setData(data:any): void {
    // console.log("I'm doing something")
    if (data) {
      this.data = data
    }
  }

  getData(): any {
    return this.data
  }

}
