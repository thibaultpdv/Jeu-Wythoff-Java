/**
 * Ce programme permet de joueur contre un ordinateur au jeu de Wythoff
 * @author PONDAVEN Thibault
 */
class Wythoff2 {
    void principal() {
		/*testVictoire();
		testPositionPionX();
		testPositionPionY();
		testChangementJoueur();
		testCreerPlateau();
		testContient();
		testEstUnePositionGagnante();
		testChercheProchainX();*/
		
        jouer();
    }
    /**
	* Permet de déterminer toutes les positions gagnantes d'un plateau et de les afficher sur le plateau
	* @param n la taille du plateau de jeu
	* @param plateau , le plateau que l'on souhaite traiter et intéragir avec
	* @param posGagnantes , un tableau a deux entrées où l'on stockera toutes les coordonnées des positions gagnantes
	*/
    void toutesPositionsGagnantes(int n,char[][] plateau,int[][] posGagnantes) {
        int maxPositions = n/2; // Nombre maximal de positions gagnantes dans la moitié supérieure

        int[] positionsX = new int[maxPositions]; // Tableau pour stocker les abscisses
        int[] positionsY = new int[maxPositions]; // Tableau pour stocker les ordonnées

        positionsX[0] = 0; // Première position gagnante
        positionsY[0] = 0;

        for (int i = 1; i < maxPositions; i++) {
            // Trouver la prochaine abscisse disponible
            int x = chercheProchainX(positionsX,positionsY, i);

            // Calculer l'ordonnée en fonction de l'abscisse et du rang
            int y = x + i;

            positionsX[i] = x;
            positionsY[i] = y;
        }

        // Afficher les positions gagnantes
        for (int i = 0; i < maxPositions; i++) {
            System.out.println("Position Gagnante " + i + ": (" + positionsX[i] + ", " + positionsY[i] + ")");
            plateau[positionsX[i]][positionsY[i]] = 'x';
            plateau[positionsY[i]][positionsX[i]] = 'x';
            posGagnantes[0][i] = positionsX[i];
            posGagnantes[1][i] = positionsY[i];
        }
    }
	/**
	 * Permet de trouver la prochaine valeur qu'il nous manque dans la recherche de nos indices et de nos rangs
	 * @param positionsX , un tableau d'entiers contenant les positions x
	 * @param positionsY , un tableau d'entiers contenant les positions y
	 * @param rangActuel , un entier contenant la valeur du rang actuel
	 * @return prochainX , un entier contenant la valeur du prochain x non utilisés dans les rangs
	 */
    int chercheProchainX(int[] positionsX,int[] positionsY, int rangActuel) {
		//Initialisation des variables locales
        int prochainX = rangActuel;
        //Condition tant que une certaines valeur est présente on continue à chercher la prochaine valeur manquantes
        while ((contient(positionsX, prochainX))||(contient(positionsY,prochainX)) ){
            prochainX++;
        }
        return prochainX;
    }
    
	/**
	* Permet de savoir si l'on possède une valeur dans une liste de rang et de x 
	* @param tabPosition, tableau d'indice pour stocker les rangs
	* @param value, la valeur de l'indice
	* @return contient, un booléen permettant de savoir si l'on a déjà une valeur donc notre recherche de rang
	*/
    boolean contient(int[] tabPosition, int value) {
		boolean contient = false;
		//On cherche sur une valeur est présente dans un tableau
        for (int i : tabPosition) {
            if (i == value) {
                contient = true;
            }
        }
        return contient;
    }
    
