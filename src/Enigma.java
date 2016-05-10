
public class Enigma {
	private String alfabet;
    private Plugboard plugboard = null;
    private Reflector reflector;
    private Rotor[] rotoare = new Rotor[3];
    private String message;
    
    /**
     * <p>
     * Constructor care initializeaza masina Enigma cu un alfabet ,un plugboard
     * un reflector , 3 rotoare si un mesaj 
     * </p>
     * @param alfabet
     * @param plugboard
     * @param reflector
     * @param rotoare
     * @param message
     */
    public Enigma(String alfabet, Plugboard plugboard, Reflector reflector, Rotor[] rotoare, String message) {
    	this.alfabet = alfabet;
        this.plugboard = plugboard;
        this.reflector = reflector;
        this.rotoare = rotoare;
        this.message = message;
    }
    
    /**
     * <p>
     * Metoda ce roteste rotorul din dreapta
     * </p>
     */
    public void rotireRotorDreapta() {
        rotoare[2].setOffsetRotor((char) (rotoare[2].getOffsetRotor() - 'A' + 66));
    }
    
    /**
     * <p>
     * Verificam daca rotorul din mijloc se afla cu o pozitie ininte de notch (am luat in considerare si cazul
     * in care poate fi rotor cu doua notch-uri) . Daca da, rotim rotorl cel mai din stanga cu o pozitie (iar 
     * daca aceasta depaseste 'Z' incepem de la 'A') dupa care il punem si pe cel din mijoc pe notch-ul sau
     * (daca acesta , de asemenea , depaseste litera 'Z' va continua de la 'A').
     * </p>
     */
	public void rotorMijlocPeNotch() {
		if ((char) (rotoare[1].getOffsetRotor() - 'A' + 66) == rotoare[1].getNotchLetter() || 
				(char) (rotoare[1].getOffsetRotor() - 'A' + 66) == rotoare[1].getSecondNotchLetter()) {

			rotoare[0].setOffsetRotor((char) (rotoare[0].getOffsetRotor() - 'A' + 66));
			if (rotoare[0].getOffsetRotor() > 'Z') {
				rotoare[0].setOffsetRotor('A');
			}
			rotoare[1].setOffsetRotor((char)(rotoare[1].getOffsetRotor() - 'A' + 66));
			if (rotoare[1].getOffsetRotor() > 'Z') {
				rotoare[1].setOffsetRotor('A');
			}
		}
	}
	
	/**
	 * <p>
	 * Verificam daca rotorul din dreapta se afla cu o pozitie inainte de notch (am tratat
	 * si cazul in care ar putea sa aiba doua notch-uri ) . Daca da , mutam rotorul din 
	 * mijloc cu o pozitie si trecem rotorul din dreapta pe notch (Daca litera depaseste 
	 *  'Z' , continua rotirea de la 'A').
	 * </p>
	 */
	public void rotorDreaptaPeNotch() {
		if (rotoare[2].getOffsetRotor() > 'Z') {
			rotoare[2].setOffsetRotor('A');
		}
		
		if (rotoare[2].getOffsetRotor() == rotoare[2].getNotchLetter() ||
				rotoare[2].getOffsetRotor() == rotoare[2].getSecondNotchLetter()) {
			rotoare[1].setOffsetRotor((char)(rotoare[1].getOffsetRotor() - 'A' + 66));
			if (rotoare[1].getOffsetRotor() > 'Z') {
				rotoare[1].setOffsetRotor('A');
			}
		}
	}
	
	/**
	 * <p>
	 * Metoda ce primeste ca parametru o litera si returneaza
	 * indexul acesteia din alfabet
	 * </p>
	 * @param tipOffset
	 * @return
	 * 			Returneaza codul ASCII al lierei primite ca parametru
	 */
	public int charToASCII(char tipOffset){
		return tipOffset - 65;
	}
	
	/**
	 * <p>
	 * Metoda ce primeste ca parametru un index si returneaza litera corespunzatoare
	 * acestuia din tabelul ASCII
	 * </p>
	 * @param index
	 * @return
	 * 			Returneaza litera corezpunzatoare index-ului primit ca parametru
	 */
	public char ASCIItoChar(int index){
		return (char)(index + 65);
	}
	
	/**
	 * <p>
	 * Metoda ce seteaza notch-ul rotoarelor 
	 * </p>
	 */
	public void setNotches() {
		for (int i = 0; i < rotoare.length; i++) {
			rotoare[i].notchLetter(rotoare[i].transfRotor(rotoare[i].getTip() - 1));
		}
	}
	
