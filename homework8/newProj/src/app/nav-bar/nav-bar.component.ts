import { Component, OnInit } from '@angular/core';
import { DataService } from "../data.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  p: number = 1;
  collection: any[] = ['a','a','a','a','a','a','a','b'];
}