    /**
	* Affichage du plateau de jeu avec les indices de lignes
	* et de colonnes
	* @param plateau le tableau a afficher
	*/
    void affichePlateau(char[][] plateau){
		//Initialisation de variables locales
		int LINE_LENGTH = plateau.length;
		int COLUMM_LENGTH = plateau[0].length;
		System.out.println();
		//Positionnement des indices de la colonne de gauche sur le plateau pour indiquer les coordonnées
		for(int i = LINE_LENGTH-1; i >= 0;i--){
			System.out.print(i);
			if(i < 10){
				System.out.print(" ");
			}
			System.out.print(" |");
			//Positionnement des séparateurs pour dessiner le plateau
			for(int j = 0; j < COLUMM_LENGTH;j++){
				System.out.print(" " + plateau[i][j] + " " + "|");
				
			}
			
			System.out.println();

		}
		System.out.print("  ");
		//Positionnement des indices de la ligne supérieure sur le plateau pour indiquer les coordonnées
		for (int i = 0; i < COLUMM_LENGTH; i++) {
			System.out.print("   " + i);
		}
		System.out.println();
	
				
	}
	/**
	* Créer un plateau de jeu carré rempli de caractere espace ’ ’
	* @param lg taille du plateau (lg < 10 : pas à vérifier)
	* @return tableau de caractere en deux dimensions
	*/
	char[][] creerPlateau(int lg){
		//Création d'un plateau de longueur
		char[][] plateau = new char[lg][lg];
		int x = 0;
		int y = 0;
		//On ajoute à chaque case un emplacement vide
		for(int i = 0; i < plateau.length; i++){
			for(int j = 0; j < plateau[i].length;j++){
				plateau[i][j] = ' ';
			}
		}
		return plateau;
	}
	
	/**
	* Place le pion aléatoirement sur le plateau
	* @param plateau le plateau que l'on souhaite traiter
	*/
	void placementPionAleatoire(char[][]plateau){
		int x = 0;
		int y = 0;
		//Génération d'une position aléatoire pour le pion de début de partie
		do{
			x = (int) (Math.random() * plateau.length);
			y = (int) (Math.random() * plateau.length);
		}while(x <= 1 || y <= 1 || x >= plateau.length-1 || y >= plateau.length-1 || x == y);
        plateau[x][y] = 'o';

	}
	/**
	* Récupération des coordonnées x du pion
	* @param plateau le plateau souhaiter à traiter
	* @return x la coordonnée x du pion
	*/
	int positionPionX(char[][] plateau){
		//Initialisation de variables locales
		int LINE_LENGTH = plateau.length;
		int COLUMM_LENGTH = plateau[0].length;
		int x=0;
		int y=0;
		//Recherche des valeurs de x et y (retenu que de x)
		for(int i = 0; i < LINE_LENGTH; i++){
			for(int j = 0; j < COLUMM_LENGTH; j++){
				if(plateau[i][j] == 'o'){
					x = i ;
					y = j ;
				}
			}
		}
		return x;
		
	}
	
	/**
	* Récupération des coordonnées y du pion
	* @param plateau le plateau souhaiter à traiter
	* @return y la coordonnée y du pion
	*/
	int positionPionY(char[][] plateau){
		//Initialisation de variables locales
		int LINE_LENGTH = plateau.length;
		int COLUMM_LENGTH = plateau[0].length;
		int x=0;
		int y=0;
		//Recherche de la valeur de x et y (retenu que de y)
		for(int i = 0; i < LINE_LENGTH; i++){
			for(int j = 0; j < COLUMM_LENGTH; j++){
				if(plateau[i][j] == 'o'){
					x = i ;
					y = j ;
				}
			}
		}
		return y;
	}
	/**
	* Permet de faire bouger le pion sur le plateau par le joueur
	* @param plateau le plateau souhaiter à traiter
	*/
	/**
	* Permet de faire bouger le pion sur le plateau
	* @param plateau le plateau souhaiter à traiter
	*/
	void mouvementPion(char[][] plateau) {
		//Initialisation de variables locales
		
		int x=positionPionX(plateau);
		int y=positionPionY(plateau);
		char direction;
		int deplacement;
		boolean positionValable = false;
		
		//Condition tant que le déplacement n'est pas valide on recommence
		while(positionValable == false){
			direction = ' ';
			deplacement = -1;
			//Condition tant que la direction indiqué n'est pas valable on redemande
			while(direction != 'b' && direction != 'g' && direction !=  'd'){
				direction = SimpleInput.getChar("Vers quelle direction voulez-vous déplacer le pion (b pour bas , g pour gauche , d pour diagonale) ? ");
			}
			//Condition tant que le nombre de case désiré pour se déplacer est inférieur ou égal à 0 on redemande
			while(deplacement <= 0){
				deplacement = SimpleInput.getInt("De combien de case voulez-vous vous déplacez ? ");
			}
			//Si la direction choisis est b, le pion va se déplacer vers le bas
			if (direction == 'b'){
				
				if(x-deplacement >= 0){
					plateau[x-deplacement][y] = 'o';
					plateau[x][y] = ' ';
					positionValable = true;
				}else{
					System.out.println("Une erreur a été détectée, merci de réessayer ! ");
				}
			//Si la direction choisis est g, le pion va se déplacer vers la gauche	
			}else if(direction == 'g'){
				if(y-deplacement >= 0){
					plateau[x][y-deplacement] = 'o';
					plateau[x][y] = ' ';
					positionValable = true;
				}else{
					System.out.println("Une erreur a été détectée, merci de réessayer ! ");
				}
			//Sinon si la direction choisis est d, le pion va se déplacer en diagonale
			}else{
				if(x-deplacement >= 0 && y-deplacement >= 0){
					plateau[x-deplacement][y-deplacement] = 'o';
					plateau[x][y] = ' ';
					positionValable = true;
				}else{
					System.out.println("Une erreur a été détectée, merci de réessayer ! ");
				}
			}
		}
	}
	
