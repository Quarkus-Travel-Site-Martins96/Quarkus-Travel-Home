

export class ShowcaseVO {
	
	products: ShowcaseProductVO[];
	
}

export class ShowcaseProductVO {
	
	hotelID: string;
	title: string;
	location: string;
	basicPrice: number;
	description: string;
	image: string;
}