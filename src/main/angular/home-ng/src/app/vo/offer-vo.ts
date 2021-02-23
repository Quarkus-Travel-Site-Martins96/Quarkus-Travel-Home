import { ShowcaseProductVO } from './showcase-vo';

export class OfferVO {
	
	products: OfferProductVO[];
	
}

export class OfferProductVO extends ShowcaseProductVO {
	
	offerPrice: number;
}