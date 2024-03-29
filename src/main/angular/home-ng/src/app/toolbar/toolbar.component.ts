import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';
import { Environment } from 'src/environments/environment';
import { RestService } from '../rest-service';
import { JWT } from './jwt-decode-vo';
import { DefaultAvatar } from './default.avatar';
import { CookieService } from 'ngx-cookie-service';

@Component({
	selector: 'app-toolbar',
	templateUrl: './toolbar.component.html',
	styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit, AfterViewInit {
	
	private validateUrl: string = Environment.getLoginHost() + "/validate";

	homeUrl: string = Environment.getHomeHost();
	loginUrl: string = Environment.getLoginHost();
	userManagementUrl: string = Environment.getUserManagerHost();
	staticContentHost: string = Environment.getStaticContentHost();

	jwt: JWT = new JWT();
	cookieJWT: string;

	isAvatarExists: boolean = false;
	avatarSrc: string | ArrayBuffer;
	DEFAULT_AVATAR : string = DefaultAvatar.DEFAULT_AVATAR;

	constructor(private cookies: CookieService, private rest: RestService) {
		this.cookieJWT = this.cookies.get('user.jwt');
	}


	ngOnInit(): void {

		if (this.cookieJWT) {
			
			//JWT found, check if is valid
			const obsv: Observable<HttpResponse<JWT>> = this.rest.sendPost<JWT>(this.validateUrl, this.cookieJWT, new HttpHeaders({
				'content-type': 'text/plain'
			}))

			firstValueFrom(obsv)
					.then((resp) => {
						//JWT correct, setting the token information
						this.jwt = resp.body;
						// Loading avatar image
						this.userAvatarSetting();
					})
					.catch(error => {
						//JWT non correct or service not available
						if (error.status === 401) {
							console.error('The token JWT is not valid, relogin required', error)
							this.logout();
						} else {
							console.error('The call is not end correct', error)
							this.jwt = null;
						}
					});
		}
	}

	ngAfterViewInit(): void {
		let prevScrollpos = window.pageYOffset;
		window.onscroll = function() {
			var currentScrollPos = window.pageYOffset;
			if (prevScrollpos > currentScrollPos) {
				document.getElementById("navbar").style.top = "0";
			} else {
				document.getElementById("navbar").style.top = "-80px";
			}
			prevScrollpos = currentScrollPos;
		}
	}
	
	logout(): void {
		this.cookies.delete('user.jwt');
		window.top.location.href = this.homeUrl;
	}


	private userAvatarSetting(): void {
		if (!this.cookieJWT) {
			this.isAvatarExists = false;
			return;
		}

		this.isAvatarExists = false;
		this.getAvatar(this.jwt.avatar);
	}


	async getAvatar(filename: string): Promise<void> {
		const getAvatarUrl = this.staticContentHost + "/get/" + filename;
		console.log("Getting avatar: " + getAvatarUrl);
		const obsv: Observable<HttpResponse<Blob>> = this.rest.sendGetAvatar(getAvatarUrl)

		await firstValueFrom(obsv)
				.then((resp) => {
					if (!resp || !resp.body) {
						this.isAvatarExists = false;
						return;
					}
					var reader = new FileReader();
 					reader.readAsDataURL(resp.body)
					reader.onload = (_event) => {
						this.avatarSrc = reader.result;
						this.isAvatarExists = true;
					}
				})
				.catch(error => {
					console.error("Error getting avatar: ", error);
					this.isAvatarExists = false;
				});
	}

}
