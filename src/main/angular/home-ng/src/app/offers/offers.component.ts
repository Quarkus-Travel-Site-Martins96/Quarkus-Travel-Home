import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RestService } from '../rest-service';
import { Environment } from '../../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { OfferVO } from '../vo/offer-vo';


@Component({
	selector: 'app-offers',
	templateUrl: './offers.component.html',
	styleUrls: ['./offers.component.css']
})
export class OffersComponent implements OnInit {
	
	private homeUrl: string =  Environment.getHomeHost() + '/home/offers';
	urlHotel: string;
	
	offers: OfferVO = new OfferVO();
	error: string;
	
	private sub: Subscription;

	constructor(private rest: RestService) {
		console.log("costruttore di offerte");
		this.urlHotel = Environment.getHotelHost() + "/?hotel-id=";
	}

	ngOnInit(): void {
		if (this.offers) {
			if (this.sub)
				this.sub.unsubscribe();
				
			console.log("prendo le offerte");
				
			this.sub = this.rest.sendGet<OfferVO>(this.homeUrl, new HttpHeaders({
				'content-type': 'application/json'
			})).subscribe(r => {
				this.offers = r.body;
			}, error => {
				console.error(error);
				this.error = error;
			});
		}
	}

}
