import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RestService } from '../rest-service';
import { host, urlHotelBase } from '../../environments/environment';
import { ShowcaseVO } from '../vo/showcase-vo';
import { HttpHeaders } from '@angular/common/http';


const homeUrl: string = host + '/home/showcase';

@Component({
  selector: 'app-showcase',
  templateUrl: './showcase.component.html',
  styleUrls: ['./showcase.component.css']
})
export class ShowcaseComponent implements OnInit {
	showcase: ShowcaseVO = new ShowcaseVO();
	error: string;
	urlHotel: string = urlHotelBase + '/?hotel-id=';
	
	
	private sub: Subscription;
	

	constructor(private rest: RestService) {
		
	}
	
	ngOnInit(): void {
		if (this.showcase) {
			if (this.sub)
				this.sub.unsubscribe();
				
			this.sub = this.rest.sendGet<ShowcaseVO>(homeUrl, new HttpHeaders({
				'content-type': 'application/json'
			})).subscribe(r => {
				this.showcase = r.body;
			}, error => {
				console.error(error);
				this.error = error;
			});
		}
			
	}
}
