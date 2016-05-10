
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class Main {
        public static void main(String[] args) {
            File file = new File(args[0]);
            BufferedReader br = null;
            
            StringBuffer strMessage = new StringBuffer();
            char[] alfabet = new char[26];
            char tipReflector = ' ';
            Rotor[] rotors = new Rotor[3];
            Plugboard plugboard = new Plugboard();
            char operatie = ' ';
            
            try {
                FileReader fr = new FileReader(file);
                br = new BufferedReader(fr);
                
                //Linia I din fisier - alfabetul
                alfabet = br.readLine().replaceAll("\\s|\n","").toCharArray();
                
                //Linia II din fisier - configuratia plugboardului
                String line = br.readLine().replaceAll("[^a-zA-Z0-9]", "");
                plugboard = new Plugboard(line);
                
                //Linia III din fisier - Tipul reflectorului si roatoarele folosite
                String linie = br.readLine().replaceAll("\\s|\n","");
                tipReflector = linie.charAt(0);
                for (int i = 1; i < linie.length(); i++) {
                    rotors[i - 1] = new Rotor(linie.charAt(i));
                }
                
                //Linia IV din fisier - Offset-ul Inelelor
                String pozInele = br.readLine().replaceAll("\\s|\n","");
                for (int i = 0; i < pozInele.length(); i++) {
                    rotors[i].setOffsetInel(pozInele.charAt(i));
                }
                
                //Linia V din fisier - Offset-ul Rotoarelor
                String pozRotoare = br.readLine().replaceAll("\\s|\n","");
                for (int i = 0; i < pozInele.length(); i++) {
                    rotors[i].setOffsetRotor(pozRotoare.charAt(i));
                }
               
                //Linia VI din fisier - Operatia pe care urmeaza sa o facem ('C'riptare sau 'D'ecriptare) 
                operatie = br.readLine().replaceAll("\\s|\n","").toCharArray()[0]; 
                
               
                //Linia VII din fisier - Mesajul care trebuie Codificat / Decodificat
                String message = "";
                while ((message = br.readLine()) != null) {
                    strMessage.append(message);
                }
                
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + file.toString());
            } catch (IOException e) {
                System.out.println("Unable to read file: " + file.toString());
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Unable to close file: " + file.toString());
                }
                catch(NullPointerException ex) {
                }
            }
            
            //AVEM TIPUL REFLECTORULUI
            Reflector reflector = new Reflector(tipReflector);          
            
            //AVEM O ENIGMA CAREIA I-AM DAT UN ALFABET , UN PLUGBOARD , UN REFLECTOR , 3 ROTOARE SI UN  MESAJ PENTRU OPERATIA DE CRIPTARE
            Enigma enigmaDeclarate = new Enigma(String.valueOf(alfabet), plugboard, reflector, rotors, strMessage.toString());
            if (operatie == 'C' || operatie == 'D') 
            	enigmaDeclarate.criptare();
        }
}