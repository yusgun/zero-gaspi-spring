package zerogaspi.model;


public interface IViews {
	public static interface IViewBasic {}
	
	public static interface IViewAssociation extends IViewBasic {}
	
	//public static interface IViewAssociationDetail extends IViewAssociation {}
	
	public static interface IViewClient extends IViewBasic {}
	
	//public static interface IViewClientDetail extends IViewClient {}
	
	public static interface IViewCommande extends IViewBasic {}
	
	//public static interface IViewCommandeDetail extends IViewCommande {}
	
	public static interface IViewCommandeWithLot extends IViewCommande {}
	
	public static interface IViewCommandeWithFacture extends IViewCommande {}
	
	public static interface IViewCommandeWithLotAndFacture extends IViewCommandeWithLot,IViewCommandeWithFacture {}
	
	
	
	public static interface IViewCommandeGratuite extends IViewCommande {}
	
	public static interface IViewCommandeGratuiteWithLot extends IViewCommandeGratuite, IViewCommandeWithLot {}
	
	public static interface IViewCommandeGratuiteWithLotAndFacture extends IViewCommandeGratuiteWithLot, IViewCommandeWithFacture, IViewCommandeGratuite {}
	
	
	
	public static interface IViewCommandePayante extends IViewCommande {}
	
	public static interface IViewCommandePayanteWithLot extends IViewCommandePayante, IViewCommandeWithLot {}
	
	public static interface IViewCommandePayanteWithFacture extends IViewCommandePayante, IViewCommandeWithFacture {}
	
	public static interface IViewCommandePayanteWithLotAndFacture extends IViewCommandePayanteWithLot, IViewCommandeWithFacture, IViewCommandePayante {}
	
	
	public static interface IViewConnexion extends IViewBasic {}
	
	//public static interface IViewConnexionDetail extends IViewConnexion {}
	
	public static interface IViewEntreprise extends IViewBasic {}
	
	//public static interface IViewEntrepriseDetail extends IViewEntreprise {}
	
	public static interface IViewEntrepriseWithVendeur extends IViewBasic {}
	
	public static interface IViewFacture extends IViewBasic {}
	
	//public static interface IViewFactureDetail extends IViewFacture {}
	
	public static interface IViewFactureWithCommande extends IViewFacture {}
	
	
	public static interface IViewIdentite extends IViewBasic {}
	
	//public static interface IViewIdentiteDetail extends IViewIdentite {}
	
	public static interface IViewIdentiteWithConnexion extends IViewIdentite {}
	
	public static interface IViewListeFavori extends IViewBasic {}
	
	
	//public static interface IViewListeFavoriDetail extends IViewListeFavori {}
	
	public static interface IViewListeFavoriWithEntreprise extends IViewListeFavori {}
	
	public static interface IViewListeFavoriWithClient extends IViewListeFavori {}
	
	public static interface IViewListeFavoriWithClientAndEntreprise extends IViewListeFavori,IViewListeFavoriWithEntreprise,IViewListeFavoriWithClient {}
	
	public static interface IViewLot extends IViewBasic {}
	
	//public static interface IViewLotDetail extends IViewLot {}
	
	public static interface IViewLotWithEntreprise extends IViewLot {}
	
	public static interface IViewPaiement extends IViewBasic {}
	
	//public static interface IViewPaiementDetail extends IViewPaiement {}
	
	public static interface IViewPaiementWithCommandePayante extends IViewPaiement {}
	
	public static interface IViewParticulier extends IViewBasic {}
	
	//public static interface IViewParticulierDetail extends IViewParticulier {}
	
	public static interface IViewVendeur extends IViewBasic {}
	
	//public static interface IViewVendeurDetail extends IViewVendeur {}

}