	/**
	* Permet de faire bouger le pion sur le plateau par l'ordinateur
	* @param plateau le plateau souhaiter à traiter
	* @param posGagnantes, le tableau à deux entrées contenant toutes les positions gagnantes du plateau
	*/
	void mouvementOrdinateur(char[][] plateau,int[][] posGagnantes){
		//Initialisation des variable et constantes locales
		int LINE_LENGTH = plateau.length;
		int COLUMM_LENGTH = plateau[0].length;
		int directionRandom = (int) (Math.random() * 3);
		int random = (int) (Math.random() * plateau.length-1);
		int x = positionPionX(plateau);
		int y = positionPionY(plateau);
		int k = 0;
		int l = 0;
		int compteur = 0;
		boolean fin = false;
		
		//Tant que aucune action n'a été réalisé on redemande
		while(fin == false){
					//Si le pion se trouve sur une position Gagnante le pion se déplace de 1 en fonction d'une direction aléatoire (déplacement aléatoire)
					if(estUnePositionGagnante(x,y,posGagnantes) || estUnePositionGagnante(y,x,posGagnantes)){
						
						//Si la direction est 1, le pion va se déplacer vers la gauche de 1 case
						if(directionRandom == 1 ){
							
							plateau[x-1][y] = 'o';
							plateau[x][y] =  'x';
							fin = true;
						
						//Si la direction est 2, le pion va se déplacer vers le bas de 1 case
						}else if(directionRandom == 2){
							
							plateau[x][y-1] = 'o';
							plateau[x][y] = 'x';
							fin = true;
							
						//Sinon le pion va se déplacer en diagonale de 1 case	
						}else{
							
							plateau[x-1][y-1] = 'o';
							plateau[x][y] = 'x';
							fin = true;
							
						}
					//Si le pion se situe sur la ligne d'ordonnée 0 ou la colonne d'abscisse 0 ou la diagonale ou x = y
					}else if(plateau[0][y] == 'o' || plateau[x][0] == 'o' || (plateau[x][y] == 'o' && x == y)){
						//L'ordinateur déplace le pion directement à l'emplacement gagnant (0;0)
						System.out.println("2");
						plateau[0][0] ='o';
						plateau[x][y] = ' ';
						fin = true;
						
					//Sinon le pion va se déplacer vers la position Gagnante la plus proche	
					}else{
						//On cherche premièrement une position gagnante sur la ligne où se situe le pion, si il y a une position gagnante sur la ligne, l'ordinateur s'y déplace directement
						for( k = x; k > 0; k--){
							if(estUnePositionGagnante(y,k,posGagnantes)&& fin == false){
								plateau[k][y] = 'o';
								plateau[x][y] = ' ';
								fin = true;
							}
							else if(estUnePositionGagnante(k,y,posGagnantes)&& fin == false){
								plateau[k][y] = 'o';
								plateau[x][y] = ' ';
								fin = true;
							}
							else{
								compteur++;
							}
						}
							
						//On cherche sinon une position gagnante sur la colonne où se situe le pion, si il y a une position gagnante sur la colonne, l'ordinateur s'y déplace directement
						for(l = y; l > 0; l--){
							if(estUnePositionGagnante(x,l,posGagnantes)&& fin == false){
								plateau[x][l] = 'o';
								plateau[x][y] = ' ';
								fin = true;
							}else if(estUnePositionGagnante(l,x,posGagnantes)&& fin == false){
								plateau[x][l] = 'o';
								plateau[x][y] = ' ';
								fin = true;
							}else{
								compteur++;
							}
						}
						//Sinon on cherche une position gagnante sur la diagonale, s'il y en a une le pion s'y déplace directement
						for(int m = 0; m < x; m++){
							int a = x-m;
							int b = y-m;
							if(estUnePositionGagnante(a,b,posGagnantes)&& fin == false){
								plateau[a][b] = 'o';
								plateau[x][y] = ' ';
								fin = true;
							}else if(estUnePositionGagnante(b,a,posGagnantes)&& fin == false){
								plateau[a][b] = 'o';
								plateau[x][y] = ' ';
								fin = true;
							}else{
								compteur++;
							}
						}
					}		
				}
			}
		
	
	
