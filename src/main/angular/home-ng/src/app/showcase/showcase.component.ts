import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RestService } from '../rest-service';
import { Environment } from '../../environments/environment';
import { ShowcaseVO } from '../vo/showcase-vo';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-showcase',
  templateUrl: './showcase.component.html',
  styleUrls: ['./showcase.component.css']
})
export class ShowcaseComponent implements OnInit {
	
	private homeUrl: string = Environment.getHomeHost() + '/home/showcase';
	urlHotel: string = Environment.getHotelHost() + '/?hotel-id=';
	
	showcase: ShowcaseVO = new ShowcaseVO();
	error: string;
	homeBgImg: string;
	
	private sub: Subscription;
	

	constructor(private rest: RestService) {
	}
	
	ngOnInit(): void {
		this.homeBgImg = "background-image:url('./assets/background/showcase" + this.getRandomInt(5) + ".jpg');";
		
		if (this.showcase) {
			if (this.sub)
				this.sub.unsubscribe();
				
			this.sub = this.rest.sendGet<ShowcaseVO>(this.homeUrl, new HttpHeaders({
				'content-type': 'application/json'
			})).subscribe(r => {
				this.showcase = r.body;
			}, error => {
				console.error(error);
				this.error = error;
			});
		}
			
	}
	
	


	private getRandomInt(max: number): number {
		return Math.floor(Math.random() * Math.floor(max)) + 1;
	}
}
