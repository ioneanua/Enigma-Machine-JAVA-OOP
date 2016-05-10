
public class Rotor {
    private char notchLetter;
    private char secondNotchLetter;
    private int tip;
    private char offsetInel;
    private char offsetRotor;
    
    private String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    
    private String[] rotoare = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ","AJDKSIRUXBLHWTMCQGZNPYFVOE","BDFHJLCPRTXVZNYEIWGAKMUSQO",
            "ESOVPZJAYQUIRHXLNFTGKDCMWB","VZBRGITYUPSDNHLXAWMJQOFECK","JPGVOUMFYQBENHZRDKASXLICTW",
            "NZJHGRCXMYSWBOUFAIVLPEKQDT","FKQHTLXOCBJSPDZRAMEWNIUYGV"};
    /**
     * Constructor fara parametrii
     */
    public Rotor() {}
    
    /**
     * Metoda ce intoarce tipul rotorului
     * @param tip
     */
    public Rotor(char tip) {
        this.tip = tip - '0';
    }
    
    /**
     * Set-er
     * @param offsetRotor
     */
    public void setOffsetRotor(char offsetRotor) {
        this.offsetRotor = offsetRotor;
    }
    
    /**
     * Set-er
     * @param offsetInel
     */
    public void setOffsetInel(char offsetInel) {
        this.offsetInel = offsetInel;
    }
    
    /**
     * Get-er
     * @return offsetRotor
     */
    public char getOffsetRotor() {
        return offsetRotor;
    }
    
    /**
     * Get-er
     * @return offsetInel
     */
    public char getOffsetInel() {
		return offsetInel;
	}
    
    /**
     * Seteaza notch-ul rotorului primit ca paramteru
     * @param rotor
     */
    public void notchLetter(String rotor){
        if (rotor.equals(rotoare[0]))
            notchLetter = 'R';
        else if (rotor.equals(rotoare[1]))
            notchLetter = 'F';
        else if (rotor.equals(rotoare[2]))
            notchLetter = 'W';
        else if (rotor.equals(rotoare[3]))
            notchLetter = 'K';
        else if (rotor.equals(rotoare[4]))
            notchLetter = 'A';
        else if (rotor.equals(rotoare[5]) || rotor.equals(rotoare[6]) || 
                rotor.equals(rotoare[7])) {
            
            notchLetter = 'A';
            secondNotchLetter = 'N';
        }
    }
    
	/**
	 * Pozitia unei litere in alfabet
	 * @param literaAlfabet
	 * @return 
	 * 			Returneaza indexul din alfabet al literei primite ca parametru
	 */
    public int pozitieAlfabet(char literaAlfabet) {
        return alfabet.indexOf(literaAlfabet);
    }
    
    /**
     * <p>
     * Daca rotorul primit ca parametru este egal cu unul din rotaorele existente ,
     * verificam index-ul literei de intrare in alfabet si o inlocuim cu indexul rotorului
     * </p>
     * @param literaIntrare
     * @param rotor
     * @return
     * 			Returneaza litera in urma maparii Inverse
     */
    public char mapareCharRotor(char literaIntrare , String rotor) {
        int poz = -1;
        for (int i = 0; i < 8; i++) {
            if (rotor.equals(rotoare[i])) {
                poz = i;
                break;
            }
        }
        
        int index = alfabet.indexOf(literaIntrare);
        if (poz != -1) {
            return rotoare[poz].charAt(index);
        }
        return '{';
        
    }
    
    /**
     * <p>
     * Daca rotorul primit ca parametru este egal cu unul din rotaorele existente ,
     * verificam index-ul literei de intrare in rotor si o inlocuim cu indexul din alfabet
     * </p>
     * @param literaIntrare
     * @param rotor
     * @return
     * 			Returneaza litera in urma maparii
     */
    public char mapareInversa(char literaIntrare , String rotor){
        int poz = -1;
        for (int i = 0; i < 8; i++) {
            if (rotor.equals(rotoare[i])) {
                poz = i;
                break;
            }
        }
        
        if (poz != -1) {
            int index = rotoare[poz].indexOf(literaIntrare);
            return alfabet.charAt(index);
        }
        return '{';
    }
    
    /**
     * Primeste numarul rotorului 
     * @param nrRotor
     * @return
     * 			returneaza rotorul corespunzator numarului
     */
    public String transfRotor(int nrRotor){
        if (nrRotor >= 0 && nrRotor < 8) {
            return rotoare[nrRotor];
        }
        return "nu avem";
    }
    
    /**
     * Get-er
     * @return
     * 			Notch Rotor
     */
    public char getNotchLetter() {
        return notchLetter;
    }
    
    /**
     * Get-er
     * @return
     * 			Second Notch Letter
     */
    public char getSecondNotchLetter() {
        return secondNotchLetter;
    }
    
    /**
     * 
     * @return
     * 			Returneaza tipul rotorului
     */
    public int getTip() {
        return tip;
    }
}