	/**
	* Permet d'alterner le joueur
	* @param joueurActuel le nom du joueur qui joue actuellement
	* @param joueur1 le nom du joueur 1
	* @return joueur le nom du joueur qui doit jouer(soit ordinateur soit joueur)
	*/
	String changementJoueur(String joueurActuel, String joueur1){
        // Initialisation de variable locale
        String joueur = joueur1;
        // Changement de symbole en fonction du symbole du dernier joueur
        if (joueurActuel== joueur1) {
            joueur = "Ordinateur";
        }
        return joueur;
    }
    
	/**
	* Permet de savoir si la partie est terminée ou non
	* @param plateau le plateau souhaiter à traiter
	* @return victoire le booléen vérifiant si la partie est terminé ou non
	*/
    boolean victoire(char[][] plateau) {
        boolean victoire = false;
        //Si le pion se situe en (0;0) alors victoire
        if (plateau[0][0] == 'o') {
            victoire = true;
        }
        return victoire;
    }
	
	/**
	 * Méthode permettant de joueur au jeu de Wythoff
	 */
	void jouer() {
		//On entre le nom du joueur
        String joueur1 = SimpleInput.getString("Comment s'appelle le joueur 1 ? ");
        //On demande à l'utilisateur s'il veut commencer ou non
        char premierJoueur = SimpleInput.getChar("Voulez-vous commencer à jouer(o/n) ? ");
        //On demande la taille du plateau
        int taille = SimpleInput.getInt("Sur quelle taille de plateau voulez-vous jouer ? ");
        int[][]posGagnantes = new int[2][taille/2];
        
        // Création du plateau de jeu
        char[][] plateau = creerPlateau(taille);
        toutesPositionsGagnantes(taille, plateau,posGagnantes);
        afficheTab(posGagnantes);
        placementPionAleatoire(plateau);
        
        //On définit le nom de l'adversaire
        String joueur = "Ordinateur";
        if(premierJoueur == 'o'){
			joueur = joueur1;
		}
        // Boucle de jeu : elle continue tant que l'une des conditions de fin n'est pas atteinte
        while (!victoire(plateau)) {
            affichePlateau(plateau);
            
            // Les deux joueurs jouent l'un après l'autre
            System.out.println();
            System.out.println("C'est au joueur " + joueur + " de jouer");
            System.out.println();
            if(joueur == "Ordinateur"){
				mouvementOrdinateur(plateau,posGagnantes);
			}else{
				mouvementPion(plateau);
			}
            // Changement de joueur
            joueur = changementJoueur(joueur, joueur1);
        }
        // Affichage du tableau final
        affichePlateau(plateau);
        // Annonce de fin du jeu et déclaration du gagnant s'il existe
        System.out.println("Fin de la partie");
        if (victoire(plateau)) {
            joueur = changementJoueur(joueur, joueur1);
            System.out.println(joueur + " a gagné ");
        }
    }
    /**
     * Permet d'afficher le contenu d'un tableau à deux entrée
     * @param tab, un tableau d'entiers à deux entrées
     */
     
