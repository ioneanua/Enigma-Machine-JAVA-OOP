
public class Plugboard {
    private String plugboardDus = new String();
    private String plugboardIntors = new String();
    
    /**
     * Constructor fara parametrii
     */
    public Plugboard() {}
    
    /**
     * <p>
     * Constructor care seteaza plugboard-ul pentru drumul dus si pentru drumul intors
     * </p>
     * @param str
     * 			Primeste ca parametru str , care este linia din fisier
     */
    public Plugboard(String str) {
        plugboardDus = str;
        plugboardIntors = new StringBuffer(plugboardDus).reverse().toString();
    }
    
    /**
     * Get-er 
     * @return
     */
    public String getPlugboardDus() {
        return plugboardDus;
    }
    
    /**
     * Get-er
     * @return
     */
    public String getPlugboardIntors() {
		return plugboardIntors;
	}
}
