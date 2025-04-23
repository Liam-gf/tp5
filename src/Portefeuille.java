public class Portefeuille
{
	private Cryptomonnaie monnaie;
	private double montant; // Soit le nombre de jetons
	private String proprietaire;
	
	public Portefeuille(Cryptomonnaie monnaie, double montant, String proprietaire)
	{
		this.monnaie      = monnaie;
		this.montant      = montant;
		this.proprietaire = proprietaire;
	}


	/**
	  * Cette fonction vous permet de transfÃ©rer des devises du portefeuille actuel 
	  * vers le portefeuille de destination pour le montant indiquÃ©. Le type de devise 
	  * (nom du Jeton) doit Ãªtre le mÃªme entre les deux portefeuilles et le montant 
	  * du portefeuille actuel doit Ãªtre supÃ©rieur ou Ã©gal Ã  celui indiquÃ©.
	  * @param destination 
	  * @param montantJetons
	  * @return Vrai si la transaction a Ã©tÃ© effectuÃ©e, faux sinon.  
	  */
	public boolean transfertDevise (Portefeuille destination, double montantJetons)
	{
		this.montant -= montantJetons;
		destination.montant += montantJetons;
		if (this.montant >= 0 && this.monnaie.getNom().equals(destination.monnaie.getNom()))
		{
			return true;
		}
		else
		{
			this.montant += montantJetons;
			destination.montant -= montantJetons;
		}
		return false;
	}


	/**
	  * Cette fonction vous permet d'acheter des jetons de la 
	  * crypto-devise en fonction de leur valeur en euros. 
	  * Le rÃ©sultat est l'augmentation des jetons de la crypto-monnaie.
	  * @param montantEuros Valeur d'achat en euros 
	  * @return true si le montant en euros est supÃ©rieur ou Ã©gal Ã  0 
	  */
	public boolean achatDevise (double montantEuros)
	{
		this.montant += montantEuros / this.monnaie.getValeurDeJeton();
		if (montantEuros >= 0)
		{
			return true;
		}
		else
		{
			this.montant -= montantEuros / this.monnaie.getValeurDeJeton();
		}
		return false;
	}

	/**
	  * Valide si le proprietaire passÃ© en parametre est celui
	  * qui as le portefeuille
	  * @param proprietaire
	  * @return true si les nom du propriÃ©taire est correct
	  */
	public boolean estProprietaire (String proprietaire)
	{
		return (proprietaire.equals(this.proprietaire))?true:false;
	}

	/**
	  * 
	  * @return La valeur en euros du Portefeuille. 
	  * Autrement dit, le monant de jetons multipliÃ© par la valeur des jetons. 
	  */
	public double valeurEnEuros()
	{
		return this.montant * this.monnaie.getValeurDeJeton();
	}


	public String getProprietaire()
	{
		return proprietaire;
	}


	public Cryptomonnaie getMonnaie()
	{
		return monnaie;
	}


	public double getMontant()
	{
		return montant;
	}


	@Override
	public String toString()
	{
		return String.format("%10s",proprietaire) + " : "
		     + String.format("%10.1f", montant)   + " x " 
		     + this.monnaie.toString()            + " = "
		     + String.format("%10.1f", valeurEnEuros());
	}
} 