    void afficheTab(int[][]tab){
		for(int i = 0; i < tab.length; i++){
			System.out.print(i);
			for(int j = 0; j < tab[i].length; j++){
				System.out.print(" " + tab[i][j] + " " + "|");
			}
		System.out.println();
		}	
	}
	/**
	 * Permet de savoir si deux entiers formant des coordonnées sont une position gagnante
	 * @param x , un entier représentant l'abscisse d'une position à analyser
	 * @param y , un entier représentant l'ordonnée d'une position à analyser
	 * @param PosGagnantes , un tableau à deux entiers permettant de savoir si x et y forment un couple de coordonnées gagnantes
	 * @return estPosG, un booléen indiquant si des coordonnées sont une position gagnante, faux sinon
	 */
	boolean estUnePositionGagnante(int x, int y, int[][] PosGagnantes){
		boolean estPosG = false;
		for(int i = 0; i < PosGagnantes[0].length;i++){
			if( x == PosGagnantes[0][i] && y == PosGagnantes[1][i]){
				estPosG = true;
			}
		}
		return estPosG;
	}
	
	/**
     * Effectue des tests de la méthode victoire() avec divers cas.
     */
    void testVictoire() {
        System.out.println();
        System.out.println("*** testVictoire()");
        char[][] plateau1 = creerPlateau(5);
        plateau1[0][0] = 'o';
        char[][] plateau2 = creerPlateau(8);
        testCasVictoire(plateau1, true);
        testCasVictoire(plateau2,false);
        
    }

    /**
     * Effectue un test de la méthode victoire avec un cas donné.
     * @param plateau , le plateau que l'on veut traiter.
     * @param resultat Le résultat attendu du test.
     */
    void testCasVictoire(char[][] plateau, boolean resultat) {
        // Arrange
        System.out.print("victoire(");
        affichePlateau(plateau);
        System.out.print(")\t= " + resultat + "\t : "); 
        // Act
        boolean resExec = victoire(plateau);
        // Assert
        if (resExec == resultat) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }
    
    /**
     * Effectue des tests de la méthode positionPionX avec divers cas.
     */
    void testPositionPionX() {
        System.out.println();
        System.out.println("*** testPositionsPionX()");
        char[][] plateau1 = creerPlateau(5);
        plateau1[0][0] = 'o';
        char[][] plateau2 = creerPlateau(8);
        plateau2[3][5] = 'o';
        testCasPositionPionX(plateau1, 0);
        testCasPositionPionX(plateau2, 3);
        
    }

    /**
     * Effectue un test de la méthode positionPionX avec un cas donné.
     * @param plateau , le plateau que l'on veut traiter
     * @param resultat Le résultat attendu du test.
     */
    void testCasPositionPionX(char[][] plateau, int resultat) {
        // Arrange
        System.out.print("positionPionX(");
        affichePlateau(plateau);
        System.out.print(")\t= " + resultat + "\t : "); 
        // Act
        int resExec = positionPionX(plateau);
        // Assert
        if (resExec == resultat) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }
    
    /**
     * Effectue des tests de la méthode positionPionY avec divers cas.
     */
    void testPositionPionY() {
        System.out.println();
        System.out.println("*** testPositionsPionY()");
        char[][] plateau1 = creerPlateau(5);
        plateau1[0][0] = 'o';
        char[][] plateau2 = creerPlateau(8);
        plateau2[3][5] = 'o';
        testCasPositionPionY(plateau1, 0);
        testCasPositionPionY(plateau2, 5);
        
    }

    /**
     * Effectue un test de la méthode positionPionY avec un cas donné.
     * @param plateau , le plateau que l'on veut traiter
     * @param resultat Le résultat attendu du test.
     */
    void testCasPositionPionY(char[][] plateau, int resultat) {
        // Arrange
        System.out.print("positionPionY(");
        affichePlateau(plateau);
        System.out.print(")\t= " + resultat + "\t : "); 
        // Act
        int resExec = positionPionY(plateau);
        // Assert
        if (resExec == resultat) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }
    
