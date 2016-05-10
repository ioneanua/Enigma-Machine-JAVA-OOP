
public class Reflector {
    private char tipReflector;
    
    /**
     * Constructor ce initializeaza reflectorul 
     * @param tipReflector
     */
    public Reflector (char tipReflector) {
        this.tipReflector = tipReflector;
    }
    
    /**
     * Get-er
     * @return
     * 			Returneaza tipul reflectorului
     */
    public char getTipReflector() {
		return tipReflector;
	}
    
    /**
     * </p>
     * Matrice de char-ri in care tinem configuratia Reclectorului de tip B
     * </p>
     */
    private char [][] Reflector_B = new char[][] {{'A', 'Y'},
                                          {'B', 'R'},
                                          {'C', 'U'},
                                          {'D', 'H'},
                                          {'E', 'Q'},
                                          {'F', 'S'},
                                          {'G', 'L'},
                                          {'I', 'P'},
                                          {'J', 'X'},
                                          {'K', 'N'},
                                          {'M', 'O'},
                                          {'T', 'Z'},
                                          {'V', 'W'}};
    /**
     * <p>
     * Matrice de char-uri in care tinem configuratia Reflectorului de tip C
     * </p>
     */
    private char [][] Reflector_C = new char[][] {{'A', 'F'},
                                          {'B', 'V'},
                                          {'C', 'P'},
                                          {'D', 'J'},
                                          {'E', 'I'},
                                          {'G', 'O'},
                                          {'H', 'Y'},
                                          {'K', 'R'},
                                          {'L', 'Z'},
                                          {'M', 'X'},
                                          {'N', 'W'},
                                          {'T', 'Q'},
                                          {'S', 'U'}};
     
                                          
    /**
     * Get-er                                     
     * @return
     * 			Returneaza Reflector B
     */
    public char[][] getReflectorB() {
        return Reflector_B;
    }
    
    /**
     * Get-er
     * @return
     * 			Returneaza Reflector C
     */
    public char[][] getReflectorC() {
        return Reflector_C;
    }
}
