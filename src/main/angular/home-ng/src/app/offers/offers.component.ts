import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RestService } from '../rest-service';
import { host } from '../../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { OfferVO } from '../vo/offer-vo';


const homeUrl: string = 'http://' + host + '/home/offers';


@Component({
	selector: 'app-offers',
	templateUrl: './offers.component.html',
	styleUrls: ['./offers.component.css']
})
export class OffersComponent implements OnInit {
	offers: OfferVO = new OfferVO();
	error: string;
	
	private sub: Subscription;

	constructor(private rest: RestService) {

	}

	ngOnInit(): void {
		if (this.offers) {
			if (this.sub)
				this.sub.unsubscribe();
				
			this.sub = this.rest.sendGet<OfferVO>(homeUrl, new HttpHeaders({
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