    /**
     * Effectue des tests de la méthode changementJoueur avec divers cas.
     */
    void testChangementJoueur() {
    System.out.println();
    System.out.println("*** testChangementJoueur()");
    String joueurActuel1 = "Alice";
    String joueur1 = "Alice";

    String joueurActuel2 = "Ordinateur";

    String nouveauJoueur1 = changementJoueur(joueurActuel1, joueur1);
    String nouveauJoueur2 = changementJoueur(joueurActuel2, joueur1);

    testCasChangementJoueur(joueurActuel1, joueur1, nouveauJoueur1);
    testCasChangementJoueur(joueurActuel2, joueur1, nouveauJoueur2);
	}
	
	/**
     * Effectue un test de la méthode changementJoueur avec un cas donné.
     * @param joueurActuel , le nom du joueur qui à jouer en dernier
     * @param joueur 1, le nom du joueur 1
     * @param joueur 2, le nom du joueur 2
     * @param resultat Le résultat attendu du test.
     */
	void testCasChangementJoueur(String joueurActuel, String joueur1, String resultat) {
		// Arrange
		System.out.print("changementJoueur(" + joueurActuel + ", " + joueur1 + ")\tAttendu : " + resultat + "\tRésultat : ");
		// Act
		String resExec = changementJoueur(joueurActuel, joueur1);
		// Assert
		if (resExec == resultat) {
			System.out.println("OK");
		} else {
			System.err.println("ERREUR");
		}
	}
	
	/**
     * Effectue des tests de la méthode creerPlateau avec divers cas.
     */
	void testCreerPlateau() {
		System.out.println();
		System.out.println("*** testCreerPlateau()");
		int taillePlateau1 = 5;
		int taillePlateau2 = 8;
		char[][] plateau1 = creerPlateau(taillePlateau1);
		char[][] plateau2 = creerPlateau(taillePlateau2);

		System.out.println("Plateau 1 (Taille attendue : " + taillePlateau1 + "x" + taillePlateau1 + "):");
		affichePlateau(plateau1);
		System.out.println();

		System.out.println("Plateau 2 (Taille attendue : " + taillePlateau2 + "x" + taillePlateau2 + "):");
		affichePlateau(plateau2);

		testCasCreerPlateau(plateau1, taillePlateau1);
		testCasCreerPlateau(plateau2, taillePlateau2);
	}
	
	/**
     * Effectue un test de la méthode creerPlateau avec un cas donné.
     * @param plateau , le plateau que l'on veut traiter
     * @param resultat Le résultat attendu du test.
     */
	void testCasCreerPlateau(char[][] plateau, int resultat) {
		// Arrange
		System.out.print("creerPlateau(" + resultat + ")\t: ");
		// Act
		int tailleObtenue = plateau.length;
		// Assert
		if (tailleObtenue == resultat) {
			System.out.println("OK");
		} else {
			System.err.println("ERREUR - Taille obtenue : " + tailleObtenue);
		}
	}
	
	/**
	 * Effectue des tests de la méthode chercheProchainX avec divers cas.
	 */
	void testChercheProchainX() {
		System.out.println();
		System.out.println("*** testChercheProchainX()");
		int[] positionsX1 = {0, 1, 3};
		int[] positionsY1 = {0, 2, 4};
		int rangActuel1 = 2;

		int[] positionsX2 = {0, 1, 2, 4, 5};
		int[] positionsY2 = {0, 3, 6, 9, 12};
		int rangActuel2 = 5;

		int resultatAttendu1 = 5;
		int resultatAttendu2 = 7;

		testCasChercheProchainX(positionsX1, positionsY1, rangActuel1, resultatAttendu1);
		testCasChercheProchainX(positionsX2, positionsY2, rangActuel2, resultatAttendu2);
	}

