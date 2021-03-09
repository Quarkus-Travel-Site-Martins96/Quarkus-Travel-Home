import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-home-head',
	templateUrl: './home-head.component.html',
	styleUrls: ['./home-head.component.scss']
})
export class HomeHeadComponent implements OnInit {

	homeBgImg: string;
	
	cicleEffectNum: number[] = Array<number>(50);

	constructor() { }

	ngOnInit(): void {
		this.homeBgImg = "background-image:url('./assets/background/home" + this.getRandomInt(5) + ".jpg');";
	}



	private getRandomInt(max: number): number {
		return Math.floor(Math.random() * Math.floor(max)) + 1;
	}
}
