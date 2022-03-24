package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "product")
@Entity
public class Produit {

	@Id
	private long id;
        private String nom;
	private String groupe_aliment;
        private String sousgroupe_aliment;
        @Column(name = "livraison") // que si le nom de la variable est différent du nom du champ
        private String conditionLivraison;
        private String materiau_emballage;
        private double score_unique_ef;
        private double changement_climatique;
        private double appauvrissement_couche_ozone;
        private double particules;
	

}
