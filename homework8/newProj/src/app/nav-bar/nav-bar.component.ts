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
  p: number = 1;

  displayData(): void {
    console.log(this.data)
  }

}