	/**
	 * <p>
	 * Metoda ce face cripatarea:
	 * Se Seteaza notc-urile rotoarelor si , se ia litera cu litera si se incepe criptarea.
	 * Plugboard:Se trece litera prin plugboard (daca corespondenta exista)
	 * 
	 * -se fac rotirile specifice pentru notch-uri
	 * 
	 * Drum Dus: Mergem de la rotorul din dreapta pana la rotorul din stanga .
	 * 			Facem operatie corespunzatoare , mapam rezultatul , facem din nou operatia
	 * 			si o sa rezulte un index , pe care il transformam in litera.
	 * Reflector: Litera rezultata intra in reflector , iese litera corespunzatoare 
	 * 			  configurarii reflectorului respectiv si se pregateste pentru drumul intors.
	 * Drum Intors:Operatia este la fel ca la drumul Dus doar ca difera operatie efectuata 
	 * 				(doar la semne) si iese un index pe care il transformam in litera.
	 * Plugboard:Litera rezultata o trecem prin configuratia plugboard-ului (daca exista una).
	 * Iesire:Litera la finualul criptarii (dupa ce a iesit din plugboard).
	 * </p>
	 */
    public void criptare() {
    	setNotches();
    	String out = new String();
        char[] mesaj = message.toCharArray();
        for (char c : mesaj) {
        	if (alfabet.indexOf(c) == -1) {
        		continue;
        	}
        	
            int indexPlugboard = plugboard.getPlugboardDus().indexOf(c);
            
            //Intreare in PLUGBOARD
            if (indexPlugboard != -1){
                if (indexPlugboard % 2 == 0){
                    c = plugboard.getPlugboardDus().charAt(indexPlugboard + 1);
                } else {
                    c = plugboard.getPlugboardDus().charAt(indexPlugboard - 1);
                }
            }
            //Iesire din PLUGBOARD
            
            char literaIesire2 = c;
            rotireRotorDreapta();
            rotorMijlocPeNotch();
            rotorDreaptaPeNotch();
            
            //Drum TASTE - REFLECTOR
            for (int i = rotoare.length - 1; i >= 0; i--) {
            	int indexIntrare = charToASCII(literaIesire2) + 
            			(rotoare[i].getOffsetRotor() - 'A') - 
            			(rotoare[i].getOffsetInel() - 'A');
            	
            	if (indexIntrare > 25) {
            		indexIntrare = indexIntrare - 26;
            	} else if (indexIntrare < 0) {
            		indexIntrare = indexIntrare + 26;
            	}
            	
            	char literaIntrare = ASCIItoChar(indexIntrare);
            	
            	String rotorFolosit = rotoare[i].transfRotor(rotoare[i].getTip() - 1);
            	char literaIesire = rotoare[i].mapareCharRotor(literaIntrare, rotorFolosit);
            	int indexIesire = charToASCII(literaIesire) -
            			(rotoare[i].getOffsetRotor() - 'A') +
            			(rotoare[i].getOffsetInel() - 'A');
            	
            	if (indexIesire > 25) {
            		indexIesire = indexIesire - 26;
            	} else if (indexIesire < 0) {
            		indexIesire = indexIesire + 26;
            	}
            	
            	literaIesire2 = ASCIItoChar(indexIesire);
            }
            //------------
            char tipReflector = reflector.getTipReflector();
            char[][] mapareReflector = new char[13][2];
            if (tipReflector == 'B') {
            	mapareReflector = reflector.getReflectorB();
            } else {
            	mapareReflector = reflector.getReflectorC();
            }
            for (int i = 0; i < 13; i++) {
            	if (mapareReflector[i][0] == literaIesire2 || mapareReflector[i][1] == literaIesire2) {
            		if (mapareReflector[i][0] == literaIesire2) {
            			literaIesire2 = mapareReflector[i][1];
            			break;
            		} else {
            			literaIesire2 = mapareReflector[i][0];
            			break;
            		}
            	}
            }
            //--------------
            
            //Drum REFLECTOR - IESIRE
            for (int i = 0; i < rotoare.length; i++) {
            	int indexIntrare = charToASCII(literaIesire2) +
            			(rotoare[i].getOffsetRotor() - 'A') -
            			(rotoare[i].getOffsetInel() - 'A');
            	
            	if (indexIntrare > 25) {
            		indexIntrare = indexIntrare - 26;
            	} else if (indexIntrare < 0) {
            		indexIntrare = indexIntrare + 26;
            	}
            	
            	char literaIntrare = ASCIItoChar(indexIntrare);
            	String rotorFolosit = rotoare[i].transfRotor(rotoare[i].getTip() - 1);
            	char literaIesire = rotoare[i].mapareInversa(literaIntrare, rotorFolosit);
            	int indexIesire = charToASCII(literaIesire) -
            			(rotoare[i].getOffsetRotor() - 'A') +
            			(rotoare[i].getOffsetInel() - 'A');
            	
            	if (indexIesire > 25) {
            		indexIesire = indexIesire - 26;
            	} else if (indexIesire < 0) {
            		indexIesire = indexIesire + 26;
            	}
            	literaIesire2 = ASCIItoChar(indexIesire);
            }
            indexPlugboard = plugboard.getPlugboardDus().indexOf(literaIesire2);
            //Intreare in PLUGBOARD
            if (indexPlugboard != -1){
                if (indexPlugboard % 2 == 0){
                    literaIesire2 = plugboard.getPlugboardDus().charAt(indexPlugboard + 1);
                } else {
                    literaIesire2 = plugboard.getPlugboardDus().charAt(indexPlugboard - 1);
                }
            }
            //Iesire din PLUGBOARD
            out += literaIesire2;
        
        }
        System.out.println(out);
    }
}