	/**
	 * Effectue un test de la méthode chercheProchainX avec un cas donné.
	 * @param positionsX, tableau d'entiers contenant les positions x
	 * @param positionsY, tableau d'entiers contenant les positions y
	 * @param rangActuel, un entier contenant la valeur du rang actuel
	 * @param resultatAttendu, le résultat attendu du test.
	 */
	void testCasChercheProchainX(int[] positionsX, int[] positionsY, int rangActuel, int resultatAttendu) {
		// Arrange
		System.out.print("chercheProchainX(");
		afficheTableau(positionsX);
		System.out.print(", ");
		afficheTableau(positionsY);
		System.out.print(", " + rangActuel + ")\tAttendu : " + resultatAttendu + "\tRésultat : ");
		// Act
		int resExec = chercheProchainX(positionsX, positionsY, rangActuel);
		// Assert
		if (resExec == resultatAttendu) {
			System.out.println("OK");
		} else {
			System.err.println("ERREUR");
		}
	}

	/**
	 * Effectue des tests de la méthode contient avec divers cas.
	 */
	void testContient() {
		System.out.println();
		System.out.println("*** testContient()");
		int[] tabPosition1 = {0, 2, 4, 6};
		int value1 = 3;
		int value2 = 4;

		int[] tabPosition2 = {1, 3, 5, 7, 9};
		int value3 = 5;
		int value4 = 0;

		testCasContient(tabPosition1, value1, false);
		testCasContient(tabPosition1, value2, true);

		testCasContient(tabPosition2, value3, true);
		testCasContient(tabPosition2, value4, false);
	}

	/**
	 * Effectue un test de la méthode contient avec un cas donné.
	 * @param tabPosition, tableau d'indice pour stocker les rangs
	 * @param value, la valeur de l'indice
	 * @param resultatAttendu, le résultat attendu du test.
	 */
	void testCasContient(int[] tabPosition, int value, boolean resultatAttendu) {
		// Arrange
		System.out.print("contient(");
		afficheTableau(tabPosition);
		System.out.print(", " + value + ")\tAttendu : " + resultatAttendu + "\tRésultat : ");
		// Act
		boolean resExec = contient(tabPosition, value);
		// Assert
		if (resExec == resultatAttendu) {
			System.out.println("OK");
		} else {
			System.err.println("ERREUR");
		}
	}

	/**
	 * Effectue des tests de la méthode estUnePositionGagnante avec divers cas.
	 */
	void testEstUnePositionGagnante() {
		System.out.println();
		System.out.println("*** testEstUnePositionGagnante()");
		int x1 = 2;
		int y1 = 3;
		int[][] posGagnantes1 = {{1, 2, 3}, {2, 3, 4}};

		int x2 = 4;
		int y2 = 1;
		int[][] posGagnantes2 = {{1, 2, 3}, {2, 3, 4}};

		testCasEstUnePositionGagnante(x1, y1, posGagnantes1, true);
		testCasEstUnePositionGagnante(x2, y2, posGagnantes2, false);
	}

	/**
	 * Effectue un test de la méthode estUnePositionGagnante avec un cas donné.
	 * @param x, un entier représentant l'abscisse d'une position à analyser
	 * @param y, un entier représentant l'ordonnée d'une position à analyser
	 * @param posGagnantes, un tableau à deux entiers permettant de savoir si x et y forment un couple de coordonnées gagnantes
	 * @param resultatAttendu, le résultat attendu du test.
	 */
	void testCasEstUnePositionGagnante(int x, int y, int[][] posGagnantes, boolean resultatAttendu) {
		// Arrange
		System.out.print("estUnePositionGagnante(" + x + ", " + y + ", ");
		afficheTab(posGagnantes);
		System.out.print(")\tAttendu : " + resultatAttendu + "\tRésultat : ");
		// Act
		boolean resExec = estUnePositionGagnante(x, y, posGagnantes);
		// Assert
		if (resExec == resultatAttendu) {
			System.out.println("OK");
		} else {
			System.err.println("ERREUR");
		}
	}
	
	/**
	 * Affiche le contenu d'un tableau d'entiers.
	 * @param tableau, le tableau à afficher
	 */
	void afficheTableau(int[] tableau) {
		System.out.print("[");
		for (int i = 0; i < tableau.length; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(tableau[i]);
		}
		System.out.print("]");
	}
}
