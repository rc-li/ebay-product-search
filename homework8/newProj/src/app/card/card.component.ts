import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() cardData: any;
  buttonVal: number = 0;
  buttonText: string = "Show Details"
  displayTab: boolean = false

  click(): void {
    if (this.buttonVal == 0) {
      this.buttonVal = 1;
      this.buttonText = "Hide Details"
      this.displayTab = true
    }
    else if (this.buttonVal == 1) {
      this.buttonVal = 0;
      this.buttonText = "Show Details"
      this.displayTab = false
    }
  }
}
