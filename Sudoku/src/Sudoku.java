public class Sudoku {
	/*
	 * 
	 * L2 - PO, TP n 2 
	 * Auteur : KOUMBA Elisia 
	 * 
	 */
	static final int n = 3;		// taille des regions
	/*
	 * Terminologie
	 * 
	 * m est un plateau (de sudoku) si
	 * 	- m est un int [][] ne contenant que des entiers compris entre 0 et 9
	 * 	- m.length = n^2
	 *  - m[i].length = n^2 pour tous les i de 0 a n^2-1
	 *  
	 */

	static String enClair (int [][] m) {
		/*
		 * Prerequis : m est un plateau de sudoku
		 * Resultat : une chaine dont l'affichage permet de visualiser m
		 * 
		 */
		String r = "" ;		
		for (int i = 0; i < n*n ; i++) {
			for (int j = 0; j < n*n ; j++) {
				r = r + m[i][j] + " " ;
				if (j%n == n-1) {r = r + "  ";}
			}
			if (i%n == n-1) {r = r + "\n";}
			r = r + "\n";
		}		
		r = r + " " ;		
		return r ;
	} // enClair
	
	static int [][] aPartirDe (String s) {
		/*
		 * Prerequis : s est une chaine contenant au moins n^4 chiffres decimaux
		 * Resultat : un plateau de sudoku initialise avec les n^4 premiers chiffres
		 * decimaux de s (les chiffres sont consideres comme ranges par lignes).
		 */
		int [][] m = new int [n*n][n*n] ;
		int k = 0 ;
		for (int i = 0; i < m.length ; i++) {
			for (int j = 0; j < m[i].length ; j++) {
				while ("0123456789".indexOf(s.charAt(k))==-1) {k++;}
				m[i][j] = (int) s.charAt(k) - (int) '0' ;
				k++ ;
			}			
		}
		return m ;
		
	} // aPartirDe
	
	static boolean presentLigne (int [][] m, int v, int i) {
		boolean res = false;
		for(int j=0; j<m.length && res==false ; j++) {
			if(m[i][j]==v) {
				res=true;
			}
		}
		return res ; 
	} // presentLigne
	
	static boolean presentColonne (int [][] m, int v, int j) {
		boolean res = false;
		for(int i=0; i<m.length && res==false ; i++) {
			if(m[i][j]==v) {
				res=true;
			}
		}
		return res ;
	} // presentColonne

	
	static boolean presentRegion  (int [][] m, int v, int i, int j) {
		
		int resi=i%n;
		int icoin= i-resi;
		int resj=j%n;
		int jcoin= j-resj;
		boolean res = false;
		for(int x=icoin; x<icoin+n && !res;x++) {
			for(int y=jcoin ; y<jcoin+n && !res; y++) {
				if(m[x][y]==v) {
					res=true;
				}
			}
		}
		return res ;
	} // presentRegion
	
	
	static boolean [] lesPossiblesEn (int [][] m, int i, int j) {
		boolean [] res= new boolean[n*n+1];
		for (int x=1;x<n*n+1;x++) {
			if(!presentLigne(m,x,i) && !presentColonne(m,x,j) && !presentRegion(m,x,i,j) && m[i][j]==0){
				res[x]=true;
			}
			else {
				res[x]=false;
			}
		}
		return res ; 
	} // lesPossiblesEn
	
	
	static String enClair (boolean[] t) {
		/*
		 * Prerequis : t.length != 0
		 * Resultat :
		 * une chaine contenant tous les indices i de la tranche [1..t.length-1] tels
		 * que t[i] soit vrai
		 */
		String r = "{" ;
		for (int i = 1; i < t.length; i++) {
			if (t[i]) {r = r + i + ", " ; }
		}
		if (r.length() != 1) {r = r.substring(0, r.length()-2);}
		return r + "}" ;
	} // enClair
	
	

	static int toutSeul (int [][] m, int i, int j) {
		boolean[] tab=lesPossiblesEn(m,i,j);
		int res=-1;
		int x=0;
		for(int v=0; v<tab.length && x<2; v++) {
			if(tab[v]==true) {
				x++;
				if(x==1) {
					res=v;
				}
				else {
					res=-1;
				}
			}

		}
		return res ;
	} // toutSeul

	static void essais (String grille) {

		int[][] m = aPartirDe(grille);
		System.out.println("Probleme\n\n"+enClair(m));
		boolean zero=true;
		while (zero==true){
			zero=false;
			for(int i =0; i<n*n ; i++){
				for(int j=0; j<n*n ; j++) {
					if(toutSeul(m,i,j)!=-1) {
						m[i][j]=toutSeul(m,i,j);
						System.out.println("Coordonnées de la case :[" +i+"]["+j+"] la valeur affectée:"+m[i][j]);
						zero = true ;
					}
				}
			}
			System.out.println("Il se peut qu'on ait avancé\n\n"+enClair(m));
		}
	}
	
	public static void main(String[] args) {
		String grille1 = 
			"040 001 006 \n" +
			"007 900 800 \n" +
			"190 086 074 \n" +
			"            \n" +
			"200 690 010 \n" +
			"030 405 090 \n" +
			"060 017 003 \n" +
			"            \n" +
			"910 750 042 \n" +
			"008 002 700 \n" +
			"400 300 080   " ;
		String grille2 = 
		    "030 000 006 \n" +
			"000 702 300 \n" +
			"104 038 000 \n" +
			"            \n" +
			"300 020 810 \n" +
		    "918 000 265 \n" +
			"062 050 007 \n" +
			"            \n" +
			"000 140 708 \n" +
			"001 209 000 \n" +
			"800 000 020   " ;
		String grille3 = 
			    "30 40 \n" +
				"01 02 \n" +
				"      \n" +
				"04 03 \n" +
			    "20 10  \n" ;
		String grille4=
				"D060 0B00 00G0 020E \n"+
				"0BF0 05D0 0EA0 0980 \n"+
				"C500 608F 1307 00DA \n"+
				"0007 0001 B000 6000 \n"+
				"            \n" +
				"0010 C000 0009 0B00 \n"+
				"5C00 040A G0B0 001F \n"+
				"0780 00BE 4A00 0520 \n"+
				"00D3 0G67 51E0 9800 \n"+
				"            \n" +
				"005F 06GC ABD0 7E00 \n"+
				"0E70 00F4 9600 0CB0 \n"+
				"1G00 080B 30C0 00F6 \n"+
				"00B0 2000 000E 0G00 \n"+
				"            \n" +
				"000B 000G 8000 4000 \n"+
				"8400 B0E6 DG01 0057 \n"+
				"0DC0 03A0 0970 01G0 \n"+
				"G0E0 0F00 0030 0A02 \n";
		
		essais(grille1) ;
		//essais(grille2) ;
		//essais(grille3) ;
		//essais(grille4) ;
		//System.out.println(presentLigne(aPartirDe(grille1),7,1));
		//System.out.println(presentColonne(aPartirDe(grille1),7,1));
		//System.out.println(presentRegion(aPartirDe(grille1),7,1,1));
		//System.out.println(lesPossiblesEn (aPartirDe(grille1),4,2));
		//System.out.println (toutSeul(aPartirDe(grille1),8,4));
	}
		
}
//Pour un tableau 4*4 il suffit de mettre n=2
//Pour un tableau 16*16 il faut mettre n=4 et modifié la fonction aPartirDe