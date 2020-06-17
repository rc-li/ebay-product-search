import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() data: any
  @Input() dataLength: number
  @Input() keyword: string
  p: number = 1;

  displayData(): void {
    console.log(this.keyword)
  }

}
