import { Component, OnInit } from '@angular/core';
import { catchError, Subscription, throwError } from 'rxjs';
import { RestService } from '../rest-service';
import { Environment } from '../../environments/environment';
import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
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
	isLoading: boolean;
	
	private sub: Subscription;

	constructor(private rest: RestService) {
		console.log("costruttore di offerte");
		this.urlHotel = Environment.getHotelHost() + "/?hotel-id=";
	}

	ngOnInit(): void {
		if (this.offers) {
			if (this.sub)
				this.sub.unsubscribe();
				
			console.log("Taking offerts via REST");
				
			this.isLoading = true;
			this.error = undefined;
			this.sub = this.rest.sendGet<OfferVO>(this.homeUrl, new HttpHeaders({
				'content-type': 'application/json'
			})).pipe(
				catchError(this.handleError)
			).subscribe(r => {
				this.isLoading = false;
				console.log('Rest call finished');
				if (!r || !r.body || r.body == null) {
					this.error = 'Empty response for offers';
				} else if (r.ok) {
					this.offers = r.body;
				}
			});
		}
	}


	private handleError(err: HttpErrorResponse) {
		this.isLoading = false;
		console.error(err);
		this.error = err.message;
		return throwError(() => new Error(this.error));
	}


}
